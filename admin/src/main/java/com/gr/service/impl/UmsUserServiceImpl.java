package com.gr.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gr.mapper.UmsUserMapper;
import com.gr.pojo.UmsResource;
import com.gr.pojo.UmsUser;
import com.gr.service.IUmsResourceService;
import com.gr.service.IUmsUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author guorui
 * @since 2021-06-27
 */
@Service
public class UmsUserServiceImpl extends ServiceImpl<UmsUserMapper, UmsUser> implements IUmsUserService {
    @Resource
    BCryptPasswordEncoder passwordEncoder;
    @Resource
    IUmsResourceService resourceService;

    @Override
    public IPage<UmsUser> page(Integer pageNo, Integer pageSize, String name) {
        QueryWrapper<UmsUser> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            wrapper.like("login_name", name);
        }
        return this.page(new Page<>(pageNo, pageSize), wrapper);
    }

    @Override
    public List<UmsUser> getAll() {
        QueryWrapper<UmsUser> wrapper = new QueryWrapper<>();
        wrapper.eq("active", 1);
        return this.list(wrapper);
    }

    @Override
    public Map<String, Object> login(String username, String password) throws Exception {
        QueryWrapper<UmsUser> wrapper = new QueryWrapper<>();
        wrapper.eq("login_name", username);
        UmsUser user = this.getOne(wrapper);
        if (user == null) {
            throw new Exception("用户不存在");
        }
        if (user.getActive() == 0) {
            throw new Exception("该用户已失效");
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new Exception("密码错误");
        }
        // 如果登录成功，需要获取到用户权限
        List<UmsResource> resources = resourceService.getByUserId(user.getId());
        Map<String, Object> split = split(resources);
        Map<String, Object> result = new HashMap<>();
        result.put("frontUrl", split.get("frontUrl"));
        String token = JWT.create().withClaim("username", username)
                .withClaim("backUrl", (List<String>) split.get("backUrl"))
                .sign(Algorithm.HMAC256("guorui"));
        result.put("token", token);
        return result;
    }

    @Override
    public Map<String, Object> customerLogin(String username, String password) throws Exception {
        QueryWrapper<UmsUser> wrapper = new QueryWrapper<>();
        wrapper.eq("login_name", username);
        UmsUser user = this.getOne(wrapper);
        if (user == null) {
            throw new Exception("用户不存在");
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new Exception("密码错误");
        }
        if (user.getActive() == 0) {
            throw new Exception("该用户已失效");
        }
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("token", user.getId());
//        userInfo.put("username",user.getLoginName());
//        userInfo.put("userIcon",user.getIcon());
//
//        String token = JWT.create().withClaim("userInfo", userInfo)
//                .sign(Algorithm.HMAC256("guorui"));
//
//        Map<String, Object> result = new HashMap<>();
//        result.put("token", token);
        return userInfo;
    }

    @Override
    public boolean changePassword(String username, String rawPassword) {
        UpdateWrapper<UmsUser> wrapper = new UpdateWrapper<>();
        wrapper.set("password", rawPassword).eq("login_name", username);
        return this.update(wrapper);
    }

    @Override
    public boolean getTheUser(UmsUser user) throws Exception {
        QueryWrapper<UmsUser> wrapper = new QueryWrapper<>();
        wrapper.eq("login_name", user.getLoginName()).eq("email", user.getEmail()).eq("active", 1);
        UmsUser result = this.getOne(wrapper);
        return result != null;
    }

    private Map<String, Object> split(List<UmsResource> resources) {
        Map<String, Object> map = new HashMap<>();
        List<String> backUrl = new ArrayList<>();
        List<UmsResource> frontUrl = getByParentId(resources, 0L);
        for (UmsResource resource : resources) {
            if (StringUtils.isNotBlank(resource.getBackUrl())) {
                backUrl.add(resource.getBackUrl());
            }
        }
        map.put("backUrl", backUrl);
        map.put("frontUrl", frontUrl);
        return map;
    }

    private List<UmsResource> getByParentId(List<UmsResource> list, Long parentId) {
        List<UmsResource> result = new ArrayList<>();
        for (UmsResource resource : list) {
            if (resource.getParentId().longValue() == parentId.longValue() && resource.getType() == 0) {
                resource.setChildren(getByParentId(list, resource.getId()));
                result.add(resource);
            }
        }
        return result;
    }
}
