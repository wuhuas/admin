package com.knowledge.common.base.config;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.feilong.core.Validator;  

@Configuration  
@EnableCaching 
public class RedisConfig extends CachingConfigurerSupport {
	
	@Bean  
    public KeyGenerator keyGenerator() {  
        return new KeyGenerator() {  
            @Override  
            public Object generate(Object target, Method method, Object... params) {  
                StringBuilder sb = new StringBuilder();  
                sb.append(target.getClass().getSimpleName()).append(".");  
                sb.append(method.getName());  
                for (Object obj : params) {  
                	if(Validator.isNotNullOrEmpty(obj)){
                		sb.append("_").append(obj.toString());  
                	}
                }  
                return sb.toString();  
            }  
        };  
    }  
	  
	      
    @Bean  
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {  
    	 return new RedisCacheManager(
    	            RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory),
    	            this.getRedisCacheConfigurationWithTtl(60), // 默认策略，未配置的 key 会使用这个
    	            this.getRedisCacheConfigurationMap() // 指定 key 策略
    	        );
    }  
	    
    @Bean  
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {  
        StringRedisTemplate template = new StringRedisTemplate(factory);  
        template.setValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));  
        template.afterPropertiesSet();  
        return template;  
    }  
    
    private Map<String, RedisCacheConfiguration> getRedisCacheConfigurationMap() {
        Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>();
        redisCacheConfigurationMap.put("match", this.getRedisCacheConfigurationWithTtl(60*5));  //赛事缓存5分钟
        return redisCacheConfigurationMap;
    }

    private RedisCacheConfiguration getRedisCacheConfigurationWithTtl(Integer seconds) {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);  
        jackson2JsonRedisSerializer.setObjectMapper(om);

        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        redisCacheConfiguration = redisCacheConfiguration.serializeValuesWith(
            RedisSerializationContext
                .SerializationPair
                .fromSerializer(jackson2JsonRedisSerializer)
        ).entryTtl(Duration.ofSeconds(seconds));

        return redisCacheConfiguration;
    }
}
