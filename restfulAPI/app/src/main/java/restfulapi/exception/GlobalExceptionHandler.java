package restfulapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex){
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setMessage("Validation failed");
    errorResponse.setDetail(ex.getBindingResult().toString());
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(InvalidFormatException.class)
  public ResponseEntity<ErrorResponse> handleInvalidFormatException(InvalidFormatException ex){
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setMessage("Invalid Format");
    errorResponse.setDetail(ex.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }
}
