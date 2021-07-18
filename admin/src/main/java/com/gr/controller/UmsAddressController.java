package com.gr.controller;


import com.gr.pojo.UmsAddress;
import com.gr.service.IUmsAddressService;
import com.gr.util.ResultJson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 地址表 前端控制器
 * </p>
 *
 * @author guorui
 * @since 2021-07-16
 */
@RestController
@RequestMapping("/ums-address")
public class UmsAddressController {
    @Resource
    IUmsAddressService addressService;

    @GetMapping("/list")
    ResultJson list(Long userId) {
        return ResultJson.success(addressService.getByUserId(userId), "");
    }

    @GetMapping("/getOne")
    ResultJson getOne(Long addressId) {
        return ResultJson.success(addressService.getById(addressId), "");
    }

    @PostMapping("/save")
    ResultJson add(UmsAddress umsAddress) {
        return ResultJson.success(addressService.saveOrUpdate(umsAddress), "编辑成功");
    }

    @PostMapping("/del")
    ResultJson del(Long id) {
        return ResultJson.success(addressService.removeById(id), "删除成功");
    }

    @PostMapping("/changeDefault")
    ResultJson changeDefault(Long id) {
        return ResultJson.success(addressService.changeDefault(id), "");
    }
}
