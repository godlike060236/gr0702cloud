package com.gr.mapper;

import com.gr.pojo.UmsResource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 资源表 Mapper 接口
 * </p>
 *
 * @author guorui
 * @since 2021-07-06
 */
public interface UmsResourceMapper extends BaseMapper<UmsResource> {
    List<UmsResource> getByUserId(Long userId);
}
