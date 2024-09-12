package com.jcs.malanka.coffee.api.rest.handler;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    ResponseEntity<ProblemDetail> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest webRequest) {
        ProblemDetail problemDetail = createProblemDetail(ex, HttpStatus.NOT_FOUND, "Not found", null, null, webRequest);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problemDetail);
    }

}
