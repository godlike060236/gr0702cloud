package com.gr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gr.pojo.PmsProduct;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author guorui
 * @since 2021-07-09
 */
public interface IPmsProductService extends IService<PmsProduct> {
    IPage<PmsProduct> page(Integer pageNo, Integer pageSize, String name);
    IPage<PmsProduct> pageByCategory(Integer pageNo, Integer pageSize, String categoryId);
}
