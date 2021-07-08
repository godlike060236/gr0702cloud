package com.gr.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gr.pojo.BasePojo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author guorui
 * @since 2021-07-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UmsUser extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 密码
     */
    @JsonIgnore
    private String password;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 是否有效
     */
    private Integer active;

    /**
     * 用户头像
     */
    private String icon;

    /**
     * 密码明文
     */
    @TableField(exist = false)
    @JsonIgnore
    private String rawPassword;
}
