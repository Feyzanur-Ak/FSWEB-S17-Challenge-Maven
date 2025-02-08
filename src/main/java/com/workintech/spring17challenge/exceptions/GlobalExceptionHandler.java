package com.workintech.spring17challenge.exceptions;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    private ResponseEntity<ApiErrorResponse> CourseHandlerException (ApiException apiException) {
        ApiErrorResponse errorResponse =new ApiErrorResponse(apiException.getStatus().value(),apiException.getMessage(),System.currentTimeMillis());
        return  new ResponseEntity<>(errorResponse,apiException.getStatus());
    }


    @ExceptionHandler
    private  ResponseEntity<ApiErrorResponse> handlerException(Exception exception) {
        ApiErrorResponse errorResponse=new ApiErrorResponse(HttpStatus.CREATED.value(), exception.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse,HttpStatus.CREATED);
    }



}