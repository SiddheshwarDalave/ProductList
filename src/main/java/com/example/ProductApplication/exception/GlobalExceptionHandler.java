package com.example.ProductApplication.exception;

import com.example.ProductApplication.dto.ExceptionResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.lang.model.element.NestingKind;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {


    //global exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDTO> handleCategoryAlreadyExistsException(Exception ex, WebRequest webRequest){
        ExceptionResponseDTO exceptionResponseDTO=new ExceptionResponseDTO(
                webRequest.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionResponseDTO);
    }
//Category Section
    @ExceptionHandler(CategoryAlreadyExistsException.class)
    // Advanced way to showing exception- Show the API, time , error and status detail // Can add more detail if required
    public ResponseEntity<ExceptionResponseDTO> handleCategoryAlreadyExistsException(CategoryAlreadyExistsException ex, WebRequest webRequest){
        ExceptionResponseDTO exceptionResponseDTO=new ExceptionResponseDTO(
                webRequest.getDescription(false),
                HttpStatus.CONFLICT,
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionResponseDTO);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    //Normal way-Return the string error message only
    public ResponseEntity<String> handleCategoryNotFoundException(CategoryNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }


    @ExceptionHandler(CategoryListIsEmptyException.class)
    //Normal Way
//    public ResponseEntity<String> handleCategoryListIsEmptyException(CategoryListIsEmptyException ex){
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    //Advanced Way
    public ResponseEntity<ExceptionResponseDTO> handleCategoryListIsEmptyException(CategoryListIsEmptyException ex,WebRequest webRequest){
        ExceptionResponseDTO exceptionResponseDTO=new ExceptionResponseDTO(
                webRequest.getDescription(true),
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                LocalDateTime.now()

        );
        return new ResponseEntity<>(exceptionResponseDTO,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex){
        return  ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }


    // Product Section
}
