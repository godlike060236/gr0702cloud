package com.gr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gr.mapper.PmsSkuValueMapper;
import com.gr.pojo.PmsSkuValue;
import com.gr.service.IPmsSkuValueService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品sku值 服务实现类
 * </p>
 *
 * @author guorui
 * @since 2021-07-10
 */
@Service
public class PmsSkuValueServiceImpl extends ServiceImpl<PmsSkuValueMapper, PmsSkuValue> implements IPmsSkuValueService {
    @Override
    public List<PmsSkuValue> list(Long productId) {
        QueryWrapper<PmsSkuValue> wrapper = new QueryWrapper<>();
        wrapper.eq("product_id", productId);
        return this.list(wrapper);
    }
}
