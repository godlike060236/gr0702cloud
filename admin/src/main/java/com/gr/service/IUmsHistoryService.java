package com.gr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gr.pojo.UmsHistory;

import java.util.List;

/**
 * <p>
 * 个人足迹 服务类
 * </p>
 *
 * @author guorui
 * @since 2021-07-20
 */
public interface IUmsHistoryService extends IService<UmsHistory> {
    List<UmsHistory> getByUserId(Long userId);

}
