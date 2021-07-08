package com.gr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gr.pojo.UmsRoleUser;
import com.gr.mapper.UmsRoleUserMapper;
import com.gr.service.IUmsRoleUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色与用户关联表 服务实现类
 * </p>
 *
 * @author guorui
 * @since 2021-07-05
 */
@Service
public class UmsRoleUserServiceImpl extends ServiceImpl<UmsRoleUserMapper, UmsRoleUser> implements IUmsRoleUserService {

    @Override
    @Transactional
    public boolean save(Long roleId, Long[] userIds) {
        QueryWrapper<UmsRoleUser> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id",roleId);
        this.remove(wrapper);

        List<UmsRoleUser> list = new ArrayList<>();
        for (Long userId: userIds) {
            list.add(new UmsRoleUser(roleId,userId));
        }
        return this.saveBatch(list);
    }

    @Override
    public List<UmsRoleUser> getUsersByRoleId(Long roleId) {
        QueryWrapper<UmsRoleUser> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id",roleId);
        return this.list(wrapper);
    }
}
