package com.hongguo.redisson;

import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * @author hongguo_cheng
 * @date 2021/8/14
 */
public class RedissonTest {

    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://127.0.0.1:6379");

        RedissonClient client = Redisson.create(config);
        RBucket<Object> hello = client.getBucket("hello");
        hello.set("world world");
        Object o = hello.get();
        System.out.println(o);

        client.shutdown();
    }
}
