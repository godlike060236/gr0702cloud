package com.gr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gr.mapper.PmsSpuMapper;
import com.gr.pojo.PmsSpu;
import com.gr.service.IPmsSpuService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * spu属性表 服务实现类
 * </p>
 *
 * @author guorui
 * @since 2021-07-09
 */
@Service
public class PmsSpuServiceImpl extends ServiceImpl<PmsSpuMapper, PmsSpu> implements IPmsSpuService {

    @Override
    public List<PmsSpu> list(Long categoryId) {
        QueryWrapper<PmsSpu> wrapper = new QueryWrapper<>();
        wrapper.eq("category_id", categoryId);
        return this.list(wrapper);
    }

    @Override
    public List<PmsSpu> getByCategory(Long[] categoryIds) {
        List<Long> ids = Arrays.asList(categoryIds);
        QueryWrapper<PmsSpu> wrapper = new QueryWrapper<>();
        wrapper.in("category_id",ids);
        return this.list(wrapper);
    }
}
