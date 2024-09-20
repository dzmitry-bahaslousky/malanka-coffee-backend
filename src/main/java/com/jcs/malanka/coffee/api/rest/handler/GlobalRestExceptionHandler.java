package com.jcs.malanka.coffee.api.rest.handler;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    ResponseEntity<ProblemDetail> handleEntityNotFoundException(
            EntityNotFoundException ex,
            WebRequest webRequest) {
        log.error(ex.getMessage(), ex);
        ProblemDetail problemDetail = createProblemDetail(ex, HttpStatus.NOT_FOUND, ex.getMessage(), null, null, webRequest);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problemDetail);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status,
            @NonNull WebRequest request) {
        String errMsg = Optional.ofNullable(ex.getDetailMessageArguments()).stream()
                .flatMap(Arrays::stream)
                .map(Object::toString)
                .collect(Collectors.joining());

        ProblemDetail problemDetail = createProblemDetail(ex, status, errMsg, null, null, request);
        return ResponseEntity.status(status).body(problemDetail);
    }

}
