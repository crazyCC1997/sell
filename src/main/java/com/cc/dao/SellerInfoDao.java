package com.cc.dao;

import com.cc.pojo.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 卖家信息dao
 *
 * @author cchen
 * @version 1.0
 * @date 2020/2/20 15:49
 */
public interface SellerInfoDao extends JpaRepository<SellerInfo, String> {

    SellerInfo findByOpenid(String openid);
}
