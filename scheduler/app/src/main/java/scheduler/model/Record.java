package scheduler.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Record implements Serializable {

  private static final long serialVersionUID = 100L;

  private LocalDateTime time;
  private int subscribers;
  private int dropouts;
  private int paymentAmount;
  private int amountUsed;
  private int salesAmount;

}
