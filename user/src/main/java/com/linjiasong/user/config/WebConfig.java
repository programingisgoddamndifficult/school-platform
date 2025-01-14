package com.linjiasong.user.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.linjiasong.user.interceptor.UserInfoInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author linjiasong
 * @date 2025/01/14 23:36
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private UserInfoInterceptor userInfoInterceptor;

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 1. 定义一个convert转换消息的对象
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

        // 2. 添加fastjson的配置信息
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.PrettyFormat,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteDateUseDateFormat
        );
        fastConverter.setFastJsonConfig(fastJsonConfig);

        // 3. 处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastConverter.setSupportedMediaTypes(fastMediaTypes);

        // 4. 在converters中添加fastjson的配置信息
        converters.add(0, fastConverter);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器并添加拦截路径，这里对所有请求进行拦截
        registry.addInterceptor(userInfoInterceptor).addPathPatterns("/**");
    }
}
