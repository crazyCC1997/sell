package com.cc.form;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 类目数据表单
 *
 * @author cchen
 * @version 1.0
 * @date 2020/2/20 14:14
 */
@Data
public class CategoryForm {
    /**
     * 类目id
     */
    private Integer categoryId;

    /**
     * 类目名字
     */
    private String categoryName;

    /**
     * 类目编号
     */
    private Integer categoryType;
}
