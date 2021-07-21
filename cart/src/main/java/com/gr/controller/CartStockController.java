package com.gr.controller;


import com.gr.pojo.CartStock;
import com.gr.service.ICartStockService;
import com.gr.util.ResultJson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author dongyuchen
 * @since 2021-07-12
 */
@RestController
@RequestMapping("/cart-stock")
public class CartStockController {
    @Resource
    ICartStockService cartStockService;

    @GetMapping("/list")
    ResultJson list(Long active, Long cartStockId) {
        return ResultJson.success(cartStockService.list(active));
    }

    @GetMapping("/getListByCartStockId")
    ResultJson getListByCartStockId(Long active, Long cartStockId) {
        return ResultJson.success(cartStockService.getListByCartStockId(active, cartStockId));
    }

    @PostMapping("/update")
    ResultJson update(CartStock cartStock) {
        return ResultJson.success(cartStockService.updateById(cartStock), "修改成功");
    }

    @PostMapping("/updateStock")
    ResultJson updateStock(Long cartStockId, Integer stock) {
        return ResultJson.success(cartStockService.updateStock(cartStockId, stock), "修改成功");
    }

    @PostMapping("/add")
    ResultJson add(CartStock cartStock) {
        Long productId = cartStock.getProductId();
        Long skuId = cartStock.getSkuId();
        return ResultJson.success(cartStockService.saveBySku(cartStock, productId, skuId), "添加成功");
    }

    @GetMapping("/getone")
    ResultJson getone(Long id) {
        return ResultJson.success(cartStockService.getById(id));
    }

    @PostMapping("/del")
    ResultJson del(Long cartStock) {
        return ResultJson.success(cartStockService.del(cartStock), "删除成功");
    }
}
