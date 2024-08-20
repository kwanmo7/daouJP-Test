package scheduler;

import static org.mockito.Mockito.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scheduler.service.SchedulerService;

public class SchedulerTest { // 단위 테스트

  private static final Logger logger = LoggerFactory.getLogger(SchedulerTest.class);

  private Scheduler scheduler;
  private SchedulerService schedulerService;

  private File inputDir;

  @BeforeEach
  public void setUp() throws IOException {
    schedulerService = mock(SchedulerService.class);
    scheduler = new Scheduler(schedulerService);

    inputDir = new File("input/");
    if(!inputDir.exists()){
      inputDir.mkdir();
    }
    logger.info("Input 디렉토리 생성 : {}", inputDir.getAbsolutePath());
  }

  @AfterEach
  public void after() throws IOException{
    for(File file : inputDir.listFiles()){
      file.delete();
    }
    inputDir.delete();
    logger.info("Input 디렉토리 삭제 : {}",inputDir.getAbsolutePath());
  }

  @Test
  public void testGetFileForTxtFile() throws IOException{ // txtFile Read Test
    File txtFile = new File(inputDir, "test.txt");
    try(FileWriter writer = new FileWriter(txtFile)){
      writer.write("2024-08-16 00|100|200|300|400|500");
    }
    logger.info("txt File 생성 : {}",txtFile.getAbsolutePath());
    scheduler.getFile();

    verify(schedulerService, times(1)).readTxtFile(txtFile);
    verify(schedulerService, times(0)).readCsvFile(any(File.class));

    logger.info("txt file process OK");
  }

  @Test
  public void testGetFileForCsvFile() throws IOException{ // csvFile Read Test
    File csvFile = new File(inputDir, "test.csv");
    try(FileWriter writer = new FileWriter(csvFile)){
      writer.write("2024-08-15 00,200,300,400,500,600");
    }

    logger.info("csv file 생성 : {}", csvFile.getAbsolutePath());
    scheduler.getFile();

    verify(schedulerService, times(1)).readCsvFile(csvFile);
    verify(schedulerService, times(0)).readTxtFile(any(File.class));

    logger.info("csv file process OK");
  }
}
