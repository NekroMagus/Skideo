package net.skideo.controller.exception;

import net.skideo.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class AcademyExceptionController {

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<?> notFound() {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Not found");
    }
}