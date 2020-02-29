package com.cc.controller.seller;

import com.cc.exception.SellException;
import com.cc.form.ProductForm;
import com.cc.pojo.ProductCategory;
import com.cc.pojo.ProductInfo;
import com.cc.service.ProductCategoryService;
import com.cc.service.ProductInfoService;
import com.cc.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

/**
 * 卖家端商品信息controller
 *
 * @author cchen
 * @version 1.0
 * @date 2020/2/19 14:31
 */
@Controller
@RequestMapping("/seller/product")
@Slf4j
public class SellerProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * 卖家端商品列表
     *
     * @param page
     * @param size
     * @param model
     * @return
     */
    @GetMapping("/list")
    public String list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                       @RequestParam(value = "size", defaultValue = "10") Integer size,
                       Model model) {
        PageRequest pageRequest = new PageRequest(page - 1, size);
        model.addAttribute("productInfoPage", productInfoService.findAll(pageRequest));
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        return "/product/list";
    }


    /**
     * 商品上架
     *
     * @param productId
     * @param model
     * @return
     */
    @GetMapping("on_sale")
    public String onSale(@RequestParam("productId") String productId, Model model) {
        try {
            productInfoService.onSale(productId);
        }catch (SellException e) {
            model.addAttribute("msg", e.getMessage());
            model.addAttribute("url", "/sell/seller/product/list");
            return "/common/error";
        }
        model.addAttribute("url", "/sell/seller/product/list");
        return "/common/success";
    }

    /**
     * 商品下架
     *
     * @param productId
     * @param model
     * @return
     */
    @GetMapping("/off_sale")
    public String offSale(@RequestParam("productId") String productId, Model model) {
        try {
            productInfoService.offSale(productId);
        }catch (SellException e) {
            model.addAttribute("msg", e.getMessage());
            model.addAttribute("url", "/sell/seller/product/list");
            return "/common/error";
        }
        model.addAttribute("url", "/sell/seller/product/list");
        return "/common/success";
    }

    /**
     * 商品新增修改
     *
     * @param productId
     * @param model
     * @return
     */
    @GetMapping("/index")
    public String index(@RequestParam(value = "productId", required = false) String productId, Model model) {
        if (StringUtils.isNotEmpty(productId)) {
            ProductInfo productInfo = productInfoService.findOne(productId);
            model.addAttribute("productInfo", productInfo);
        }
        //查询所有的类目
        List<ProductCategory> productCategoryList = productCategoryService.findAll();
        model.addAttribute("productCategoryList", productCategoryList);
        return "product/index";
    }

    /**
     * 保存
     *
     * @param productForm
     * @param bindingResult
     * @param model
     * @return
     */
    @PostMapping("/save")
    public String save(@Valid ProductForm productForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("msg", bindingResult.getFieldError().getDefaultMessage());
            model.addAttribute("url", "sell/seller/product/index");
            return "/common/error";
        }
        ProductInfo productInfo = new ProductInfo();
        try {
            //如果productId为空，说明是新增
            if (StringUtils.isNotEmpty(productForm.getProductId())) {
                productInfo = productInfoService.findOne(productForm.getProductId());
            }
            productForm.setProductId(KeyUtil.genUniqueKey());
            BeanUtils.copyProperties(productForm, productInfo);
            productInfoService.save(productInfo);
        }catch (SellException e) {
            model.addAttribute("msg", e.getMessage());
            model.addAttribute("url", "/sell/seller/product/index");
            return "/common/list";
        }
        model.addAttribute("url", "/sell/seller/product/list");
        return "/common/success";
    }
}
