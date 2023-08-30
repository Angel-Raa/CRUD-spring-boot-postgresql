package org.caja.ideal.shoppingcart.exeptions;

import io.jsonwebtoken.ExpiredJwtException;
import org.caja.ideal.shoppingcart.api.ApiResponseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<ApiResponseException> usernameAlreadyExistsException(UsernameAlreadyExistsException exception) {
        ApiResponseException apiResponseException = new ApiResponseException(exception.getMessage(), exception.getCode(), exception.getHttp());
        return ResponseEntity.status(exception.getHttp()).body(apiResponseException);
    }

    @ExceptionHandler(NotFoundProductExeptions.class)
    public ResponseEntity<ApiResponseException> notFoundProductExeptionsException(NotFoundProductExeptions productExeptions) {
        ApiResponseException apiResponseException = new ApiResponseException(productExeptions.getMessage(), productExeptions.getCode(), productExeptions.getHttp());
        return ResponseEntity.status(productExeptions.getHttp()).body(apiResponseException);
    }
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ApiResponseException> apiResponseException(ExpiredJwtException exception) {
        ApiResponseException apiResponseException = new ApiResponseException(exception.getMessage(), HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(apiResponseException.getHttp()).body(apiResponseException);
    }

    @ExceptionHandler(InvalidProductExeptions.class)
    public ResponseEntity<ApiResponseException> invalidProductEx( InvalidProductExeptions exeptions){
        ApiResponseException apiResponseException = new ApiResponseException(exeptions.getMessage(), exeptions.getCode(), exeptions.getHttp());
        return ResponseEntity.status(exeptions.getHttp()).body(apiResponseException);
    }
}
