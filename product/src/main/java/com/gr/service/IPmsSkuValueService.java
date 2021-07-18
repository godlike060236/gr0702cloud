package com.gr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gr.pojo.PmsSkuValue;

import java.util.List;

/**
 * <p>
 * 商品sku值 服务类
 * </p>
 *
 * @author guorui
 * @since 2021-07-10
 */
public interface IPmsSkuValueService extends IService<PmsSkuValue> {
    List<PmsSkuValue> list(Long productId);
}
