package io.dfjinxin.config;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModelException;
import io.dfjinxin.component.WebFreeMarkerView;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class FreemarkerConfig {

    @Bean
    public FreeMarkerViewResolver freemarkerViewResolver() {
        FreeMarkerViewResolver bean = new FreeMarkerViewResolver();
        bean.setOrder(0);
        bean.setViewClass(WebFreeMarkerView.class);
        bean.setRequestContextAttribute("request");
        bean.setContentType("text/html;charset=UTF-8");
        bean.setSuffix(".ftl");
        return bean;
    }

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer() throws TemplateModelException {
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        configurer.setTemplateLoaderPaths("classpath:/templates");
        configurer.setPreferFileSystemAccess(false);

        Map<String, Object> variables = new HashMap<>(1);
        variables.put("from", "");

        BeansWrapper wrapper = BeansWrapper.getDefaultInstance();
        TemplateHashModel factory = wrapper.getStaticModels();
        TemplateHashModel staticObj;


        configurer.setFreemarkerVariables(variables);

        Properties settings = new Properties();
        settings.setProperty("default_encoding", "utf-8");
        settings.setProperty("number_format", "0.##");
        settings.setProperty("template_exception_handler", "io.dfjinxin.handler.FreemarkerExceptionHandler");
        settings.setProperty("template_update_delay", "0");
        settings.setProperty("output_encoding", "utf-8");
        configurer.setFreemarkerSettings(settings);
        return configurer;
    }
}
