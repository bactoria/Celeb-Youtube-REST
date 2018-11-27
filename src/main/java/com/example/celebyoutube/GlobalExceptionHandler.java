package com.example.celebyoutube;

import com.example.celebyoutube.exceptions.ChannelNotFoundException;
import lombok.Getter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // 빈등록
public class GlobalExceptionHandler {

    @ExceptionHandler(ChannelNotFoundException.class)
    public void todoNotFoundException(ChannelNotFoundException e) {
        System.out.println(e.getMessage());
        System.out.println(e.getLocalizedMessage());
        System.out.println(e.getCause());
    }
/*
    @ExceptionHandler
    public ResponseEntity<String> asd(MethodArgumentNotValidException e) {

        ErrorDetails errorDetails = new ErrorDetails(e.getBindingResult().getFieldError().getField(), e.getBindingResult().getFieldError().getDefaultMessage());

        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }*/

    @Getter
    private class ErrorDetails {

        private String field;
        private String message;

        public ErrorDetails(String field, String message) {
            this.field = field;
            this.message = message;
        }

        @Override
        public String toString() {
            return "ErrorDetails{" +
                    "errorField='" + field + '\'' +
                    ", message='" + message + '\'' +
                    '}';
        }
    }

}