package com.cc.handler;

import com.cc.config.ProjectUrlProperties;
import com.cc.exception.ResponseBankException;
import com.cc.exception.SellException;
import com.cc.exception.SellerAuthorizeException;
import com.cc.utils.ResultVoUtil;
import com.cc.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 异常处理类
 *
 * @author cchen
 * @version 1.0
 * @date 2020/3/1 11:49
 */
@ControllerAdvice
public class SellExceptionHandler {

    @Autowired
    private ProjectUrlProperties projectUrlProperties;

    //拦截登录异常
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public String handleAuthorizeException(Model model) {
        //重定向到登录授权二维码
        //http://cchen1997.natapp1.cc/sell/wechat/quAuthorize?returnUrl=http://cchen1997.natapp1.cc/sell/seller/login
        return "redirect:"
                .concat(projectUrlProperties.getWechatOpenAuthorize())
                .concat("/sell/wechat/qrAuthorize?returnUrl=")
                .concat(projectUrlProperties.getSell())
                .concat("/sell/seller/login");
    }

    /**
     * SellException异常统一处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVo handleSellException(SellException e) {
        return ResultVoUtil.error(e.getCode(), e.getMessage());
    }

    /**
     * 改变状态码
     */
    @ExceptionHandler(value = ResponseBankException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public void responseBankException() {

    }
}
