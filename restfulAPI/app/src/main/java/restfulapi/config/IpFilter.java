package restfulapi.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class IpFilter implements Filter {

  @Value("${allowed-ips}")
  private String allowedIps; // application.properties에서 설정한 허용 아이피

  private List<String> allowedIpList;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    allowedIpList = Arrays.asList(allowedIps.split(","));
  }

  @Override
  public void destroy() {
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpServletResponse httpResponse = (HttpServletResponse) response;

    String remoteAddr = httpRequest.getRemoteAddr();
    if(allowedIpList.contains(remoteAddr)){ // 허용 IP체크
      System.out.println("Ip Okay");
      chain.doFilter(request,response);
    }else{
      httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN,"Forbidden IP");
    }
  }

  public List<String> getAllowedIpList() {
    return allowedIpList;
  }
}
