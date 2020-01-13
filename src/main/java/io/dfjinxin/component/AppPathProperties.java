package io.dfjinxin.component;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("app.path")
public class AppPathProperties {
    private String plugin;
    private String temp;
    private String upload;
    private String thumbnail;
    private String workDir;
    private String wechatCertFile;
}