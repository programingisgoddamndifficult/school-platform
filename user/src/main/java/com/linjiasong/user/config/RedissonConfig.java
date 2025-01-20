package com.linjiasong.user.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author linjiasong
 * @date 2025/1/20 下午3:11
 */
@Configuration
public class RedissonConfig {
    @Value("${spring.redis.host}")
    private String redisLoginHost;
    @Value("${spring.redis.port}")
    private Integer redisLoginPort;
    @Value("${spring.redis.password}")
    private String redisLoginPassword;


    @Bean
    public RedissonClient redissonClient() {
        return createRedis(redisLoginHost, redisLoginPort, redisLoginPassword);
    }

    private RedissonClient createRedis(String redisHost, Integer redisPort, String redisPassword) {
        Config config = new Config();
        SingleServerConfig singleServerConfig = config.useSingleServer();
        singleServerConfig.setAddress("redis://" + redisHost + ":" + redisPort + "");
        singleServerConfig.setPassword(redisPassword);
        config.setCodec(new JsonJacksonCodec());
        return Redisson.create(config);
    }
}
