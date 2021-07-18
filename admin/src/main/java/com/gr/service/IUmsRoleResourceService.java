package com.gr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gr.pojo.UmsRoleResource;

import java.util.List;

/**
 * <p>
 * 角色关联资源 服务类
 * </p>
 *
 * @author guorui
 * @since 2021-07-06
 */
public interface IUmsRoleResourceService extends IService<UmsRoleResource> {
    boolean save(Long roleId, Long[] resourcesIds);

    List<Long> getByRoleId(Long roleId);
}
