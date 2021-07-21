package com.gr.controller;


import com.gr.service.IPmsStockService;
import com.gr.util.ResultJson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * sku库存表 前端控制器
 * </p>
 *
 * @author guorui
 * @since 2021-07-10
 */
@RestController
@RequestMapping("/pms-stock")
public class PmsStockController {
    @Resource
    IPmsStockService pmsStockService;

    @GetMapping("/list")
    ResultJson list(Long productId) {
        return ResultJson.success(pmsStockService.list(productId), "加载数据成功");
    }

    @GetMapping("/updateStock")
    ResultJson updateStock(Long id, Long productId, Integer stock){
        return ResultJson.success(pmsStockService.updateStock(id,productId,stock),"修改数据成功");
    }
}
