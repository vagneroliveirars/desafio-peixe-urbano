package br.com.peixeurbano.deals.controller;

import br.com.peixeurbano.deals.dto.ErrorResponseDTO;
import br.com.peixeurbano.deals.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> notFoundExceptionHandler(NotFoundException exception,
                                                                     ServerHttpRequest httpRequest) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(buildErrorResponseDTO(httpRequest, HttpStatus.NOT_FOUND, exception.getMessage()));
    }

    private ErrorResponseDTO buildErrorResponseDTO(ServerHttpRequest httpRequest,
                                                   HttpStatus status,
                                                   String message) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO();
        errorResponse.setTimestamp(ZonedDateTime.now());
        errorResponse.setPath(httpRequest.getURI().getRawPath());
        errorResponse.setStatus(status.value());
        errorResponse.setError(status.getReasonPhrase());
        errorResponse.setMessage(message);
        return errorResponse;
    }

}