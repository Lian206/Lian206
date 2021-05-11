package com.lym.small.exceptions;
import com.lym.small.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

/**
 * @desc: 异常处理
 * @author: Lian
 * @time: 2021/5/10 17:15
 */
@RestControllerAdvice
public class ExceptionHandle {

    private final static Logger logger= LoggerFactory.getLogger(ExceptionHandle.class);

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> exceptionHandler(MethodArgumentNotValidException e)
    {
        return ResultUtil.resultResp("500",e.getBindingResult().getFieldError().getDefaultMessage());
    }

}
