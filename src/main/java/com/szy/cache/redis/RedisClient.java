package com.szy.cache.redis;

import com.szy.cache.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;

/**
 * redis操作类
 * Created by Administrator on 2016/10/16.
 */
public class RedisClient {

    @Autowired
    static JedisPool jedisPool;

    /**
     * 字符串增加
     * @param key
     * @param value
     */
    public static void addString(String key ,String value){
        Jedis redisClient = null;
        try {
            redisClient = jedisPool.getResource();
            value = RedisUtil.isNullOrEmpty(value) ? "" : value;
            redisClient.set(key,value);
        } catch (Exception e) {
            // 销毁对象
            jedisPool.returnBrokenResource(redisClient);
        }  finally {
            // 还原到连接池
            jedisPool.returnResource(redisClient);
        }
    }

    /**
     * 获取String值
     * @param key
     * @return value
     */
    public static String getString(String key){
        Jedis redisClient = null;
        try {
            redisClient = jedisPool.getResource();

            return redisClient.get(key);
        } catch (Exception e) {
            // 销毁对象
            jedisPool.returnBrokenResource(redisClient);
        }  finally {
// 还原到连接池
            jedisPool.returnResource(redisClient);
        }
        return null;
    }



    /**
     * 保存数据 类型为 Map
     * @param flag
     * @param mapData
     */
    public static void setMapDataToRedis(String flag,Map<String, String> mapData) {
        Jedis redisClient = null;
        try {
            redisClient = jedisPool.getResource();
            redisClient.hmset(flag, mapData);
        } catch (Exception e) {
// 销毁对象
            jedisPool.returnBrokenResource(redisClient);
        } finally {
// 还原到连接池
            jedisPool.returnResource(redisClient);
        }
    }


    /**
     * 保存数据 类型为 key-value
     * @param flag
     * @param field
     * @param value
     */
    public static void setDataToRedis(String flag, String field, String value) {
        Jedis redisClient = null;
        try {
            redisClient = jedisPool.getResource();
            redisClient.hset(flag, field, value);
        } catch (Exception e) {
            // 销毁对象
            jedisPool.returnBrokenResource(redisClient);
        } finally {
            // 还原到连接池
            jedisPool.returnResource(redisClient);
        }
    }


    /**
     * 获取Map数据
     * @param key
     * @return
     */
    public static Map<String, String> getMapData(String key) {
        Map<String, String> dataMap = null;


        Jedis redisClient = null;
        try {jedisPool.getResource();
            dataMap = redisClient.hgetAll(key);
        } catch (Exception e) {
            // 销毁对象
            jedisPool.returnBrokenResource(redisClient);
        } finally {
            // 还原到连接池
            jedisPool.returnResource(redisClient);
        }
        return dataMap;
    }


    /**
     * 删除
     * @param key
     * @return
     */
    public static long deleteData(String key) {
        long result = 0;
        Jedis redisClient = null;
        try {
            redisClient = jedisPool.getResource();
            result = redisClient.del(key);
        } catch (Exception e) {
// 销毁对象
            jedisPool.returnBrokenResource(redisClient);
        } finally {
// 还原到连接池
            jedisPool.returnResource(redisClient);
        }
        return result;
    }


    /**
     * 根据key和字段获取数据
     * @param key
     * @param value
     * @return
     */
    public static String getData(String key, String value) {
        String data = null;
        Jedis redisClient = null;
        try {
            redisClient = jedisPool.getResource();
            data = redisClient.hget(key, value);
        } catch (Exception e) {
            // 销毁对象
            jedisPool.returnBrokenResource(redisClient);
        } finally {
            // 还原到连接池
            jedisPool.returnResource(redisClient);
        }
        return data;
    }
}
