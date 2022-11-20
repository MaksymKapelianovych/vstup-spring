package ua.vstup.domain;


import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@Getter
@Setter
public class ErrorResponse {
    // customizing timestamp serialization format

    private int code;

    private String status;

    private String message;

    private String stackTrace;

    public ErrorResponse(
            HttpStatus httpStatus,
            String message
    ) {
        this.code = httpStatus.value();
        this.status = httpStatus.name();
        this.message = message;
    }

    public ErrorResponse(
            HttpStatus httpStatus,
            String message,
            String stackTrace
    ) {
        this(
                httpStatus,
                message
        );

        this.stackTrace = stackTrace;
    }
}
