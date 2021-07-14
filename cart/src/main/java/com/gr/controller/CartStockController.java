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
 *  前端控制器
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
    ResultJson list(Long skuId) {
        return ResultJson.success(cartStockService.list());
    }
    @PostMapping("/update")
    ResultJson update(CartStock cartStock) {
        return ResultJson.success(cartStockService.updateById(cartStock),"修改sku成功");
    }
    @GetMapping("/getone")
    ResultJson getone(Long id) {
        return ResultJson.success(cartStockService.getById(id));
    }
    @PostMapping("/del")
    ResultJson del(Long id) {
        System.out.println(id);
        return ResultJson.success(cartStockService.removeById(id),"删除成功");
    }


}