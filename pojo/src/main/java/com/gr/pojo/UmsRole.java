package com.gr.pojo;

import com.gr.pojo.BasePojo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author guorui
 * @since 2021-07-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UmsRole extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * 角色名
     */
    private String name;

    /**
     * 是否有效
     */
    private Integer active;


}
