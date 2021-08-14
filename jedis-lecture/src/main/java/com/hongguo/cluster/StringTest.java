package com.hongguo.cluster;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author hongguo_cheng
 * @date 2021/8/14
 */
public class StringTest {

    private static JedisCluster cluster;

    private final static String HOST = "127.0.0.1";

    private final static List<Integer> MASTER_PORTS = Arrays.asList(30001, 30002, 30003, 30004, 30005);
    private final static List<Integer> REPLICA_PORTS = Arrays.asList(30006, 30007, 30008, 30009, 30010);

    @BeforeClass
    public static void init() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(5);
        config.setMaxIdle(2);
        config.setMaxWaitMillis(2000);

        Set<HostAndPort> nodes = new HashSet<>();
        REPLICA_PORTS.forEach(port -> nodes.add(new HostAndPort(HOST, port)));

        cluster = new JedisCluster(nodes, config);
    }

    @AfterClass
    public static void close() {
        if (cluster != null) {
            try {
                cluster.close();
            } catch (Exception e) {
                // ignore
            }
        }
    }

    @Test
    public void testString() {
        String set = cluster.set("hello", "world");
        System.out.println(set);

        String hello = cluster.get("hello");
        System.out.println(hello);
    }
}
