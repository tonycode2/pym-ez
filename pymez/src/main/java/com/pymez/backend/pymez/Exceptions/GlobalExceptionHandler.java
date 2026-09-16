package com.pymez.backend.pymez.Exceptions;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.pymez.backend.pymez.DTOs.Response.ErrorResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * This will catch every error of the class ItemsException. The function will
     * return a JSON with the exception in a formatted form
     * 
     * @param ex
     * @see ErrorResponseDto
     * @see ItemsException
     * @return ResponseEntity<ErrorResponseDto>
     */
    @ExceptionHandler(ItemsException.class)
    public ResponseEntity<ErrorResponseDto> handleItemsExceptions(ItemsException ex) {
        ErrorResponseDto errorRespose = new ErrorResponseDto(HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(), ex.getMessage());

        return new ResponseEntity<>(errorRespose, HttpStatus.NOT_FOUND);
    }

    /**
     * This will catch every error of the class MethodArgumentNotValidException. The
     * function will
     * return a JSON with the exception in a formatted form
     * 
     * @param ex
     * @see ErrorResponseDto
     * @see MethodArgumentNotValidException
     * @return ResponseEntity<ErrorResponseDto>
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> "The field '" + error.getField() + "' " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        ErrorResponseDto errorResponse = new ErrorResponseDto(HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(), errorMessage);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * This will catch every error of the class
     * MethodArgumentTypeMissmatchException. The
     * function will
     * return a JSON with the exception in a formatted form
     * 
     * @param ex
     * @see ErrorResponseDto
     * @see MethodArgumentTypeMismatchException
     * @return ResponseEntity<ErrorResponseDto>
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponseDto> handleTypeMissmatch(MethodArgumentTypeMismatchException ex) {

        String expectedType = ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "Unkown";

        ErrorResponseDto errorResponse = new ErrorResponseDto(HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "The field '" + ex.getName() + "' must be of typy " + expectedType);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * This will catch every error of the class
     * HttpMessageNotReadableException. The
     * function will
     * return a JSON with the exception in a formatted form
     * 
     * @param ex
     * @see ErrorResponseDto
     * @see HttpMessageNotReadableException
     * @return ResponseEntity<ErrorResponseDto>
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDto> handleMessageNotReadable(HttpMessageNotReadableException ex) {
        ErrorResponseDto errorResponse = new ErrorResponseDto(HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "The body of the API request (JSON) is not legible or is malformed");

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
