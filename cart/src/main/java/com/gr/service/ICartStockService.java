package com.gr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gr.pojo.CartStock;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dongyuchen
 * @since 2021-07-12
 */
public interface ICartStockService extends IService<CartStock> {

    List<CartStock> list(Long skuId);


}
