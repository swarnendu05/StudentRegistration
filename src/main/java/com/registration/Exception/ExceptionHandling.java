package com.registration.Exception;


import com.registration.patload.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.io.FileNotFoundException;
import java.util.Date;

@ControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorDto> ResourceNotFound(
            ResourceNotFound r,
            WebRequest req

    ){
        ErrorDto dto = new ErrorDto();
        dto.setDate(new Date());
        dto.setMessage(r.getMessage());
        dto.setRequest(req.getDescription(false) );
        return  new ResponseEntity<>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleFileNotFound(
            Exception r,
            WebRequest req

    ){
        ErrorDto dto = new ErrorDto();
        dto.setDate(new Date());
        dto.setMessage("not found in it"+r.getMessage());
        dto.setRequest(req.getDescription(false) );
        return  new ResponseEntity<>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler (DuplicateRegistration.class)
    public ResponseEntity<ErrorDto> DuplicateRegistration(
            DuplicateRegistration dr,
            WebRequest req

    ){
        ErrorDto dto = new ErrorDto();
        dto.setDate(new Date());
        dto.setMessage(dr.getMessage());
        dto.setRequest(req.getDescription(false) );
        return  new ResponseEntity<>(dto, HttpStatus.CONFLICT);
    }
}
