package com.cc.controller.wx;

import com.cc.dto.OrderMasterDTO;
import com.cc.enums.ResultEnum;
import com.cc.exception.SellException;
import com.cc.service.OrderMasterService;
import com.cc.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 微信支付controller
 *
 * @author cchen
 * @version 1.0
 * @date 2020/2/16 11:27
 */
@Controller
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private OrderMasterService orderMasterService;

    @Autowired
    private PayService payService;

    /**
     * 发起支付
     *
     * @param orderId
     * @param returnUrl
     * @return
     */
    @GetMapping("/create")
    public String create(@RequestParam("orderId") String orderId,
                         @RequestParam("returnUrl") String returnUrl,
                         Model model){
        //1.查询订单
        OrderMasterDTO orderMasterDTO = orderMasterService.findOne(orderId);
        if(null == orderMasterDTO){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        //2.发起支付
//        PayResponse payResponse = payService.create(orderMasterDTO);
//        model.addAttribute("payResponse", payResponse);
        model.addAttribute("payResponse", payService.create(orderMasterDTO));
        model.addAttribute("returnUrl", returnUrl);
        return "pay/create";
    }

    /**
     * 微信异步通知
     *
     * @param notifyData 异步通知数据
     */
    @PostMapping("/notify")
    //@requestBody接收的参数来自于请求体，通常用来处理前端传过来的就json、xml格式的字符串
    //@requestParam接收的参数来自于请求头
    public String notify(@RequestBody String notifyData){

        payService.notify(notifyData);

        //返回给微信处理结果
        return "pay/success";
    }
}
