package com.gr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gr.mapper.PmsProductMapper;
import com.gr.pojo.PmsProduct;
import com.gr.service.IPmsProductService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author guorui
 * @since 2021-07-09
 */
@Service
public class PmsProductServiceImpl extends ServiceImpl<PmsProductMapper, PmsProduct> implements IPmsProductService {

    @Override
    public IPage<PmsProduct> page(Integer pageNo, Integer pageSize, String name) {
        QueryWrapper<PmsProduct> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            wrapper.like("name", name);
        }
        return this.page(new Page<>(pageNo, pageSize), wrapper);
    }

    @Override
    public IPage<PmsProduct> pageByCategory(Integer pageNo, Integer pageSize, String categoryId) {
        QueryWrapper<PmsProduct> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(categoryId)) {
            wrapper.like("category_id", categoryId);
        }
        return this.page(new Page<>(pageNo, pageSize), wrapper);
    }
}
