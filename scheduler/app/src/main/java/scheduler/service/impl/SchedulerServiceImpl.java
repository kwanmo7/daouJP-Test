package scheduler.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import scheduler.model.Record;
import scheduler.mapper.SchedulerMapper;
import scheduler.service.SchedulerService;
import scheduler.util.RecordUtil;
import scheduler.vo.ConstVO;

@RequiredArgsConstructor
@Service
public class SchedulerServiceImpl implements SchedulerService {

  private final SchedulerMapper schedulerMapper;

  // 날짜 형식 변환 포매터
  private static final DateTimeFormatter DATE_TIME_FORMATTER =
                                DateTimeFormatter.ofPattern("yyyy-MM-dd HH");

  // table insert
  @Override
  public void insertRecord(Record record) {
    schedulerMapper.insertRecord(record);
  }

  // txt 파일 read
  @Override
  public void readTxtFile(File file) {
    try(BufferedReader br = new BufferedReader(new FileReader(file.getAbsoluteFile()))){
      String line = "";
      while((line = br.readLine()) != null){
        processRecordForTxt(line.split(ConstVO.textFileDelimiter));
      }
    }catch (Exception e){
      // 오류 발생시 insert 하지 않고 다음 파일로 넘어감
      e.printStackTrace();
    }
  }

  // csv 파일 read
  @Override
  public void readCsvFile(File file) {
    try(BufferedReader br = new BufferedReader(new FileReader(file.getAbsoluteFile()))){
      CSVParser csvParser = new CSVParser(br,
                            CSVFormat.DEFAULT.withDelimiter(ConstVO.csvFileDelimiter));
      for(CSVRecord csvRecord : csvParser){
        processRecordForCsv(csvRecord);
      }
    }catch (IOException e){
      // 오류 발생시 insert 하지 않고 다음 파일로 넘어감
      e.printStackTrace();
    }
  }

  // txt 파일 레코드 처리
  private void processRecordForTxt(String[] input){
    try{
      Record record = RecordUtil.createRecord(input, DATE_TIME_FORMATTER);
      if(record != null){
        insertRecord(record);
      }
    }catch (Exception e){
      // 오류 발생시 insert 하지 않고 다음 파일로 넘어감
      e.printStackTrace();
    }
  }

  // csv 파일 레코드 처리
  private void processRecordForCsv(CSVRecord csvRecord){
    try{
      Record record = RecordUtil.createRecord(csvRecord, DATE_TIME_FORMATTER);
      if(record != null){
        insertRecord(record);
      }
    }catch (Exception e){
      // 오류 발생시 insert 하지 않고 다음 파일로 넘어감
      e.printStackTrace();
    }
  }
}
