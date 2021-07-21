package com.gr.controller;


import com.gr.service.IUmsHistoryService;
import com.gr.util.ResultJson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 个人足迹 前端控制器
 * </p>
 *
 * @author guorui
 * @since 2021-07-20
 */
@RestController
@RequestMapping("/ums-history")
public class UmsHistoryController {
    @Resource
    IUmsHistoryService umsHistoryService;

    @GetMapping("/list")
    ResultJson list(Long userId) {
        return ResultJson.success(umsHistoryService.getByUserId(userId), "加载数据成功");
    }
}
