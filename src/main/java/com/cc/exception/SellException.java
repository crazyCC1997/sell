package com.cc.exception;

import com.cc.enums.ResultEnum;

/**
 * 异常处理类
 * @author cchen
 * @version 1.0
 * @date 2020/2/12 19:33
 */
public class SellException extends RuntimeException{

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }
}
