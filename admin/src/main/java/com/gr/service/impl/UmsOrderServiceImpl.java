package com.gr.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gr.mapper.UmsOrderMapper;
import com.gr.pojo.UmsOrder;
import com.gr.service.IUmsOrderService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author dc
 * @since 2021-07-18
 */
@Service
public class UmsOrderServiceImpl extends ServiceImpl<UmsOrderMapper, UmsOrder> implements IUmsOrderService {

}
