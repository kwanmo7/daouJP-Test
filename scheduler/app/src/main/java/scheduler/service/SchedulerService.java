package scheduler.service;

import java.io.File;
import scheduler.model.Record;

public interface SchedulerService {
  void insertRecord(Record record);

  void readTxtFile(File file);

  void readCsvFile(File file);
}
