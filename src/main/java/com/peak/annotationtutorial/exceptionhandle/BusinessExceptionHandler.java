package com.peak.annotationtutorial.exceptionhandle;

import com.peak.annotationtutorial.locale.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class BusinessExceptionHandler {

    @Autowired
    ErrorMessage errorMessage ;

    @ExceptionHandler({RuntimeException.class})
    @ResponseBody
    //public ResponseEntity<List> processUnmergeException(final MethodArgumentNotValidException ex) {
    public ResponseEntity<List> customerException(final RuntimeException ex){

        String error = ErrorMessage.toLocale(ex.getMessage()) ;
        return new ResponseEntity<>((new ArrayList<String>(Arrays.asList(error))), HttpStatus.BAD_REQUEST);
    }

}
