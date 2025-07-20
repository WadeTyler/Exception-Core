package net.tylerwade.exceptioncore;

import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

/**
 * Global exception handler for handling various exceptions across the application.
 * This interface uses Spring's @RestControllerAdvice to provide a centralized way to handle exceptions.
 */
@RestControllerAdvice
public interface GlobalExceptionHandler {

    /**
     * Handles HttpRequestException and returns a structured error response.
     *
     * @param ex the HttpRequestException to handle
     * @return a ResponseEntity containing the error response
     */
    @ExceptionHandler(HttpRequestException.class)
    default ResponseEntity<ErrorResponse> handleHttpRequestException(HttpRequestException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getHttpStatus().getReasonPhrase(),
                ex.getMessage(),
                ex.getHttpStatus().value(),
                Instant.now().toString()
        );

        return new ResponseEntity<>(errorResponse, ex.getHttpStatus());
    }

    /**
     * Handles IllegalArgumentException and returns a structured error response.
     *
     * @param ex the IllegalArgumentException to handle
     * @return an ErrorResponse with a BAD_REQUEST status
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    default ErrorResponse handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ErrorResponse(
                "Bad Request",
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                Instant.now().toString()
        );
    }


    /**
     * Handles MethodArgumentNotValidException and returns a structured error response.
     *
     * @param ex the MethodArgumentNotValidException to handle
     * @return an ErrorResponse with a BAD_REQUEST status
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    default ErrorResponse handleValidationException(MethodArgumentNotValidException ex) {
        StringBuilder errorMessage = new StringBuilder("Validation failed: ");
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errorMessage.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append("; ")
        );

        return new ErrorResponse(
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                errorMessage.toString(),
                HttpStatus.BAD_REQUEST.value(),
                Instant.now().toString()
        );
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    default ErrorResponse handleValidationException(ValidationException ex) {
        return new ErrorResponse(
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                Instant.now().toString()
        );
    }

    /**
     * Handles generic exceptions and returns a structured error response.
     *
     * @param ex the Exception to handle
     * @return an ErrorResponse with an INTERNAL_SERVER_ERROR status
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    default ErrorResponse handleGenericException(Exception ex) {
        return new ErrorResponse(
                "Internal Server Error",
                "An unexpected error occurred: " + ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                String.valueOf(System.currentTimeMillis())
        );
    }

}
