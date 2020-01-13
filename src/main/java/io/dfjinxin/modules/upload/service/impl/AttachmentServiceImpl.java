package io.dfjinxin.modules.upload.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.dfjinxin.handler.LocalFileHandler;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.dfjinxin.modules.upload.dao.IAttachmentDao;
import io.dfjinxin.modules.upload.entity.AttachmentEntity;
import io.dfjinxin.modules.upload.service.IAttachmentService;
import io.dfjinxin.util.DataSet;
import io.dfjinxin.util.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Service
public class AttachmentServiceImpl extends ServiceImpl<IAttachmentDao, AttachmentEntity> implements IAttachmentService {

    @Autowired
    private LocalFileHandler localFileHandler;

    @Override
    public AttachmentEntity upload(MultipartFile file, String folder, Long userId, Long objectId) {
        Assert.notNull(file, "Multipart file must not be null");

        // Upload file
        AttachmentEntity atta = localFileHandler.upload(file, folder, userId, objectId);
        save(atta);

        return atta;
    }

    @Override
    public DataSet<AttachmentEntity> queryAttachments(Long userId, Integer pageIndex, Integer pageSize) {
        Map<String, Object> params = new HashMap<String, Object>() {{
            put("pageIndex", pageIndex);
            put("pageSize", pageSize);
        }};

        IPage<AttachmentEntity> page = this.page(
                new Query<AttachmentEntity>().getPage(params),
                new QueryWrapper<AttachmentEntity>()
                        .eq(userId != null, "user_id", userId).orderByDesc("id")
        );

        return new DataSet(page);
    }
}
