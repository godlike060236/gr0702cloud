package com.gr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gr.mapper.PmsProductMapper;
import com.gr.pojo.PmsProduct;
import com.gr.service.IPmsProductService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public IPage<PmsProduct> page1(Integer pageNo, Integer pageSize, String name, String categoryId, String keyWord) {
        QueryWrapper<PmsProduct> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(name) || StringUtils.isNotBlank(categoryId) || StringUtils.isNotBlank(keyWord)) {
            wrapper.like("name", name)
                    .like("category_id", categoryId)
                    .like("keywords", keyWord);
        }
        return this.page(new Page<>(pageNo, pageSize), wrapper);
    }

    @Override
    public List<PmsProduct> getKeyWords() {
        LambdaQueryWrapper<PmsProduct> wrapper = new LambdaQueryWrapper();
        // 商家图片，未删除，可用状态
        wrapper.eq(PmsProduct::getActive, 1)
                .select(PmsProduct::getKeywords);
        return this.list(wrapper);
    }
}
