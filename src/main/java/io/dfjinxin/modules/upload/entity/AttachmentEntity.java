package io.dfjinxin.modules.upload.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("attachment")
public class AttachmentEntity {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     *
     */
    private Long tenantId;
    /**
     *
     */
    private Long objectId;
    /**
     *
     */
    private Long userId;
    /**
     *
     */
    private Long totalDownloads;
    /**
     *
     */
    private Long size;
    /**
     *
     */
    private String filename;
    /**
     *
     */
    private String diskFilename;
    /**
     *
     */
    private String contentType;
    /**
     *
     */
    private String extension;
    /**
     *
     */
    private String description;
    /**
     *
     */
    private Integer hasThumb;
    /**
     *
     */
    private Date createTime;
    /**
     *
     */
    private String folder;

    /**
     *
     */
    private String mediaType;
}