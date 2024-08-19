package restfulapi.dto;


import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ApiKey {
  private Long id;
  private String clientName;
  private String apiKey;
  private LocalDateTime createdTime;

}
