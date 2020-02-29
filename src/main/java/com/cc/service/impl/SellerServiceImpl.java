package com.cc.service.impl;

import com.cc.dao.SellerInfoDao;
import com.cc.pojo.SellerInfo;
import com.cc.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 卖家端实现类
 *
 * @author cchen
 * @version 1.0
 * @date 2020/2/20 17:19
 */
@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoDao sellerInfoDao;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return sellerInfoDao.findByOpenid(openid);
    }
}
