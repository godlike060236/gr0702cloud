package com.gr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gr.pojo.UmsRoleResource;
import com.gr.mapper.UmsRoleResourceMapper;
import com.gr.service.IUmsRoleResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色关联资源 服务实现类
 * </p>
 *
 * @author guorui
 * @since 2021-07-06
 */
@Service
public class UmsRoleResourceServiceImpl extends ServiceImpl<UmsRoleResourceMapper, UmsRoleResource> implements IUmsRoleResourceService {

    @Override
    @Transactional
    public boolean save(Long roleId, Long[] resourcesIds) {
        QueryWrapper<UmsRoleResource> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id",roleId);
        this.remove(wrapper);
        List<UmsRoleResource> list = new ArrayList<>();
        for(Long resourcesId: resourcesIds){
            list.add(new UmsRoleResource(roleId,resourcesId));
        }
        return this.saveBatch(list);
    }

    @Override
    public List<Long> getByRoleId(Long roleId) {
        QueryWrapper<UmsRoleResource> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id",roleId);
        List<UmsRoleResource> list = this.list(wrapper);
        List<Long> result = new ArrayList<>();
        for(UmsRoleResource roleResource: list){
            result.add(roleResource.getResourceId());
        }
        return result;
    }
}
