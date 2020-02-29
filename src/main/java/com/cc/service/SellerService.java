package com.cc.service;

import com.cc.pojo.SellerInfo;

/**
 * 卖家端服务接口
 *
 * @author cchen
 * @version 1.0
 * @date 2020/2/20 17:16
 */
public interface SellerService {

    /**
     * 通过openId查询卖家信息
     *
     * @param openid
     * @return
     */
    SellerInfo findSellerInfoByOpenid(String openid);


}
