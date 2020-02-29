package com.cc.service.impl;

import com.cc.dao.OrderMasterDao;
import com.cc.dao.ProductInfoDao;
import com.cc.dto.CartDTO;
import com.cc.enums.ProductStatusEnum;
import com.cc.enums.ResultEnum;
import com.cc.exception.SellException;
import com.cc.pojo.OrderMaster;
import com.cc.pojo.ProductInfo;
import com.cc.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.rmi.runtime.Log;

import javax.xml.transform.Result;
import java.util.List;

/**
 * 商品service实现
 *
 * @author cchen
 * @version 1.0
 * @date 2020/2/11 10:13
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoDao productInfoDao;

    @Override
    public ProductInfo findOne(String productId) {
        return productInfoDao.findOne(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoDao.findByProductStatus(ProductStatusEnum.UP.getValue());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoDao.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        if(null == productInfo) {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        return productInfoDao.save(productInfo);
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = productInfoDao.findOne(cartDTO.getProductId());
            if(null == productInfo){
                throw  new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer stock = productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(stock);
            productInfoDao.save(productInfo);
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = productInfoDao.findOne(cartDTO.getProductId());
            if(null == productInfo){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer stock = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if(stock >= 0){
                productInfo.setProductStock(stock);
                productInfoDao.save(productInfo);
            }else {
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
        }
    }

    @Override
    public ProductInfo onSale(String productId) {
        ProductInfo productInfo = productInfoDao.findOne(productId);
        if(null == productInfo) {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if(productInfo.getProductStatus() == ProductStatusEnum.UP.getValue()) {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        productInfo.setProductStatus(ProductStatusEnum.UP.getValue());
        return productInfoDao.save(productInfo);
    }

    @Override
    public ProductInfo offSale(String productId) {
        ProductInfo productInfo = productInfoDao.findOne(productId);
        if(null == productInfo) {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if(productInfo.getProductStatus() == ProductStatusEnum.DOWN.getValue()) {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getValue());
        return productInfoDao.save(productInfo);
    }
}
