package scheduler;

import java.io.File;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import scheduler.service.SchedulerService;
import scheduler.vo.ConstVO;

@RequiredArgsConstructor
@Component
public class Scheduler {

  private final SchedulerService schedulerService;

  @Scheduled(cron = "0 0 0 * * ?") // 매일 자정에 실행
  public void getFile(){
    processFiles();
  }

  private void processFiles(){
    String filePath = "input/";
    File dir = new File(filePath);
    if(dir.isDirectory()){
      File[] files = dir.listFiles();
      for(File file : files){
        if(file.getName().endsWith(ConstVO.suffixForTxt)){
          schedulerService.readTxtFile(file); //txt File
        }else if(file.getName().endsWith(ConstVO.suffixForCsv)){
          schedulerService.readCsvFile(file); // csv File
        }
      }
    }
  }

}
