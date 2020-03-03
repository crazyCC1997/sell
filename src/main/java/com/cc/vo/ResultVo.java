package com.cc.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * http请求返回的最外层对象 （vo:view object视图对象）
 *
 * @author cchen
 * @version 1.0
 * @date 2020/2/11 10:56
 */
@Data
public class ResultVo<T> implements Serializable {

    private static final long serialVersionUID = 8432025782965308708L;

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String message;

    /**
     * 具体内容
     */
    private T data;

}
