package com.gr.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.gr.pojo.BasePojo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * <p>
 * 分类表
 * </p>
 *
 * @author guorui
 * @since 2021-07-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PmsCategory extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 上级id 顶级为0
     */
    private Long parentId;

    /**
     * 层级
     */
    private Integer level;

    /**
     * 是否有效
     */
    private Integer active;

    /**
     * 子分类
     *
     * */
    @TableField(exist = false)
    private List<PmsCategory> children;

}
