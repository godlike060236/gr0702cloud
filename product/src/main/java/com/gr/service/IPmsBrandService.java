package com.gr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gr.pojo.PmsBrand;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 品牌表 服务类
 * </p>
 *
 * @author guorui
 * @since 2021-07-02
 */
public interface IPmsBrandService extends IService<PmsBrand> {
    IPage<PmsBrand> page(Integer pageNo,Integer pageSize,String name);
}
