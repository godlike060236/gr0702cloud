package com.gr.pojo;

import java.math.BigDecimal;
import com.gr.pojo.BasePojo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author guorui
 * @since 2021-07-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PmsProduct extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 标准价格
     */
    private BigDecimal price;

    /**
     * 品牌id
     */
    private Long brandId;

    /**
     * 类别id
     */
    private String categoryId;

    /**
     * 关键词
     */
    private String keywords;

    /**
     * 上架状态
     */
    private Integer publishStatus;

    /**
     * 热卖状态
     */
    private Integer hotStatus;

    /**
     * 新品状态
     */
    private Integer newStatus;

    /**
     * 商品图片
     */
    private String img;

    /**
     * 商品相册
     */
    private String pics;

    /**
     * 是否有效
     */
    private Integer active;

    /**
     * 商品详情
     * */
    private String detailHtml;
}
