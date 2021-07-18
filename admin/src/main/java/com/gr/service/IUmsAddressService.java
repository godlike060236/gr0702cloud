package com.gr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gr.pojo.UmsAddress;

import java.util.List;

/**
 * <p>
 * 地址表 服务类
 * </p>
 *
 * @author guorui
 * @since 2021-07-16
 */
public interface IUmsAddressService extends IService<UmsAddress> {
    List<UmsAddress> getByUserId(Long userId);

    boolean changeDefault(Long id);
}
