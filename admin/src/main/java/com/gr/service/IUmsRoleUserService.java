package com.gr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gr.pojo.UmsRoleUser;

import java.util.List;

/**
 * <p>
 * 角色与用户关联表 服务类
 * </p>
 *
 * @author guorui
 * @since 2021-07-05
 */
public interface IUmsRoleUserService extends IService<UmsRoleUser> {
    boolean save(Long roleId, Long[] userIds);

    List<UmsRoleUser> getUsersByRoleId(Long id);
}
