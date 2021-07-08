package com.gr.service;

import com.gr.pojo.UmsRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author guorui
 * @since 2021-07-05
 */
public interface IUmsRoleService extends IService<UmsRole> {
    List<UmsRole> list(String name);
}
