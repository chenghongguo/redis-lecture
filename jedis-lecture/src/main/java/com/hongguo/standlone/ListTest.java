package com.hongguo.standlone;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ListPosition;

import java.util.Comparator;
import java.util.List;

/**
 * @author hongguo_cheng
 * @date 2021/7/11
 */
public class ListTest {
    private static Jedis jedis;

    @BeforeClass
    public static void before() {
        jedis = new Jedis("localhost", 6379);
    }

    @AfterClass
    public static void after() {
        jedis = null;
    }

    @Test
    public void testLpush() {
        String lpushKey = "list-lpush";
        Long lpush = jedis.lpush(lpushKey, "a", "b", "c");
        System.out.println(lpush);

        // lpushx：key存在才推入
        Long lpushx = jedis.lpushx(lpushKey.concat("-non-exists"), "a", "d", "e");
        System.out.println(lpushx);
    }

    @Test
    public void testRpush() {
        String rpushKey = "list-rpush";
        Long rpush = jedis.rpush(rpushKey, "a", "中国", "韩国");
        System.out.println(rpush);
    }

    @Test
    public void testPop() {
        String key = "list-lpush";
        List<String> lpop = jedis.lpop(key, 2);
        System.out.println(lpop);

        List<String> rpop = jedis.rpop(key, 2);
        System.out.println(rpop);
    }

    @Test
    public void testLlen() {
        String key = "list-rpush";
        Long llen = jedis.llen(key);
        System.out.println(llen);
    }

    @Test
    public void testLindex() {
        String key = "list-lpush";
        String lindex = jedis.lindex(key, 3);
        System.out.println(lindex);
    }

    @Test
    public void testLtrim() {
        String key = "list-lpush";
        String ltrim = jedis.ltrim(key, 0, 3);
        System.out.println(ltrim);
    }

    @Test
    public void testLrem() {
        String key = "list-lpush";
        Long a = jedis.lrem(key, 2, "b");
        System.out.println(a);
    }

    @Test
    public void testLinsert() {
        String key = "list-lpush";
        Long linsert = jedis.linsert(key, ListPosition.AFTER, "a", "ff");
        System.out.println(linsert);
    }

    @Test
    public void testRpopLpush() {
        String key = "list-lpush";
        String rpoplpush = jedis.rpoplpush(key, "list-rpush");
        System.out.println(rpoplpush);
    }

    @Test
    public void testLrange() {
        String key = "list-lpush";
        List<String> lrange = jedis.lrange(key, 0, -1);
        lrange.sort(Comparator.naturalOrder());
        System.out.println(lrange);
    }
}
