package io.dfjinxin.handler;

import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.SecureUtil;
import io.dfjinxin.component.AppProperties;
import io.dfjinxin.exception.ApplicationException;
import io.dfjinxin.modules.upload.entity.AttachmentEntity;
import io.dfjinxin.modules.upload.entity.UserEntity;
import io.dfjinxin.modules.upload.service.IUserService;
import io.dfjinxin.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Objects;

@Component
public class LocalFileHandler extends FileHandler {
    private static Logger logger = LoggerFactory.getLogger(LocalFileHandler.class);

    private final static String THUMBNAIL_SUFFIX = "-thumbnail";

    @Autowired
    private AppProperties properties;

    @Autowired
    private IUserService userService;

    @Override
    public AttachmentEntity upload(MultipartFile file, String folder, Long userId, Long objectId) {
        Assert.notNull(file, "Multipart file must not be null");
        UserEntity userEntity = userService.getUser(userId);

        // Build directory
        Integer year = DateUtil.date().year();
        Integer month = DateUtil.date().month() + 1;
        String subPath = null;
        subPath = new StringBuilder(32).append("/")
                .append(StringUtils.isEmpty(userEntity.getPath()) ? userEntity.getName() : userEntity.getPath()).append("/")
                .append(folder.toLowerCase()).append("/")
                .append(year).append('/')
                .append(month).toString();

        String originalBasename = file.getOriginalFilename();

        // Get basename
        String basename =  SecureUtil.md5(originalBasename + System.currentTimeMillis());

        // Get extension
        String extension = originalBasename.substring(originalBasename.lastIndexOf(".") + 1);

        logger.debug("Base name: [{}], extension: [{}] of original filename: [{}]", basename, extension, file.getOriginalFilename());

        // Build sub file path
        String subFilePath = (subPath + "/" + basename + '.' + extension).replace("//", "/");

        subFilePath = (subPath + "/" + originalBasename).replace("//", "/");
        // Get upload path
        Path uploadPath = Paths.get(properties.getPath().getWorkDir(), properties.getPath().getUpload(), subFilePath);

        logger.info("Uploading to directory: [{}]", uploadPath.toString());
        try {
            cn.hutool.core.io.FileUtil.del(uploadPath);
            FileUtil.transferTo(file, uploadPath);

            // Build upload result
            AttachmentEntity atta = new AttachmentEntity();
            atta.setTenantId(0l);
            atta.setUserId(userId);
            atta.setObjectId(objectId);
            atta.setContentType(file.getContentType());
            atta.setCreateTime(new Date());
            atta.setSize(file.getSize());
            atta.setDiskFilename(subFilePath);
            atta.setFilename(originalBasename);
            atta.setExtension(originalBasename.substring(originalBasename.lastIndexOf(".") + 1));
            atta.setMediaType(MediaType.valueOf(Objects.requireNonNull(file.getContentType())).toString());
            atta.setFolder(folder);

            // Check file type
            return atta;
        } catch (IOException e) {
            logger.error("Failed to upload file to local: " + uploadPath, e);
            throw new ApplicationException(e.getMessage());
        }
    }

    @Override
    public boolean remove(String file) {
        Path filePath = Paths.get(properties.getPath().getWorkDir(), properties.getPath().getUpload(), file);
        return cn.hutool.core.io.FileUtil.del(filePath.toString());
    }
}
