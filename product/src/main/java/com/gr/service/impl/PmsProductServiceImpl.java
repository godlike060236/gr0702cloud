package com.gr.service.impl;

import com.gr.pojo.PmsProduct;
import com.gr.mapper.PmsProductMapper;
import com.gr.service.IPmsProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author guorui
 * @since 2021-07-09
 */
@Service
public class PmsProductServiceImpl extends ServiceImpl<PmsProductMapper, PmsProduct> implements IPmsProductService {

}
