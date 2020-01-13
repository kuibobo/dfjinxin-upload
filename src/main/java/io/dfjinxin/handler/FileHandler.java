package io.dfjinxin.handler;


import io.dfjinxin.modules.upload.entity.AttachmentEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

public abstract class FileHandler {

    static MediaType IMAGE_TYPE = MediaType.valueOf("image/*");


    @NonNull
    public abstract AttachmentEntity upload(@NonNull MultipartFile file, String folder, Long userId, Long objectId);

    static boolean isImageType(@Nullable MediaType mediaType) {
        return mediaType != null && IMAGE_TYPE.includes(mediaType);
    }

    @NonNull
    static String normalizeDirectory(@NonNull String dir) {
        Assert.hasText(dir, "Directory full name must not be blank");

        return StringUtils.appendIfMissing(dir, java.io.File.separator);
    }
}
