package restfulapi;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import restfulapi.config.ApiKeyFilter;
import restfulapi.mapper.ApiKeyMapper;

import static org.mockito.Mockito.*;

public class ApiKeyFilterTest {

  private static final Logger logger = LoggerFactory.getLogger(ApiKeyFilterTest.class);

  @Mock
  private ApiKeyMapper apiKeyMapper;

  @Mock
  private HttpServletRequest request;

  @Mock
  private HttpServletResponse response;

  @Mock
  private FilterChain filterChain;

  @InjectMocks
  private ApiKeyFilter apiKeyFilter;

  @BeforeEach
  public void setUp(){
    logger.info("ApiKeyFilterTest Instance Initialized");

    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testDoFilter_ValidApiKey(){
    try{
      logger.info("testDoFilter_ValidApiKey Start");
      when(request.getHeader("API-Key")).thenReturn("123456");
      when(apiKeyMapper.checkApiKey("123456")).thenReturn(true);

      apiKeyFilter.doFilter(request,response, filterChain);
      verify(filterChain, times(1)).doFilter(request,response);

      logger.info("testDoFilter_ValidApiKey Test Okay");
    }catch (Exception e){
      logger.info("testDoFilter_ValidApiKey Test NG");
      logger.info(e.getMessage());
    }
  }

  @Test
  public void testDoFilter_InvalidApiKey(){
    logger.info("testDoFilter_InvalidApiKey Test Start");
    try{
      when(request.getHeader("API-Key")).thenReturn("1234567");
      when(apiKeyMapper.checkApiKey("1234567")).thenReturn(false);

      apiKeyFilter.doFilter(request,response, filterChain);
      verify(response,times(1))
                .sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");

      logger.info("testDoFilter_InvalidApiKey Test Okay");
    }catch (Exception e){
      logger.info("testDoFilter_InvalidApiKey Test NG");
      logger.info(e.getMessage());
    }
  }
}
