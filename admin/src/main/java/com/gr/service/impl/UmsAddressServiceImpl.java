package com.gr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gr.mapper.UmsAddressMapper;
import com.gr.pojo.UmsAddress;
import com.gr.service.IUmsAddressService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 地址表 服务实现类
 * </p>
 *
 * @author guorui
 * @since 2021-07-16
 */
@Service
public class UmsAddressServiceImpl extends ServiceImpl<UmsAddressMapper, UmsAddress> implements IUmsAddressService {

    @Override
    public List<UmsAddress> getByUserId(Long userId) {
        QueryWrapper<UmsAddress> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        return this.list(wrapper);
    }

    @Override
    public boolean changeDefault(Long id) {
        UpdateWrapper<UmsAddress> wrapper = new UpdateWrapper<>();
        wrapper.set("is_default", 0).eq("user_id", id).eq("is_default", 1);
        return this.update(wrapper);
    }
}
