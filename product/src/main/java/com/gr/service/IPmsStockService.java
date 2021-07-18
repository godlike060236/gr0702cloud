package com.gr.service;

import com.gr.pojo.PmsStock;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * sku库存表 服务类
 * </p>
 *
 * @author guorui
 * @since 2021-07-10
 */
public interface IPmsStockService extends IService<PmsStock> {
    List<PmsStock> list(Long productId);
}
