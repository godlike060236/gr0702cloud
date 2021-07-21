package com.gr.controller;


import com.gr.pojo.CartStock;
import com.gr.pojo.PmsStock;
import com.gr.pojo.UmsOrder;
import com.gr.service.ICartStockService;
import com.gr.service.IPmsStockService;
import com.gr.service.IUmsOrderService;
import com.gr.util.ResultJson;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author dc
 * @since 2021-07-18
 */
@RestController
@RequestMapping("/ums-order")
public class UmsOrderController {
    @Resource
    IUmsOrderService umsOrderService;
    @Resource
    ICartStockService cartStockService;
    @Resource
    IPmsStockService pmsStockService;

    @GetMapping("/list")
    ResultJson list(Integer pageNo, Integer pageSize, String productId) throws InterruptedException {
        return ResultJson.success(umsOrderService.page(pageNo,pageSize,productId)) ;
    }

    @GetMapping("/getAll")
    ResultJson getAll(Long userId) throws InterruptedException {
        return ResultJson.success(umsOrderService.getAll(userId),"加载数据成功");
    }

    @GetMapping("/getIsCreate")
    ResultJson getIsCreate(Long userId) throws InterruptedException {
        return ResultJson.success(umsOrderService.getIsCreate(userId),"加载数据成功");
    }

    @GetMapping("/getIsDeliver")
    ResultJson getIsDeliver(Long userId) throws InterruptedException {
        return ResultJson.success(umsOrderService.getIsDeliver(userId),"加载数据成功");
    }

    @GetMapping("/getIsFinishWithoutDeliver")
    ResultJson getIsFinishWithoutDeliver(Long userId) throws InterruptedException {
        return ResultJson.success(umsOrderService.getIsFinishWithoutDeliver(userId),"加载数据成功");
    }

    @GetMapping("/getone")
    ResultJson getone(Long id) {
        return ResultJson.success(umsOrderService.getById(id));
    }

    @PostMapping("/update")
    ResultJson update(UmsOrder umsOrder) {
        return ResultJson.success(umsOrderService.updateById(umsOrder), "修改成功");
    }

    @PostMapping("/deliver")
    ResultJson deliver(UmsOrder umsOrder) {
        String message = umsOrder.getIsDeliver() == 0 ? "未发货" : "发货成功";
        return ResultJson.success(umsOrderService.updateById(umsOrder), message);
    }

    @PostMapping("/active")
    ResultJson active(UmsOrder umsOrder) {
        String message = umsOrder.getActive() == 0 ? "订单失效" : "恢复成功";
        return ResultJson.success(umsOrderService.updateById(umsOrder), "订单已失效");
    }

    @PostMapping("/add")
    @Transactional
    ResultJson add(UmsOrder umsOrder) {
        boolean flag = umsOrderService.save(umsOrder);
        if (flag) {
            String[] str = umsOrder.getProductIds().split(",");
            for (String s : str) {
                CartStock cartStock = cartStockService.getById(s);
                if (cartStock != null) {
                    /*
                     * todo
                     */
                    PmsStock pmsStock = pmsStockService.getById(cartStock.getSkuId());
                    Integer stock = Math.toIntExact(pmsStock.getStock() - cartStock.getNumber());
                    pmsStockService.updateStock(cartStock.getSkuId(), cartStock.getProductId(),stock);
                    cartStockService.del(cartStock.getId());
                }
            }
        }
        return ResultJson.success(flag, "添加成功");
    }
}
