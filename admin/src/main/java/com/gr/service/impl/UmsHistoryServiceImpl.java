package com.gr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gr.mapper.UmsHistoryMapper;
import com.gr.pojo.UmsHistory;
import com.gr.service.IUmsHistoryService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 个人足迹 服务实现类
 * </p>
 *
 * @author guorui
 * @since 2021-07-20
 */
@Service
public class UmsHistoryServiceImpl extends ServiceImpl<UmsHistoryMapper, UmsHistory> implements IUmsHistoryService {
    @Override
    public List<UmsHistory> getByUserId(Long userId) {
        QueryWrapper<UmsHistory> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        return this.list(wrapper);
    }
}
