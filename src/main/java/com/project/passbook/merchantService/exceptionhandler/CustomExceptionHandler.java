package com.project.passbook.merchantService.exceptionhandler;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.project.passbook.merchantService.model.codes.ErrorCode;
import com.project.passbook.merchantService.model.exceptions.types.ConflictException;
import com.project.passbook.merchantService.model.exceptions.types.NotFoundException;
import com.project.passbook.merchantService.model.responses.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(NotFoundException.class)
  public final ResponseEntity<Response> handleMerchantNotFoundExceptions(
      Exception ex, WebRequest request) {
    String errorMessage = ex.getMessage();
    Response error = new Response(ErrorCode.NOT_FOUND, errorMessage, null);

    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ConflictException.class)
  public final ResponseEntity<Response> handleMerchantConflictExceptions(
      Exception ex, WebRequest request) {
    String errorMessage = ex.getMessage();
    Response error = new Response(ErrorCode.NAMING_CONFLICT,
                                  errorMessage,
                                  null);

    return new ResponseEntity<>(error,
                                HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(JsonMappingException.class)
  public
  ResponseEntity<String>handleJsonMappingException(JsonMappingException e){
    return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
  }
}
