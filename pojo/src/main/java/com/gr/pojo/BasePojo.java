package com.gr.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class BasePojo {
    @TableId(type = IdType.AUTO)
    private Long id;
}
