package com.gr.controller;


import com.gr.service.IUmsRoleUserService;
import com.gr.service.IUmsUserService;
import com.gr.util.ResultJson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 角色与用户关联表 前端控制器
 * </p>
 *
 * @author guorui
 * @since 2021-07-05
 */
@RestController
@RequestMapping("/ums-role-user")
public class UmsRoleUserController {
    @Resource
    IUmsUserService umsUserService;
    @Resource
    IUmsRoleUserService umsRoleUserService;

    @GetMapping("/getData")
    ResultJson getData(Long roleId) {
        Map<String,Object> map = new HashMap<>();
        map.put("users",umsUserService.getAll());
        map.put("values",umsRoleUserService.getUsersByRoleId(roleId));
        return ResultJson.success(map);
    }

    @PostMapping("/save")
    ResultJson save(Long roleId, Long[] userIds) {
        return  ResultJson.success(umsRoleUserService.save(roleId, userIds),"角色与用户关联成功");
    }
}
