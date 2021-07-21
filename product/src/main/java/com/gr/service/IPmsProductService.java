package com.gr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gr.pojo.PmsProduct;

import java.util.List;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author guorui
 * @since 2021-07-09
 */
public interface IPmsProductService extends IService<PmsProduct> {
    IPage<PmsProduct> page1(Integer pageNo, Integer pageSize, String name, String categoryId, String keyWord);

    List<PmsProduct> getKeyWords();

}
