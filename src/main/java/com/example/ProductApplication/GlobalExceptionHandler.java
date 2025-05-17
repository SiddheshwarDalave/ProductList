//package com.example.ProductApplication;
//
//import com.example.ProductApplication.exception.SampleException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<String> handleNotFound(ResourceNotFoundException ex) {
//        return new ResponseEntity<>(" Id not found in the database "+ ex.getMessage(), HttpStatus.NOT_FOUND);
//    }
//
// //Optional: handle other exceptions
//@ExceptionHandler(Exception.class)
//public ResponseEntity<String> handleGeneral(Exception ex) {
//    return new ResponseEntity<>("Internal server error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//}
//@ExceptionHandler(SampleException.class)
//    public ResponseEntity<String> handleError(SampleException ex){
//        return new ResponseEntity<>("This is an sample exception"+ex.getMessage(),HttpStatus.ALREADY_REPORTED);
//}
//}