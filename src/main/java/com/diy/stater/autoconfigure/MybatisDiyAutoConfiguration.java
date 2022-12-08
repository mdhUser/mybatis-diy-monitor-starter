package com.diy.stater.autoconfigure;

import com.diy.stater.constant.ConstantConfig;
import com.diy.stater.interceptor.MybatisDivInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author levi
 * @Description //TODO
 * @Param  * @param null
 * @return
 **/
@Configuration
@EnableConfigurationProperties(MybatisDivProperties.class)
@ConditionalOnProperty(prefix = ConstantConfig.DIY_PREFIX, value = "enable", havingValue = "true", matchIfMissing = false)
public class MybatisDiyAutoConfiguration{

    @Bean
    public MybatisDivInterceptor mybatisDivInterceptor() {
        return new MybatisDivInterceptor();
    }
}

