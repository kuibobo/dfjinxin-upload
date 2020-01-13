package io.dfjinxin.modules.upload.service;

import io.dfjinxin.modules.upload.entity.AttachmentEntity;
import io.dfjinxin.util.DataSet;
import org.springframework.web.multipart.MultipartFile;

public interface IAttachmentService {

    AttachmentEntity upload(MultipartFile file, String folder, Long userId, Long objectId);

    DataSet<AttachmentEntity> queryAttachments(Long userId, Integer pageIndex, Integer pageSize);
}
