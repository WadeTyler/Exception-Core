package net.tylerwade.exceptioncore;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class HttpRequestException extends RuntimeException {

    private final HttpStatus httpStatus;

    public HttpRequestException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpRequestException(String message, Throwable cause, HttpStatus httpStatus) {
        super(message, cause);
        this.httpStatus = httpStatus;
    }

    public HttpRequestException(Throwable cause, HttpStatus httpStatus) {
        super(cause);
        this.httpStatus = httpStatus;
    }

    public HttpRequestException(HttpStatus httpStatus) {
        super(httpStatus.getReasonPhrase());
        this.httpStatus = httpStatus;
    }

    public HttpRequestException(String message) {
        super(message);
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR; // Default to 500 if not specified
    }
}
