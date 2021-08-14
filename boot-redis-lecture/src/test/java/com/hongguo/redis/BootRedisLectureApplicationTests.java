package com.hongguo.redis;

import com.hongguo.redis.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class BootRedisLectureApplicationTests {

    @Autowired
    private RedisTemplate<String, String> stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, Serializable> serializableRedisTemplate;

    @Test
    void testString() {
        stringRedisTemplate.opsForValue().set("hello", "world from lettuce");
        System.out.println(stringRedisTemplate.opsForValue().get("hello"));
    }

    @Test
    void testSerializable() {
        User user = new User();
        user.setId(100L);
        user.setName("hongguo");
        user.setAge(30);
        serializableRedisTemplate.opsForValue().set("user:hongguo", user);

        User userFromRedis = (User) serializableRedisTemplate.opsForValue().get("user:hongguo");
        System.out.println(userFromRedis);
    }

    @Test
    void testHash() {
        Map<String, Object> user = new HashMap<>();
        user.put("id", 100);
        user.put("name", "hongguo");
        serializableRedisTemplate.opsForHash().putAll("user:hongguo:100", user);

        Map<Object, Object> entries = serializableRedisTemplate.opsForHash().entries("user:hongguo:100");
        System.out.println(entries);
    }

    @Test
    public void testHashString() {
        Map<String, String> user = new HashMap<>();
        user.put("id", "200");
        user.put("name", "hongguo");
        stringRedisTemplate.opsForHash().putAll("user:hongguo:200", user);

        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries("user:hongguo:200");
        System.out.println(entries);
    }

}
