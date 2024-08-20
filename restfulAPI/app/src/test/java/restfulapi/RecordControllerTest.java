package restfulapi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import restfulapi.config.IpFilter;
import restfulapi.controller.RecordController;
import restfulapi.dto.RecordDTO;
import restfulapi.mapper.ApiKeyMapper;
import restfulapi.service.RecordService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RecordController.class)
public class RecordControllerTest {

  private static final Logger logger = LoggerFactory.getLogger(RecordControllerTest.class);

  @Autowired
  private MockMvc mockMvc;
  
  @MockBean
  private RecordService recordService;

  @MockBean
  private ApiKeyMapper apiKeyMapper;

  @MockBean
  private IpFilter ipFilter;

  @InjectMocks
  private RecordController recordController;

  @BeforeEach
  void setUp() {
    when(ipFilter.getAllowedIpList()).thenReturn(Arrays.asList("127.0.0.1"));
  }

  @Test
  void testGetAllRecord() throws Exception {
    logger.info("testGetAllRecord Start");
    LocalDateTime start = LocalDateTime.parse("2024-06-01T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    LocalDateTime end = LocalDateTime.parse("2024-08-20T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    when(apiKeyMapper.checkApiKey("123456")).thenReturn(true);
    when(recordService.selectAllRecord(start,end)).thenReturn(Collections.emptyList());
    mockMvc.perform(get("/api/list")
           .param("start",start.toString())
           .param("end",end.toString())
           .header("API-Key","123456"))
           .andExpect(status().isOk())
           .andReturn();

    logger.info("testGetAllRecord Okay");
  }

  @Test
  void testGetSubscriber() throws Exception {
    logger.info("testGetSubscriber Start");
    LocalDateTime start = LocalDateTime.parse("2024-06-01T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    LocalDateTime end = LocalDateTime.parse("2024-08-20T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    when(apiKeyMapper.checkApiKey("123456")).thenReturn(true);
    when(recordService.selectSubscribers(start,end)).thenReturn(Collections.emptyList());
    mockMvc.perform(get("/api/subscriber")
           .param("start",start.toString())
           .param("end",end.toString())
           .header("API-Key","123456"))
           .andExpect(status().isOk())
           .andReturn();
    logger.info("testGetSubscriber Okay");
  }

  @Test
  void testGetDropouts() throws Exception {
    logger.info("testGetDropouts Start");
    LocalDateTime start = LocalDateTime.parse("2024-06-01T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    LocalDateTime end = LocalDateTime.parse("2024-08-20T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    when(apiKeyMapper.checkApiKey("123456")).thenReturn(true);
    when(recordService.selectDropouts(start,end)).thenReturn(Collections.emptyList());
    mockMvc.perform(get("/api/dropouts")
           .param("start",start.toString())
           .param("end",end.toString())
           .header("API-Key","123456"))
           .andExpect(status().isOk())
           .andReturn();
    logger.info("testGetDropouts Okay");
  }

  @Test
  void testGetPaymentAmount() throws Exception {
    logger.info("testGetPaymentAmount Start");
    LocalDateTime start = LocalDateTime.parse("2024-06-01T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    LocalDateTime end = LocalDateTime.parse("2024-08-20T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    when(apiKeyMapper.checkApiKey("123456")).thenReturn(true);
    when(recordService.selectPaymentAmount(start,end)).thenReturn(Collections.emptyList());
    mockMvc.perform(get("/api/paymentAmount")
           .param("start",start.toString())
           .param("end",end.toString())
           .header("API-Key","123456"))
           .andExpect(status().isOk())
           .andReturn();
    logger.info("testGetPaymentAmount Okay");
  }

  @Test
  void testGetAmountUsed() throws Exception {
    logger.info("testGetAmountUsed Start");
    LocalDateTime start = LocalDateTime.parse("2024-06-01T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    LocalDateTime end = LocalDateTime.parse("2024-08-20T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    when(apiKeyMapper.checkApiKey("123456")).thenReturn(true);
    when(recordService.selectAmountUsed(start,end)).thenReturn(Collections.emptyList());
    mockMvc.perform(get("/api/amountUsed")
           .param("start",start.toString())
           .param("end",end.toString())
           .header("API-Key","123456"))
           .andExpect(status().isOk())
           .andReturn();
    logger.info("testGetAmountUsed Okay");
  }

  @Test
  void testGetSalesAmount() throws Exception {
    logger.info("testGetSalesAmount Start");
    LocalDateTime start = LocalDateTime.parse("2024-06-01T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    LocalDateTime end = LocalDateTime.parse("2024-08-20T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    when(apiKeyMapper.checkApiKey("123456")).thenReturn(true);
    when(recordService.selectSalesAmount(start,end)).thenReturn(Collections.emptyList());
    mockMvc.perform(get("/api/salesAmount")
           .param("start",start.toString())
           .param("end",end.toString())
           .header("API-Key","123456"))
           .andExpect(status().isOk())
           .andReturn();
    logger.info("testGetSalesAmount Okay");
  }

  @Test
  void testUpdateRecord() throws Exception {
    logger.info("testUpdateRecord Start");
    RecordDTO recordDTO = new RecordDTO();
    recordDTO.setId(85L);
    recordDTO.setAmountUsed(800);
    when(apiKeyMapper.checkApiKey("123456")).thenReturn(true);
    mockMvc.perform(put("/api/update").contentType("application/json")
           .content("{\"id\":1,\"name\":\"Updated Record\"}")
           .header("API-Key","123456"))
           .andExpect(status().isOk())
           .andReturn();
    logger.info("testUpdateRecord Okay");
  }

  @Test
  void testInsertRecord() throws Exception{
    logger.info("testInsertRecord Start");
    RecordDTO recordDTO = new RecordDTO();
    recordDTO.setTime(LocalDateTime.parse("2024-08-08 01",DateTimeFormatter.ofPattern("yyyy-MM-dd HH")));
    recordDTO.setAmountUsed(800);
    recordDTO.setDropouts(600);
    recordDTO.setSubscribers(400);
    recordDTO.setPaymentAmount(5000);
    recordDTO.setSalesAmount(1000);
    when(apiKeyMapper.checkApiKey("123456")).thenReturn(true);
    when(recordService.insertRecord(any(RecordDTO.class))).thenReturn(recordDTO);

    mockMvc.perform(post("/api").contentType("application/json")
           .content("{\"time\":\"2024-08-08T01:00:00\",\"amountUsed\":800,\"dropouts\":600,\"subscribers\":400,\"paymentAmount\":5000,\"salesAmount\":1000}")
           .header("API-Key","123456"))
           .andExpect(status().isOk())
           .andReturn();
    logger.info("testInsertRecord Test Okay");
  }


  @Test
  void testDeleteRecord() throws Exception {
    logger.info("testDeleteRecord Start");
    when(apiKeyMapper.checkApiKey("123456")).thenReturn(true);
    doNothing().when(recordService).deleteRecord(85L);
    mockMvc.perform(delete("/api/")
           .param("id","85")
           .header("API-Key","123456"))
           .andExpect(status().isOk())
           .andReturn();
    logger.info("testDeleteRecord Okay");
  }
}
