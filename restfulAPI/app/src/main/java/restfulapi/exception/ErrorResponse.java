package restfulapi.exception;

import lombok.Data;

@Data
public class ErrorResponse {
  private String message;
  private String detail;
}
