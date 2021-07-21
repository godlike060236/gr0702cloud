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
    public List<CartStock> list(Long active) {
        QueryWrapper<CartStock> wrapper = new QueryWrapper<CartStock>();
        wrapper.eq("active", active);
        return this.list(wrapper);
    }

    @Override
    public List<CartStock> getListByCartStockId(Long active, Long cartStockId) {
        QueryWrapper<CartStock> wrapper = new QueryWrapper<CartStock>();
        wrapper.eq("active", active).eq("id", cartStockId);
        return this.list(wrapper);
    }

    @Override
    public CartStock saveBySku(CartStock cartStock, Long productId, Long skuId) {
        UpdateWrapper<CartStock> wrapper = new UpdateWrapper<CartStock>();
        wrapper.eq("product_id", productId).eq("sku_id", skuId).eq("active", 1);
        if (this.list(wrapper).size() == 0) {
            this.save(cartStock);
        } else {
            Long number = this.list(wrapper).get(0).getNumber() + cartStock.getNumber();
            wrapper.set("number", number);
            this.update(wrapper);
        }
        return cartStock;
    }

    @Override
    public boolean del(Long cartStockId) {
        UpdateWrapper<CartStock> wrapper = new UpdateWrapper<CartStock>();
        wrapper.set("active", 0).eq("id", cartStockId);
        return this.update(wrapper);
    }

    @Override
    public boolean updateStock(Long cartStockId, Integer stock) {
        UpdateWrapper<CartStock> wrapper = new UpdateWrapper<CartStock>();
        wrapper.set("stock", stock).eq("id", cartStockId);
        return this.update(wrapper);
    }


}
