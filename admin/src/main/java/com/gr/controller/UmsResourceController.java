package com.gr.controller;


import com.gr.service.IUmsResourceService;
import com.gr.util.ResultJson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 资源表 前端控制器
 * </p>
 *
 * @author guorui
 * @since 2021-07-06
 */
@RestController
@RequestMapping("/ums-resource")
public class UmsResourceController {
    @Resource
    IUmsResourceService umsResourceService;

    @GetMapping("/list")
    ResultJson list() {
        return ResultJson.success(umsResourceService.getResource(0L));
    }
}
