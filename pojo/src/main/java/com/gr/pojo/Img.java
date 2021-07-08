package com.gr.pojo;

import com.gr.pojo.BasePojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 图片表
 * </p>
 *
 * @author guorui
 * @since 2021-07-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Img extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * 文件MD5
     */
    private String md5;

    /**
     * 文件大小
     */
    private Long size;

    /**
     * 文件后缀名
     */
    private String suffix;

    /**
     * 文件路径
     */
    private String url;


}
