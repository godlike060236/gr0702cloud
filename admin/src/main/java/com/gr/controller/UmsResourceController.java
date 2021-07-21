package com.gr.controller;


import com.gr.pojo.UmsResource;
import com.gr.service.IUmsResourceService;
import com.gr.util.ResultJson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    IUmsResourceService resourceService;

    @GetMapping("/list")
    ResultJson list() {
        return ResultJson.success(resourceService.getResource(0L));
    }

    @GetMapping("/getone")
    ResultJson getone(Long id) {
        return ResultJson.success(resourceService.getById(id));
    }

    @PostMapping("/update")
    ResultJson update(UmsResource umsResource) {
        return ResultJson.success(resourceService.updateById(umsResource), "修改资源成功");
    }

    @PostMapping("/add")
    ResultJson add(UmsResource umsResource) {
        return ResultJson.success(resourceService.save(umsResource), "添加资源成功");
    }

    @PostMapping("/del")
    ResultJson del(UmsResource umsResource) {
        String message = umsResource.getActive() == 0 ? "删除资源成功" : "恢复资源成功";
        return ResultJson.success(resourceService.updateById(umsResource), message);
    }
}
