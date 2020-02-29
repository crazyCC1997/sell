package com.cc.controller.seller;

import com.cc.dto.OrderMasterDTO;
import com.cc.enums.ResultEnum;
import com.cc.exception.SellException;
import com.cc.service.OrderMasterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jws.WebParam;

/**
 * 卖家端订单controller
 *
 * @author cchen
 * @version 1.0
 * @date 2020/2/17 23:50
 */
@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellerOrderController {

    @Autowired
    private OrderMasterService orderMasterService;

    /**
     * 分页查询订单信息
     *
     * @param page 第几页，从第一页开始
     * @param size 一页多少条记录
     * @return
     */
    @GetMapping("/list")
    public String list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                       @RequestParam(value = "size", defaultValue = "10") Integer size,
                       Model model){
        PageRequest pageRequest = new PageRequest(page - 1, size);
        model.addAttribute("orderMasterDTOPage", orderMasterService.findList(pageRequest));
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        return "order/list";
    }

    /**
     * 取消订单
     *
     * @param orderId 订单id
     * @param model
     * @return
     */
    @GetMapping("/cancel")
    public String cancel(@RequestParam("orderId") String orderId, Model model) {
        try {
            OrderMasterDTO orderMasterDTO = orderMasterService.findOne(orderId);
            orderMasterService.cancel(orderMasterDTO);
        }catch (SellException e) {
            log.error("【卖家端取消订单】 发生异常{}", e);
            model.addAttribute("msg", e.getMessage());
            model.addAttribute("url", "/sell/seller/order/list");
            return "/common/error";
        }
        model.addAttribute("msg", ResultEnum.ORDER_CANCEL_SUCCESS.getMessage());
        model.addAttribute("url", "/sell/seller/order/list");
        return "/common/success";
    }

    /**
     * 获取订单详情
     *
     * @param orderId
     * @param model
     * @return
     */
    @GetMapping("/detail")
    public String detail(@RequestParam("orderId") String orderId, Model model) {

        try {
            orderMasterService.findOne(orderId);
        }catch (SellException e) {
            log.error("【卖家端查询订单详情】发生异常{}", e);
            model.addAttribute("msg", e.getMessage());
            model.addAttribute("url", "/sell/seller/order/list");
            return "/common/error";
        }
        model.addAttribute("orderMasterDTO", orderMasterService.findOne(orderId));
        return "/order/detail";
    }

    /**
     * 完结订单
     *
     * @param orderId
     * @param model
     * @return
     */
    @GetMapping("/finish")
    public String finish(@RequestParam("orderId") String orderId, Model model) {
        try {
            OrderMasterDTO orderMasterDTO = orderMasterService.findOne(orderId);
            orderMasterService.finish(orderMasterDTO);
        }catch (SellException e) {
            log.error("【卖家端完结订单】 发生异常{}", e);
            model.addAttribute("msg", e.getMessage());
            model.addAttribute("url", "/sell/seller/order/list");
            return "/common/error";
        }

        model.addAttribute("msg", ResultEnum.ORDER_FINISH_SUCCESS.getMessage());
        model.addAttribute("url", "/sell/seller/order/list");
        return "/common/success";
    }
}
