package scheduler.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.commons.csv.CSVRecord;
import scheduler.model.Record;

public class RecordUtil {
  public static Record createRecord(String[] input, DateTimeFormatter formatter){
    Record record = new Record();
    try{
      record.setTime(LocalDateTime.parse(input[0], formatter));
      record.setSubscribers(Integer.parseInt(input[1]));
      record.setDropouts(Integer.parseInt(input[2]));
      record.setPaymentAmount(Integer.parseInt(input[3].replace(",","")));
      record.setAmountUsed(Integer.parseInt(input[4].replace(",","")));
      record.setSalesAmount(Integer.parseInt(input[5].replace(",","")));
    }catch (Exception e){
      return null; // 형변환 중 오류 발생시 null로 return
    }
    return record;
  }

  public static Record createRecord(CSVRecord csvRecord, DateTimeFormatter formatter){
    Record record = new Record();
    try{
      record.setTime(LocalDateTime.parse(csvRecord.get(0), formatter));
      record.setSubscribers(Integer.parseInt(csvRecord.get(1)));
      record.setDropouts(Integer.parseInt(csvRecord.get(2)));
      record.setPaymentAmount(Integer.parseInt(csvRecord.get(3).replace(",","")));
      record.setAmountUsed(Integer.parseInt(csvRecord.get(4).replace(",","")));
      record.setSalesAmount(Integer.parseInt(csvRecord.get(5).replace(",","")));
    }catch (Exception e){
      return null; // 형변환 중 오류 발생시 null로 return
    }
    return record;
  }
}
