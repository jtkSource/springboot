package jtk.springboot.cache.config;

import com.google.common.cache.CacheBuilder;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Created by jubin on 31/12/16.
 */
@Configuration
@EnableCaching
public class AuthServerCacheConfig {

    @Bean
    public CacheManager cacheManager(){
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        GuavaCache userCache = new GuavaCache("usersCache", CacheBuilder.newBuilder()
                .maximumSize(100).expireAfterAccess(2, TimeUnit.HOURS).build());
        cacheManager.setCaches(Arrays.asList(userCache));
        return cacheManager;
    }

}
