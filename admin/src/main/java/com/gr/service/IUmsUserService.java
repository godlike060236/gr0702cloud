package com.gr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gr.pojo.UmsUser;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author guorui
 * @since 2021-06-27
 */
public interface IUmsUserService extends IService<UmsUser> {
    IPage<UmsUser> page(Integer pageNo, Integer pageSize, String name);

    List<UmsUser> getAll();

    Map<String, Object> login(String username, String password) throws Exception;

    Map<String, Object> customerLogin(String username, String password) throws Exception;

    boolean changePassword(String username, String rawPassword);

    boolean getTheUser(UmsUser user) throws Exception;
}
