package com.hongguo.lettuce;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

/**
 * @author hongguo_cheng
 * @date 2021/8/14
 */
public class RedisTest {

    public static void main(String[] args) {
        RedisClient redisClient = RedisClient.create("redis://127.0.0.1:6379/0");
        StatefulRedisConnection<String, String> connect = redisClient.connect();

        RedisCommands<String, String> sync = connect.sync();

        String set = sync.set("key-1", "hello Redis!");
        System.out.println(set);

        String s = sync.get("key-1");
        System.out.println(s);

        connect.close();

        redisClient.shutdown();
    }
}
