package restfulapi.config;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

@Component
public class LoggingFilter implements Filter {
  private static final DateTimeFormatter DATE_TIME_FORMATTER =
                                  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
  private static final DateTimeFormatter LOG_FILE_FORMATTER =
                                  DateTimeFormatter.ofPattern("yyyyMMddHH");
  private static final String LOG_DIRECTORY = "./log";
  private static final String NAME = "restfulapi";

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    try{
      Files.createDirectories(Paths.get(LOG_DIRECTORY));
    }catch (IOException e){
      throw new ServletException("Directory create fail");
    }
  }

  @Override
  public void destroy() {
    Filter.super.destroy();
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpServletResponse httpResponse = (HttpServletResponse) response;
    LocalDateTime requestTime = LocalDateTime.now();
    chain.doFilter(request,response);
    LocalDateTime responseTime = LocalDateTime.now();

    long duration = Duration.between(requestTime,responseTime).toMillis();
    
    // 로그 파일에 작성될 내용
    String message = String.format("[%s]-[INFO]-[%s]-Request: %s, Response: %s, Duration: %d ms",
                                    requestTime.format(DATE_TIME_FORMATTER),
                                    httpRequest.getMethod(),
                                    httpRequest.getRequestURI(),
                                    httpResponse.getStatus(),
                                    duration);
    String logFileName = String.format("%s_%s.log", NAME,requestTime.format(LOG_FILE_FORMATTER));
    try(FileWriter writer = new FileWriter(Paths.get(LOG_DIRECTORY,logFileName).toString(), true)){
      writer.write(message + System.lineSeparator());
    }
  }
}
