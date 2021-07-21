package com.gr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gr.mapper.UmsOrderMapper;
import com.gr.pojo.UmsOrder;
import com.gr.service.IUmsOrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author dc
 * @since 2021-07-18
 */
@Service
public class UmsOrderServiceImpl extends ServiceImpl<UmsOrderMapper, UmsOrder> implements IUmsOrderService {
    @Override
    public IPage<UmsOrder> page(Integer pageNo, Integer pageSize, String productId) {
        QueryWrapper<UmsOrder> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(productId)) {
            wrapper.eq("id", productId);
        }
        return this.page(new Page<>(pageNo, pageSize), wrapper);
    }

    @Override
    public List<UmsOrder> getIsCreate(Long userId) {
        QueryWrapper<UmsOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId)
                .eq("is_deliver", 0)
                .eq("is_finish", 0);
        return this.list(wrapper);
    }

    @Override
    public List<UmsOrder> getIsDeliver(Long userId) {
        QueryWrapper<UmsOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId)
                .eq("is_deliver", 1)
                .eq("is_finish", 1);
        return this.list(wrapper);
    }

    @Override
    public List<UmsOrder> getIsFinishWithoutDeliver(Long userId) {
        QueryWrapper<UmsOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId)
                .eq("is_deliver", 0)
                .eq("is_finish", 1);
        return this.list(wrapper);
    }

    @Override
    public List<UmsOrder> getAll(Long userId) {
        QueryWrapper<UmsOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        return this.list(wrapper);
    }

}
