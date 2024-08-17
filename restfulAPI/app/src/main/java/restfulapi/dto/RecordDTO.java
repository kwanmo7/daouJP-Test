package restfulapi.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Data;

@Data
public class RecordDTO implements Serializable {

  private static final long serialVersionUID = 100L;

  private Long id;
  private LocalDateTime time;
  private int subscribers;
  private int dropouts;
  private int paymentAmount;
  private int amountUsed;
  private int salesAmount;
  private String formattedTime; // yyyy-MM-dd HH 형태의 시간

  public void setFormattedTime(){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH");
    this.formattedTime =  time.format(formatter);
  }
}
