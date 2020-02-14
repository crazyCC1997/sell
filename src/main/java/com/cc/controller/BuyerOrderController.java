package com.cc.controller;

import com.cc.converter.OrderForm2OrderMasterDTOConverter;
import com.cc.dto.OrderMasterDTO;
import com.cc.enums.ResultEnum;
import com.cc.exception.SellException;
import com.cc.form.OrderForm;
import com.cc.pojo.OrderDetail;
import com.cc.service.BuyerService;
import com.cc.service.OrderMasterService;
import com.cc.service.impl.BuyerServiceImpl;
import com.cc.service.impl.OrderMasterServiceImpl;
import com.cc.utils.ResultVoUtil;
import com.cc.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 买家端 订单controller
 *
 * @author cchen
 * @version 1.0
 * @date 2020/2/13 22:02
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderMasterService orderMasterService;

    @Autowired
    private BuyerService buyerService;

    /**
     * 创建订单
     *
     * @param orderForm 订单数据列表
     * @return
     */
    @PostMapping("/create")
    public ResultVo<Map<String,String>> create(@Valid OrderForm orderForm, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            log.error("【创建订单】 参数不正确，orderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        OrderMasterDTO orderMasterDTO = OrderForm2OrderMasterDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderMasterDTO.getOrderDetailList())){
            log.error("【创建订单】 购物车为空，orderDetailList={}", orderMasterDTO.getOrderDetailList());
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        OrderMasterDTO createResult = orderMasterService.create(orderMasterDTO);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", orderMasterDTO.getOrderId());
        return ResultVoUtil.success(map);
    }

    /**
     * 订单列表
     *
     * @param openid
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/list")
    public ResultVo<List<OrderMasterDTO>> list(@RequestParam("openid") String openid,
                                               @RequestParam(value = "page", defaultValue = "0") Integer page,
                                               @RequestParam(value = "size", defaultValue = "10") Integer size){
        if (StringUtils.isEmpty(openid)){
            log.info("【查询订单列表】 openid不能为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest pageRequest = new PageRequest(page, size);
        Page<OrderMasterDTO> orderMasterDTOPage = orderMasterService.findList(openid, pageRequest);
        return ResultVoUtil.success(orderMasterDTOPage.getContent());

    }

    /**
     * 订单详情
     * @param openid
     * @param orderId 订单id
     * @return
     */
    @GetMapping("/detail")
    public ResultVo<OrderMasterDTO> detail(@RequestParam("openid") String openid,
                                                 @RequestParam("orderId") String orderId){
        if (StringUtils.isEmpty(openid) || StringUtils.isEmpty(orderId)){
            log.error("【订单详情】 openid和orderId不能为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        OrderMasterDTO orderMasterDTO = buyerService.findOrderOne(openid, orderId);
        return ResultVoUtil.success(orderMasterDTO);
    }

    //取消订单
    @PostMapping("/cancel")
    public ResultVo cancel(@RequestParam("openid") String openid, @RequestParam("orderId") String orderId){
        if (StringUtils.isEmpty(openid) || StringUtils.isEmpty(orderId)){
            log.error("【订单详情】 openid和orderId不能为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        buyerService.cancelOrder(openid, orderId);
        return ResultVoUtil.success();
    }
}
