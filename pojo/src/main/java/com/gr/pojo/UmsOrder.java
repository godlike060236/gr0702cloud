package com.gr.pojo;

import com.gr.pojo.BasePojo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author dc
 * @since 2021-07-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UmsOrder extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 收货地址ID
     */
    private Long addressId;

    /**
     * 购物车里需要支付的商品ID
     */
    private String productIds;

    /**
     * 是否已经发货
     */
    private Integer isDeliver;

    /**
     * 是否已经完成订单
     */
    private Integer isFinish;

    /**
     * 订单可用状态
     */
    private Integer active;


}
