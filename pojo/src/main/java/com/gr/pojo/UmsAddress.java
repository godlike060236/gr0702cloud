package com.gr.pojo;

import com.gr.pojo.BasePojo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 地址表
 * </p>
 *
 * @author guorui
 * @since 2021-07-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UmsAddress extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * 收货人姓名
     */
    private String name;

    /**
     * 收货人手机号
     */
    private String tel;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 区县
     */
    private String county;

    /**
     * 详细地址
     */
    private String addressDetail;

    /**
     * 地区编码
     */
    private String areaCode;

    /**
     * 邮政编码
     */
    private String postalCode;

    /**
     * 是否为默认地址
     */
    private Integer isDefault;

    /**
     * 用户ID
     */
    private Long userId;


}
