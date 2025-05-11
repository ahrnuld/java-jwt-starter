package nl.inholland.javajwtstarter.controllers;

import nl.inholland.javajwtstarter.models.DTO.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionAdvice {

        @ExceptionHandler(value = {Exception.class})
        public ResponseEntity<ErrorDTO> genericException(Exception ex, WebRequest request) {
            ErrorDTO error = new ErrorDTO(ex.getMessage());

            return new ResponseEntity<ErrorDTO>(error, HttpStatus.BAD_REQUEST);
        }
}
