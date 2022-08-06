package com.mySpring.Web;

import com.mySpring.boot.ResponseEntility;
import com.mySpring.boot.ResponseFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestControllerAdvice
public class ExceptionHander {
    @ExceptionHandler(Exception.class)
    public ResponseEntility<String> handerNull(Exception e){
        e.printStackTrace();
        return ResponseFactory.getDeResponseEntility("操作失败");

    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntility<Map<String,String>> handerValid(MethodArgumentNotValidException result){

            Map<String,String> map=new HashMap<>();
            List<FieldError> errors = result.getFieldErrors();
            errors.forEach(e -> {
                map.put(e.getField(),e.getDefaultMessage());
            });
            return ResponseFactory.getDeResponseEntility(map);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntility<Map<String,String>> handerConstra(ConstraintViolationException result){

        Map<String,String> map=new HashMap<>();
        Set<ConstraintViolation<?>> errors = result.getConstraintViolations();
        errors.forEach(e -> {
            map.put(e.getPropertyPath().toString(),e.getMessage());
        });
        return ResponseFactory.getDeResponseEntility(map);
    }
    @ExceptionHandler(BindException.class)
    public ResponseEntility<Map<String,String>> handernew(BindException result){
        Map<String,String> map=new HashMap<>();
        List<FieldError> errors = result.getFieldErrors();
        errors.forEach(e -> {
            map.put(e.getField(),e.getDefaultMessage());
        });
        return ResponseFactory.getDeResponseEntility(map);
    }
}
