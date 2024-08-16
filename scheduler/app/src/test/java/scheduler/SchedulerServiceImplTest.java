package scheduler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import scheduler.service.impl.SchedulerServiceImpl;

@SpringBootTest
@ActiveProfiles("test")
public class SchedulerServiceImplTest { // Spring Boot 통합 테스트
  private static final Logger logger = LoggerFactory.getLogger(SchedulerServiceImplTest.class);

  @Autowired
  private SchedulerServiceImpl schedulerService;

  private File txtFile;
  private File csvFile;

  @BeforeEach
  void Set() throws IOException{
    logger.info("SchedulerServiceImpl Instance Initialized");

    txtFile = new File("input/test.txt");
    if(!txtFile.exists()){
      if(!txtFile.getParentFile().exists()){
        txtFile.getParentFile().mkdir();
      }
      try(FileWriter writer = new FileWriter(txtFile)){
        writer.write("2024-08-16 00|111|22|333|4444|555");
      }
      logger.info("txt File 생성 : {}", txtFile.getAbsolutePath());
    }else{
      logger.info("txt File 존재 : {}", txtFile.getAbsolutePath());
    }

    csvFile = new File("input/test.csv");
    if(!csvFile.exists()){
      if(!csvFile.getParentFile().exists()){
        csvFile.getParentFile().mkdir();
      }
      try(FileWriter writer = new FileWriter(csvFile)){
        writer.write("2024-07-07 00,1000,20,333,4040,55555");
      }
      logger.info("csv File 생성 : {}", csvFile.getAbsolutePath());
    }else{
      logger.info("csv File 존재 : {}", csvFile.getAbsolutePath());
    }
  }

  @Test
  void testReadTxtFile() throws IOException{
    schedulerService.readTxtFile(txtFile);
    logger.info("txt File OK");
  }

  @Test
  void testReadCsvFile() throws IOException{
    schedulerService.readCsvFile(csvFile);
    logger.info("csv file OK");
  }
}
