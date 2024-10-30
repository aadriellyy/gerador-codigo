package br.jus.tse.administrativa.exceptions;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.NoSuchElementException;

@Order(-1)
@ControllerAdvice
public class GlobalDefaultExceptionHandler  extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ExceptionResponse> handleSqlIntegrityException(HttpServletRequest req, SQLIntegrityConstraintViolationException ex){
        String error = "Falha de integridade de dados: " + ex.getMessage();
        LOGGER.error(error, ex);
        return buildResponseEntity(new ExceptionResponse(HttpStatus.BAD_REQUEST, error));
    }
    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<ExceptionResponse> handleDuplicateKeyException(HttpServletRequest req, DuplicateKeyException ex){
        ExceptionResponse response = new ExceptionResponse(HttpStatus.CONFLICT);

        response.setMessage("Esse Objeto Já existe: " + req.getRequestURI());
        LOGGER.error(response.getMessage(), ex);
        return buildResponseEntity(response);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ExceptionResponse> handleNoSuchElementException(HttpServletRequest req, NoSuchElementException ex){
        ExceptionResponse response = new ExceptionResponse(HttpStatus.NOT_FOUND);

        response.setMessage("Dados Não encontrados: " + req.getRequestURI());
        LOGGER.error(response.getMessage(), ex);
        return buildResponseEntity(response);
    }
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<ExceptionResponse> handleEmptyResultDataAccessException(HttpServletRequest req,EmptyResultDataAccessException ex){
        ExceptionResponse response = new ExceptionResponse(HttpStatus.NOT_FOUND);

        response.setMessage("Dados Não encontrados: " + req.getRequestURI());
        LOGGER.error(response.getMessage(), ex);
        return buildResponseEntity(response);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionResponse> handleCustomException(HttpServletRequest req,CustomException ex){
        ExceptionResponse response = new ExceptionResponse(HttpStatus.BAD_REQUEST);

        response.setMessage("Erro interno: " + req.getRequestURI());
        LOGGER.error(response.getMessage(), ex);
        return buildResponseEntity(response);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionResponse> handleDataIntegrityViolationException(HttpServletRequest req,DataIntegrityViolationException ex){
        ExceptionResponse response = new ExceptionResponse(HttpStatus.BAD_REQUEST);

        response.setMessage("Uma violação foi relatada: " + req.getRequestURI());
        LOGGER.error(response.getMessage(), ex);
        return buildResponseEntity(response);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(HttpServletRequest req,Exception ex){
        ExceptionResponse response = new ExceptionResponse(HttpStatus.BAD_REQUEST);

        response.setMessage("Uma violação foi relatada: " + req.getRequestURI());
        LOGGER.error("Erro inesperado em: " + req.getRequestURI(), ex);
        return buildResponseEntity(response);
    }

    private ResponseEntity<ExceptionResponse> buildResponseEntity(ExceptionResponse errorResponse){
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }
}