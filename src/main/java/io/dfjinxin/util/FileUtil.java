package io.dfjinxin.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileUtil {

    public static void transferTo(MultipartFile file, Path path) throws IOException {
        Files.createDirectories(path.getParent());
        Files.createFile(path);

        file.transferTo(path);
        log.info("file transferTo {}", path);
    }
}
