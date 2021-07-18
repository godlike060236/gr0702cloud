package com.gr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gr.mapper.CartStockMapper;
import com.gr.pojo.CartStock;
import com.gr.service.ICartStockService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author dongyuchen
 * @since 2021-07-12
 */
@Service
public class CartStockServiceImpl extends ServiceImpl<CartStockMapper, CartStock> implements ICartStockService {
    @Override
    public List<CartStock> list(Long skuId) {
        QueryWrapper<CartStock> wrapper = new QueryWrapper<CartStock>();
        wrapper.eq("sku_id", skuId);
        return this.list(wrapper);
    }

    @Override
    public CartStock saveBySku(CartStock cartStock, Long productId, Long skuId) {
        UpdateWrapper<CartStock> wrapper = new UpdateWrapper<CartStock>();
        wrapper.eq("product_id", productId).eq("sku_id", skuId);
        if (this.list(wrapper) == null) {
            this.save(cartStock);
        } else {
            Long number = this.list(wrapper).get(0).getNumber() + cartStock.getNumber();
            wrapper.set("number", number);
            this.update(wrapper);
        }
        return cartStock;
    }
}
