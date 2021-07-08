package com.gr.pojo;

import com.gr.pojo.BasePojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 角色与用户关联表
 * </p>
 *
 * @author guorui
 * @since 2021-07-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class UmsRoleUser extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 用户ID
     */
    private Long userId;


}
