package com.library.library_system.exception;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidBook.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, String> handleInvalidISBN(InvalidBook ex){
        return Map.of("error", ex.getMessage());
    }
    @ExceptionHandler(LibraryFullException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public Map<String, String> handleLibraryFull(LibraryFullException ex) {
        return Map.of("error", ex.getMessage());
    }
    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public Map<String, String> handleBookNotFound(BookNotFoundException ex){
        return Map.of("error", ex.getMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, String> handleValidation(MethodArgumentNotValidException ex) {
        return Map.of("error", "Validation failed: " + ex.getBindingResult().getFieldError().getDefaultMessage());
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Map<String, String> handleGenericException(Exception ex) {
        return Map.of("error", "An unexpected error occurred: " + ex.getMessage());
    }

}
