package peipeia.club.community.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Value("${file.accessPath}")
    String accessPath;
    @Value("${file.uploadPath}")
    String uploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        //据说是Springboot版本的原因，反正我用的是2.0.5，在绝对路径映射上需要加上file:，否则映射失败！
        registry.addResourceHandler(accessPath+"**").addResourceLocations("file:"+uploadPath);
    }
}
