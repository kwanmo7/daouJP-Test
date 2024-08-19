package restfulapi.config;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import restfulapi.mapper.ApiKeyMapper;

@Component
@RequiredArgsConstructor
public class ApiKeyFilter implements Filter {

  private ApiKeyMapper apiKeyMapper;
  private static final String API_KEY_HEADER = "API-Key";

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
  }

  @Override
  public void destroy() {
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpServletResponse httpResponse = (HttpServletResponse) response;

    String apiKey = httpRequest.getHeader(API_KEY_HEADER);
    if(apiKey != null && apiKeyMapper.checkApiKey(apiKey)){
      chain.doFilter(request,response);
    }else{
      httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }
  }
}
