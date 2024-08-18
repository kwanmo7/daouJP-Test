package restfulapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class RecordDTO implements Serializable {

  private static final long serialVersionUID = 100L;

  private static final DateTimeFormatter DATE_TIME_FORMATTER =
                                              DateTimeFormatter.ofPattern("yyyy-MM-dd HH");

  private Long id;
  private LocalDateTime time;
  private int subscribers;
  private int dropouts;
  private int paymentAmount;
  private int amountUsed;
  private int salesAmount;
  private String formattedTime; // yyyy-MM-dd HH 형태의 시간

  public void setFormattedTime(){
    this.formattedTime =  time.format(DATE_TIME_FORMATTER);
  }

  public void setTimeFromFormattedTime(){
    this.time = LocalDateTime.parse(formattedTime, DATE_TIME_FORMATTER);
  }
}
