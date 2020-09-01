//package io.dfjinxin.modules.auth.filter;
//
//import io.dfjinxin.component.AppPathProperties;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//
//import java.io.File;
//
//@Configuration
//public class InterceptorConfig extends WebMvcConfigurationSupport {
//
//    @Autowired
//    private AppPathProperties pathProperties;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry){
//        LoginInterceptor loginInterceptor = new LoginInterceptor();
//        registry.addInterceptor(loginInterceptor).addPathPatterns("/**")
//                .excludePathPatterns("/loginback")
//                .excludePathPatterns("/logincas");
//        super.addInterceptors(registry);
//    }
//
//    @Override
//    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
//        registry.addResourceHandler("/upload/**").addResourceLocations("file:" + pathProperties.getWorkDir() + pathProperties.getUpload() + File.separator);
//        super.addResourceHandlers(registry);
//    }
//}
