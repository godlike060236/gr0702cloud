package com.gr.pojo;

import com.gr.pojo.BasePojo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 个人足迹
 * </p>
 *
 * @author guorui
 * @since 2021-07-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UmsHistory extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * 关联用户id
     */
    private Long userId;

    /**
     * 历史记录
     */
    private String productIds;


}
