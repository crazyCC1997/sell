package com.cc.utils;

import com.cc.vo.ResultVo;

/**
 * 结果集工具类
 *
 * @author cchen
 * @version 1.0
 * @date 2020/2/11 19:07
 */
public class ResultVoUtil {

    public static ResultVo success(Object object){

        ResultVo resultVo = new ResultVo();
        resultVo.setData(object);
        resultVo.setCode(0);
        resultVo.setMessage("success");
        return resultVo;
    }

    public static ResultVo success(){

        return success(null);
    }

    public static ResultVo error(Integer code, String msg){

        ResultVo resultVo = new ResultVo();
        resultVo.setCode(code);
        resultVo.setMessage(msg);
        return resultVo;
    }
}
