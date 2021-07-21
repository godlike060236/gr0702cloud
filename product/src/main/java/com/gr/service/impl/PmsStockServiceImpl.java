package com.gr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.gr.pojo.PmsStock;
import com.gr.mapper.PmsStockMapper;
import com.gr.service.IPmsStockService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * sku库存表 服务实现类
 * </p>
 *
 * @author guorui
 * @since 2021-07-10
 */
@Service
public class PmsStockServiceImpl extends ServiceImpl<PmsStockMapper, PmsStock> implements IPmsStockService {
    @Override
    public List<PmsStock> list(Long productId) {
        QueryWrapper<PmsStock> wrapper = new QueryWrapper<>();
        wrapper.eq("product_id",productId);
        return this.list(wrapper);
    }

    @Override
    public boolean updateStock(Long id, Long productId, Integer stock) {
        UpdateWrapper<PmsStock> wrapper = new UpdateWrapper<PmsStock>();
        wrapper.set("stock", stock).eq("id", id).eq("product_id",productId);
        return this.update(wrapper);
    }
}
