package com.gr.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author guorui
 * @since 2021-07-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CartStock extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * 商品sku_id
     */
    private Long skuId;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品属性
     */
    private String sku;

    /**
     * 数量
     */
    private Long number;

    /**
     * 单价
     */
    private String price;

    /**
     * 商品图
     */
    private String icon;

    /**
     * 商品id
     */
    private Long productId;

    /**
     * 是否有效
     */
    private Integer active;

}
