package io.dfjinxin.modules.upload.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user")
public class UserEntity {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private Boolean admin;

    private String path;
}
