package scheduler.vo;

import java.io.Serializable;
import lombok.Data;

@Data
public class Record implements Serializable {

  private static final long serialVersionUID = 100L;

  private Long id;
  private String time;
  private int subscribers;
  private int dropouts;
  private int paymentAmount;
  private int amountUsed;
  private int salesAmount;

}
