package com.cc.controller.seller;

import com.cc.exception.SellException;
import com.cc.form.CategoryForm;
import com.cc.pojo.ProductCategory;
import com.cc.service.ProductCategoryService;
import freemarker.template.utility.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 卖家端商品类目controller
 *
 * @author cchen
 * @version 1.0
 * @date 2020/2/20 13:13
 */
@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * 商品类目列表
     *
     * @param model
     * @return
     */
    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("productCategoryList", productCategoryService.findAll());
        return "/category/list";
    }

    /**
     * 商品类目新增修改
     *
     * @param categoryId
     * @param model
     * @return
     */
    @GetMapping("/index")
    public String index(@RequestParam(value = "categoryId", required = false)Integer categoryId, Model model) {
        if (categoryId != null) {
            model.addAttribute("productCategory", productCategoryService.findOne(categoryId));
        }
        return "/category/index";
    }

    /**
     * 商品类目保存
     *
     * @param categoryForm
     * @param bindingResult
     * @param model
     * @return
     */
    @PostMapping("/save")
    public String save(@Valid CategoryForm categoryForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("msg", bindingResult.getFieldError().getDefaultMessage());
            model.addAttribute("url", "sell/seller/category/index");
            return "/common/error";
        }

        ProductCategory productCategory = new ProductCategory();
        try {
            //如果categoryId不为空，就是修改后保存
            if(categoryForm.getCategoryId() != null) {
                productCategory = productCategoryService.findOne(categoryForm.getCategoryId());
            }
            BeanUtils.copyProperties(categoryForm, productCategory);
            productCategoryService.save(productCategory);
        }catch (SellException e) {
            model.addAttribute("msg", e.getMessage());
            model.addAttribute("url", "/sell/seller/category/index");
            return "/common/error";
        }

        model.addAttribute("url", "/sell/seller/category/list");
        return "/common/success";
    }
}
