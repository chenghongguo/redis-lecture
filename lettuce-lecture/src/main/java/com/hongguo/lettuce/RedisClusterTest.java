package com.hongguo.lettuce;

import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.cluster.api.sync.RedisAdvancedClusterCommands;

/**
 * @author hongguo_cheng
 * @date 2021/8/14
 */
public class RedisClusterTest {
    public static void main(String[] args) {
        RedisClusterClient cluster = RedisClusterClient.create("redis://127.0.0.1:30001");
        StatefulRedisClusterConnection<String, String> connect = cluster.connect();

        RedisAdvancedClusterCommands<String, String> sync = connect.sync();

        String set = sync.set("hello-cluster", "cluster-----");
        System.out.println(set);

        String s = sync.get("hello-cluster");
        System.out.println(s);

        connect.close();
        cluster.shutdown();
    }
}
