package com.cc.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 卖家信息
 *
 * @author cchen
 * @version 1.0
 * @date 2020/2/20 15:44
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SellerInfo {

    @Id
    private String sellerId;

    private String username;

    private String password;

    private String openid;
}
