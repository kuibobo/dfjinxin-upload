package io.dfjinxin;

import io.dfjinxin.component.AppProperties;
import io.dfjinxin.event.listener.ApplicationPreparedListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@EnableConfigurationProperties({AppProperties.class})
@SpringBootApplication(scanBasePackages = {
        "io.dfjinxin"
})
public class DfjinxinUploadApplication extends SpringBootServletInitializer  {

    public static void main(String[] args){
        SpringApplication app = new SpringApplication(DfjinxinUploadApplication.class);
        app.addListeners(
                new ApplicationPreparedListener()
        );
        app.run(args);
    }

    //为了打包springboot项目
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        builder.listeners(new ApplicationPreparedListener());
        return builder.sources(getClass());
    }

}
