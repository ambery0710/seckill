package com.jiuzhang.seckill.services;

import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

@Service
public class RedisService {
    @Resource
    private JedisPool jedisPool;
    /**
     * 设置值
     *
     * @param key
     * * @param value
     * */
    public RedisService setValue(String key, Long value) {
        Jedis jedisClient = jedisPool.getResource();
        jedisClient.set(key, value.toString());
        jedisClient.close();
        return this;
    }
    /**
     * 获取值
     *
     * @param key
     * * @return
     * */
    public String getValue(String key) {
        Jedis jedisClient = jedisPool.getResource();
        String value = jedisClient.get(key);
        jedisClient.close();
        return value;
    }
}
