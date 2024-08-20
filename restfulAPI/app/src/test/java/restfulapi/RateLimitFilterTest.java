package restfulapi;

import io.github.bucket4j.Bucket;
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
import org.springframework.http.HttpStatus;
import restfulapi.config.RateLimitFilter;

import static org.mockito.Mockito.*;

public class RateLimitFilterTest {

  private static final Logger logger = LoggerFactory.getLogger(RateLimitFilter.class);
  @Mock
  private HttpServletRequest request;

  @Mock
  private HttpServletResponse response;

  @Mock
  private FilterChain filterChain;

  @InjectMocks
  private RateLimitFilter rateLimitFilter;

  private Bucket bucket;

  @BeforeEach
  public void setUp(){
    logger.info("RateLimitFilterTest Instance Initialized");
    MockitoAnnotations.openMocks(this);
    bucket = mock(Bucket.class);
    rateLimitFilter = new RateLimitFilter(){
      @Override
      protected Bucket makeNewBucket() {
        return bucket;
      }
    };
  }

  @Test
  void testDoFilter_WithinLimit() {
    try{
      logger.info("testDoFilter_WithinLimit Start");
      when(request.getRemoteAddr()).thenReturn("127.0.0.1");
      when(bucket.tryConsume(1)).thenReturn(true);

      rateLimitFilter.doFilter(request,response,filterChain);

      verify(filterChain, times(1)).doFilter(request,response);
      logger.info("testDoFilter_WithinLimit Test Okay");
    }catch (Exception e){
      logger.info("testDoFilter_WithinLimit Test NG");
      logger.info(e.getMessage());
    }
  }

  @Test
  void testDoFilter_ExceedLimit() {
    try{
      logger.info("testDoFilter_ExceedLimit Start");
      when(request.getRemoteAddr()).thenReturn("127.0.0.1");
      when(bucket.tryConsume(1)).thenReturn(false);

      rateLimitFilter.doFilter(request,response,filterChain);
      verify(response,times(1))
            .sendError(HttpStatus.TOO_MANY_REQUESTS.value(),"Too Many Requests");
      logger.info("testDoFilter_ExceedLimit Test Okay");
    }catch (Exception e){
      logger.info("testDoFilter_ExceedLimit NG");
      logger.info(e.getMessage());
    }
  }
}
