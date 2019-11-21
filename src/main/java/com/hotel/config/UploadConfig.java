package com.hotel.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

/**
 * @author 林晓锋
 * @date 2019/10/14
 * modified: 2019/10/14
 * 功能：上传图片大小
 */

@Configuration
public class UploadConfig {
    //    设置上传文件限制
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory config = new MultipartConfigFactory();
        //单个文件大小
        config.setMaxFileSize("10MB");
        //批量上传文件总大小
        config.setMaxRequestSize("100MB");
        return config.createMultipartConfig();
    }

}
