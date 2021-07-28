package br.com.desafio.errors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppHandlerException extends ResponseEntityExceptionHandler {
  @ExceptionHandler(value={Exception.class})
  public ResponseEntity<Object> handleExceptions(Exception e, WebRequest request) {
    
    String errorDescription = e.getLocalizedMessage();

    if(errorDescription == null)
      errorDescription = e.toString();

    ErrorMessage errorMessage = new ErrorMessage(errorDescription);

    return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
