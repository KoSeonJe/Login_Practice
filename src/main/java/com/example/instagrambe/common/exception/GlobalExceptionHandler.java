package com.example.instagrambe.common.exception;

import com.example.instagrambe.common.exception.custom.DuplicatedException;
import com.example.instagrambe.common.exception.custom.JwtValidationException;
import com.example.instagrambe.common.support.error.ErrorType;
import com.example.instagrambe.common.support.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(JwtValidationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ApiResponse<String> jwtValidationExceptionHandler(JwtValidationException e) {
    return ApiResponse.of(ErrorType.JWT_VALID_EXCEPTION, e.getMessage());
  }

  @ExceptionHandler(AuthenticationServiceException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ApiResponse<String> authenticationServiceException(AuthenticationException e) {
    return ApiResponse.of(ErrorType.AUTHENTICATION_EXCEPTION, e.getMessage());
  }

  @ExceptionHandler(AuthenticationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ApiResponse<String> authenticationException(AuthenticationException e) {
    return ApiResponse.of(ErrorType.AUTHENTICATION_EXCEPTION, e.getMessage());
  }

  @ExceptionHandler(IllegalArgumentException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ApiResponse<String> illegalArgumentException(IllegalArgumentException e) {
    return ApiResponse.of(ErrorType.ILLEGAL_VALUE, e.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ApiResponse<String> processValidationError(MethodArgumentNotValidException exception) {
    String exceptionMessage = createExceptionMessage(exception);
    return ApiResponse.of(ErrorType.ILLEGAL_VALUE, exceptionMessage);
  }

  @ExceptionHandler(DuplicatedException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ApiResponse<String> duplicatedException(DuplicatedException exception) {
    return ApiResponse.of(ErrorType.DUPLICATE_EMAIL, exception.getMessage());
  }

  private String createExceptionMessage(MethodArgumentNotValidException exception) {
    BindingResult bindingResult = exception.getBindingResult();
    StringBuilder exceptionMessageBuilder = new StringBuilder();
    for (FieldError fieldError : bindingResult.getFieldErrors()) {
      exceptionMessageBuilder.append("[");
      exceptionMessageBuilder.append(fieldError.getField());
      exceptionMessageBuilder.append("](은)는 ");
      exceptionMessageBuilder.append(fieldError.getDefaultMessage());
      exceptionMessageBuilder.append(" 입력된 값: [");
      exceptionMessageBuilder.append(fieldError.getRejectedValue());
      exceptionMessageBuilder.append("]");
    }
    return exceptionMessageBuilder.toString();
  }
}
