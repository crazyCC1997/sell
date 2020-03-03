package com.cc.controller.buyer;

import com.cc.pojo.ProductCategory;
import com.cc.pojo.ProductInfo;
import com.cc.service.ProductCategoryService;
import com.cc.service.ProductInfoService;
import com.cc.utils.ResultVoUtil;
import com.cc.vo.ProductInfoVo;
import com.cc.vo.ProductVo;
import com.cc.vo.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 买家端 商品controller
 *
 * @author cchen
 * @version 1.0
 * @date 2020/2/11 10:52
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductInfoController {
    
    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/list")
    @Cacheable(cacheNames = "product", key = "123")
    //动态指定key  #sellerId(spEL表达式)
    //@Cacheable(cacheNames = "product", key = "#sellerId", condition = "#sellerId.length() > 3", unless = "#result.getCode() != 0")
    //public ResultVo list(@RequestParam("sellerId") String sellerId)
    public ResultVo list(){
        //1.查询所有上架的商品
        List<ProductInfo> upProductList = productInfoService.findUpAll();
        //2.查询类目（一次性查询）
        //传统方法（遍历）
//        ArrayList<Integer> categoryTypeList = new ArrayList<>();
//        for (ProductInfo productInfo : upProductList) {
//            categoryTypeList.add(productInfo.getCategoryType());
//        }
        //java8新特性（stream流式编程, lambda表达式）
        List<Integer> categoryTypeList = upProductList.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory> ProductCategoryList = productCategoryService.findByCategoryTypeIn(categoryTypeList);
        //3.数据拼装
        ArrayList<ProductVo> productVoList = new ArrayList<>();
        for (ProductCategory productCategory : ProductCategoryList) {
            ProductVo productVo = new ProductVo();
            productVo.setCategoryName(productCategory.getCategoryName());
            productVo.setCategoryType(productCategory.getCategoryType());

            ArrayList<ProductInfoVo> productInfoVoList = new ArrayList<>();
            for (ProductInfo productInfo : upProductList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVo productInfoVo = new ProductInfoVo();
                    BeanUtils.copyProperties(productInfo, productInfoVo);
                    productInfoVoList.add(productInfoVo);
                }
            }
            productVo.setProductInfoVoList(productInfoVoList);
            productVoList.add(productVo);
        }
        return ResultVoUtil.success(productVoList);
    }
}
