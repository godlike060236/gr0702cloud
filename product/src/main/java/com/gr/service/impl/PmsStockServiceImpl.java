package com.gr.service.impl;

import com.gr.pojo.PmsStock;
import com.gr.mapper.PmsStockMapper;
import com.gr.service.IPmsStockService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * sku库存表 服务实现类
 * </p>
 *
 * @author guorui
 * @since 2021-07-10
 */
@Service
public class PmsStockServiceImpl extends ServiceImpl<PmsStockMapper, PmsStock> implements IPmsStockService {

}
