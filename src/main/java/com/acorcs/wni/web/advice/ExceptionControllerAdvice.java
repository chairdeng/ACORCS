package com.acorcs.wni.web.advice;

import com.acorcs.wni.web.advice.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

/**
 * Created by dengc on 2017/2/17.
 */
@ControllerAdvice
public class ExceptionControllerAdvice {
    @ResponseBody
    @ResponseStatus(value= HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ExceptionResponse handleDateFormatException(MethodArgumentTypeMismatchException ex,HttpServletRequest request){

        ExceptionResponse response = new ExceptionResponse();
        response.setCode("0002");
        response.setMessage("参数：["+ex.getParameter().getParameterName()+"]，格式不正确。"+request.getParameter(ex.getParameter().getParameterName()));
        return response;
    }
    @ResponseBody
    @ResponseStatus(value= HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ExceptionResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request){
        ExceptionResponse response = new ExceptionResponse();
        response.setMessage(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        response.setCode("0001");
        return response;
    }
    @ResponseBody
    @ResponseStatus(value= HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ConstraintViolationException.class})
    public ExceptionResponse handleConstraintViolationException(ConstraintViolationException ex){
        ExceptionResponse response = new ExceptionResponse();
        response.setMessage(ex.getConstraintViolations().iterator().next().getMessage());
        response.setCode("0001");
        return response;
    }
    @ResponseBody
    @ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ExceptionResponse handleException(Exception ex){
        ExceptionResponse response = new ExceptionResponse();
        response.setCode("9999");
        response.setMessage("未知错误");
        return response;
    }
}
