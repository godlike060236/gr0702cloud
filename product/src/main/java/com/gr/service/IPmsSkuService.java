package com.gr.service;

import com.gr.pojo.PmsSku;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * sku属性表 服务类
 * </p>
 *
 * @author guorui
 * @since 2021-07-09
 */
public interface IPmsSkuService extends IService<PmsSku> {
    List<PmsSku> list(Long categoryId);
    List<PmsSku> getByCategory(Long[] categoryIds);
}
