package com.gr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gr.mapper.UmsResourceMapper;
import com.gr.pojo.UmsResource;
import com.gr.service.IUmsResourceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 资源表 服务实现类
 * </p>
 *
 * @author guorui
 * @since 2021-07-06
 */
@Service
public class UmsResourceServiceImpl extends ServiceImpl<UmsResourceMapper, UmsResource> implements IUmsResourceService {
    @Resource
    UmsResourceMapper resourceMapper;

    @Override
    public List<UmsResource> getResource(Long parentId) {
        QueryWrapper<UmsResource> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", parentId);
        List<UmsResource> list = this.list(wrapper);
        for (UmsResource resource : list) {
            resource.setChildren(getResource(resource.getId()));
        }
        return list;
    }

    @Override
    public List<Long> getLast() {
        QueryWrapper<UmsResource> wrapper = new QueryWrapper<>();
        wrapper.eq("haschildren", 0);
        List<UmsResource> list = this.list(wrapper);
        List<Long> result = new ArrayList<>();
        list.forEach(umsResource -> result.add(umsResource.getId()));
        return result;
    }

    @Override
    public List<UmsResource> getByUserId(Long userId) {
        return resourceMapper.getByUserId(userId);
    }

}
