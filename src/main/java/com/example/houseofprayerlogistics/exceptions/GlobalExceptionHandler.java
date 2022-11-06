package com.example.houseofprayerlogistics.exceptions;


import com.example.houseofprayerlogistics.domain.ApiError;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
  public final ResponseEntity<ApiError> handleException(Exception ex, WebRequest request) {
    HttpHeaders headers = new HttpHeaders();

    if (ex instanceof MethodArgumentNotValidException) {
      HttpStatus status = HttpStatus.BAD_REQUEST;
      MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) ex;
      return handleMethodArgumentNotValidException(methodArgumentNotValidException, headers, status, request);

    }else if(ex instanceof HttpMessageNotReadableException){
      HttpStatus status = HttpStatus.BAD_REQUEST;
      HttpMessageNotReadableException httpMessageNotReadableException = (HttpMessageNotReadableException) ex;
      return handleHttpMessageNotReadableException(httpMessageNotReadableException, headers, status, request);
    }

    return null;
  }

  protected ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
                                                                          HttpHeaders headers,
                                                                          HttpStatus status, WebRequest request) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach( error -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });
    return handleExceptionInternal(ex, new ApiError(errors), headers, status, request);
  }

  protected ResponseEntity<ApiError> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex, HttpHeaders headers,
                                                                            HttpStatus status, WebRequest request) {
    List<String> errors = new ArrayList<>();
    errors.add(ex.getCause().getLocalizedMessage());
    return handleExceptionInternal(ex, new ApiError(errors), headers, status, request);
  }

  protected ResponseEntity<ApiError> handleExceptionInternal(Exception ex, ApiError body,
                                                             HttpHeaders headers, HttpStatus status,
                                                             WebRequest request) {
    if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
      request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
    }
    return new ResponseEntity<>(body, headers, status);
  }
}
