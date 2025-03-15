package service.accounts.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;
import service.accounts.exception.ApiRestExternalException;
import service.accounts.exception.BusinessException;
import service.accounts.model.ErrorDto;

import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<ErrorDto> handleValidationExceptions(WebExchangeBindException ex) {
        ErrorDto error = new ErrorDto(
                HttpStatus.BAD_REQUEST.value(),
                "Parametros enviados son incorrectos",
                Objects.requireNonNull(ex.getFieldError())
                        .getField()
                        .concat(": ")
                        .concat(Objects.requireNonNull(ex.getFieldError().getDefaultMessage())),
                null
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ApiRestExternalException.class)
    public Mono<ResponseEntity<ErrorDto>> handleCustomException(ApiRestExternalException ex) {
        ErrorDto errorDto = new ErrorDto(
                ex.getStatus().value(),
                ex.getMessage(),
                ex.getDetail(),
                ex.getUrl()
        );
        return Mono.just(ResponseEntity.status(ex.getStatus()).body(errorDto));
    }

    @ExceptionHandler(BusinessException.class)
    public Mono<ResponseEntity<ErrorDto>> businessException(BusinessException ex) {
        ErrorDto errorDto = new ErrorDto(
                HttpStatus.PARTIAL_CONTENT.value(),
                ex.getOperation(),
                ex.getMessage(),
                null
        );
        return Mono.just(ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body(errorDto));
    }
}
