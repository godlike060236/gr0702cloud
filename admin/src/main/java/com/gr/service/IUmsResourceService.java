package com.gr.service;

import com.gr.pojo.UmsResource;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 资源表 服务类
 * </p>
 *
 * @author guorui
 * @since 2021-07-06
 */
public interface IUmsResourceService extends IService<UmsResource> {
    List<UmsResource> getResource(Long parentId);
    List<Long> getLast();
    List<UmsResource> getByUserId(Long userId);
}
