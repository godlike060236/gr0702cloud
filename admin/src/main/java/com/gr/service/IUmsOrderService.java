package com.gr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gr.pojo.UmsOrder;

import java.util.List;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author dc
 * @since 2021-07-18
 */
public interface IUmsOrderService extends IService<UmsOrder> {
    IPage<UmsOrder> page(Integer pageNo, Integer pageSize, String productId);

    List<UmsOrder> getIsCreate(Long userId);

    List<UmsOrder> getIsDeliver(Long userId);

    List<UmsOrder> getIsFinishWithoutDeliver(Long userId);

    List<UmsOrder> getAll(Long userId);
}
