package com.gr.pojo;

import com.gr.pojo.BasePojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 角色关联资源
 * </p>
 *
 * @author guorui
 * @since 2021-07-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class UmsRoleResource extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 资源id
     */
    private Long resourceId;


}
