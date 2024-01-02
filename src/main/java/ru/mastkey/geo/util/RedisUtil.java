package ru.mastkey.geo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

@Component
public class RedisUtil implements CacheDBConnector {
    public static final long THIRTY_DAYS_IN_SECONDS = 2592000;

    private final Jedis jedis;

    @Autowired
    public RedisUtil(Jedis jedis) {
        this.jedis = jedis;
    }

    @Override
    public String getValueByKey(String key) {
        try {
            return jedis.get(key);
        } catch (JedisConnectionException e) {
            throw new RuntimeException("Failed to get value from Redis", e);
        }
    }

    @Override
    public void saveValueByKey(String key, String value){
        try {
            jedis.setex(key, THIRTY_DAYS_IN_SECONDS, value);
        } catch (JedisConnectionException e) {
            throw new RuntimeException("Failed to save value to Redis", e);
        }
    }
}
