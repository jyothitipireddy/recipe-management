package com.recipe.recipemanagement.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * @author Jyothi Tipireddy
 * Gloabl exceptions handler- this class seperate the exceptions code from business logic
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private final Logger logger = LogManager.getLogger();

    public GlobalExceptionHandler() {
    }

    /**
     * This method handles EntityNotFoundException
     *
     * @param request HttpServletRequest
     * @param ex      EntityNotFoundException
     * @return Exception response
     */
    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<ExceptionResponse> handleNotFoundException(HttpServletRequest request, EntityNotFoundException ex) {
        logger.error("EntityNotFoundException {} {}", request.getMethod(), request.getRequestURI());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ExceptionResponse(HttpStatus.NOT_FOUND.value(), request.getRequestURI(),
                        HttpStatus.NOT_FOUND.getReasonPhrase(), LocalDateTime.now(), ex.getMessage()));

    }

    /**
     * This method handles EmptyResultDataAccessException
     *
     * @param request HttpServletRequest
     * @param ex      EmptyResultDataAccessException
     * @return Exception response
     */
    @ExceptionHandler({EmptyResultDataAccessException.class})
    public ResponseEntity<ExceptionResponse> emptyResultDataAccessException(HttpServletRequest request, EmptyResultDataAccessException ex) {
        logger.error("EmptyResultDataAccessException {} {}", request.getMethod(), request.getRequestURI());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ExceptionResponse(HttpStatus.NOT_FOUND.value(), request.getRequestURI(),
                        HttpStatus.NOT_FOUND.getReasonPhrase(), LocalDateTime.now(), ex.getMessage()));

    }

    /**
     * This method handles PersistenceException
     *
     * @param request HttpServletRequest
     * @param ex      PersistenceException
     * @return Exception response
     */
    @ExceptionHandler({PersistenceException.class})
    public ResponseEntity<ExceptionResponse> persistenceException(HttpServletRequest request, PersistenceException ex) {
        logger.error("PersistenceException {} {}", request.getMethod(), request.getRequestURI());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), request.getRequestURI(),
                        HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), LocalDateTime.now(), ex.getMessage()));

    }

}
