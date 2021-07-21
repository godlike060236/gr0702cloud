package com.gr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gr.pojo.CartStock;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author dongyuchen
 * @since 2021-07-12
 */
public interface ICartStockService extends IService<CartStock> {
    List<CartStock> list(Long active);

    List<CartStock> getListByCartStockId(Long active, Long cartStockId);

    CartStock saveBySku(CartStock cartStock, Long productId, Long skuId);

    boolean del(Long cartStockId);

    boolean updateStock(Long cartStockId, Integer stock);
}
