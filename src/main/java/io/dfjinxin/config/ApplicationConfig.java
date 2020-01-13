package io.dfjinxin.config;

import io.dfjinxin.component.AppPathProperties;
import io.dfjinxin.component.AppProperties;
import org.springframework.boot.autoconfigure.context.MessageSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableConfigurationProperties({AppProperties.class, AppPathProperties.class})
public class ApplicationConfig {

    @Bean
    @ConfigurationProperties(
            prefix = "spring.messages"
    )
    public MessageSourceProperties messageSourceProperties() {
        return new MessageSourceProperties();
    }

    @Bean(name = "messageSource")
    public ReloadableResourceBundleMessageSource messageSource(MessageSourceProperties properties, AppProperties appProperties) {
        ReloadableResourceBundleMessageSource messageBundle = new ReloadableResourceBundleMessageSource();

        // 默认配置
        String[] names = StringUtils.commaDelimitedListToStringArray(StringUtils.trimAllWhitespace(properties.getBasename()));
        List<String> newNames = new ArrayList();
        for (String name : names) {
            newNames.add("classpath:" + name);
        }
        messageBundle.setBasenames(newNames.stream().toArray(String[]::new));

        messageBundle.setDefaultEncoding("UTF-8");
        return messageBundle;
    }

}
