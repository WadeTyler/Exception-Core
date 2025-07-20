package net.tylerwade.exceptioncore;

public record ErrorResponse(
        String error,
        String message,
        int status,
        String timestamp
) {
}
