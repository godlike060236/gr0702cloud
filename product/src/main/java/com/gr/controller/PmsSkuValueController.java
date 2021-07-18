package com.gr.controller;


import com.gr.service.IPmsSkuValueService;
import com.gr.util.ResultJson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 商品sku值 前端控制器
 * </p>
 *
 * @author guorui
 * @since 2021-07-10
 */
@RestController
@RequestMapping("/pms-sku-value")
public class PmsSkuValueController {
    @Resource
    IPmsSkuValueService pmsSkuValueService;
    @GetMapping("/list")
    ResultJson list(Long productId) {
        return ResultJson.success(pmsSkuValueService.list(productId),"加载数据成功");
    }
}
