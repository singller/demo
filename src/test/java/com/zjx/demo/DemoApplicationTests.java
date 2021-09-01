package com.zjx.demo;

import com.zjx.demo.base.Kafaka;
import com.zjx.demo.mqconfig.Customer;
import com.zjx.demo.mqconfig.Producer;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private Producer producer;

    @Autowired
    private Customer customer;

    @Test
    void send() {
        ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>(5);
        objectObjectHashMap.put(1, 1);
    }


    @Autowired
    private Kafaka kafaka;

    @Test
    void kafaTest() {
        kafaka.parser();
    }

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisTemplate<String,String> redisTemplateString;

    @Test
    public void test01() {
        SetOperations set = redisTemplate.opsForSet();

        set.add("zd", "1", "2", "3", "6");
        set.add("bg", "1", "2", "3", "4", "5");

        //差集，获取“bg”键有，而“zd”键没有的元素
        Set differenceBg = set.difference("bg", "zd");
        System.out.println(differenceBg);//[4,5]

        //差集，获取“bg”键有，而“zd”键没有的元素
        Set differenceZd = set.difference("zd", "bg");
        System.out.println(differenceZd);//[6]

        //交集,获取“bg”键和“zd”键两个键的共有元素
        Set intersect = set.intersect("bg", "zd");
        System.out.println(intersect);//[1, 2, 3]

        //并集，获取“bg”键和“zd”键两个键下的所有元素，排除重合元素
        Set union = set.union("bg", "zd");
        System.out.println(union);//[6, 1, 2, 3, 4, 5]

        //查询bg中的元素
        Set bg = set.members("bg");
        System.out.println(bg);

        //判断bg中 1 是否存在
        Boolean bg1 = set.isMember("bg", "1");
        System.out.println(bg1);

        //使用管道进行批量操作
        String key = "bgset";
        List<String> list = Arrays.asList("1", "2", "3", "4", "6");
        redisTemplate.executePipelined(new RedisCallback<List<String>>() {
            @Override
            public List<String> doInRedis(RedisConnection redisConnection) throws DataAccessException {
                byte[] serialize = redisTemplate.getKeySerializer().serialize(key);

                for (String str : list) {
                    byte[] val = redisTemplate.getKeySerializer().serialize(str);
                    redisConnection.sAdd(serialize, val);
                }
                return null;
            }
        });

        Set members = set.members(key);
        System.out.println(members);

    }

    @Test
    public void test02() {
        SetOperations set = redisTemplate.opsForSet();

        //添加集合d
        set.add("set-d", "a", "b", "c", "d");
        //添加集合e
        set.add("set-e", "c", "d", "e", "f");
        //添加集合f
        set.add("set-f", "e", "f", "g", "h");

        //求差集(属于d不属于e和f)
        Set<String> difference = set.difference("set-d", Arrays.asList("set-e", "set-f"));
        System.out.println(difference);
    }


    @Test
    public void testPipelined() {
        ArrayList<String> strList = Lists.newArrayList();
        for (int i = 0; i < 100000; i++) {
            strList.add(i + ":" + i);
        }
        String key = "platformFlow:20210831:scb:payout";
//        String key = "channelFlow:20210831:scb:payout";

        redisTemplate.executePipelined(new RedisCallback<List<String>>() {
            @Override
            public List<String> doInRedis(RedisConnection redisConnection) throws DataAccessException {
                byte[] serialize = redisTemplate.getKeySerializer().serialize(key);

                for (String str : strList) {
                    byte[] val = redisTemplate.getKeySerializer().serialize(str);
                    redisConnection.sAdd(serialize, val);
                }
                return null;
            }
        });
    }

    @Test
    public void testSet() {
        String platform = "platformFlow:20210831:scb:payout";
        String channelFlow = "channelFlow:20210831:scb:payout";

//        SetOperations set = redisTemplate.opsForSet();
//        Set<String> plf = set.difference(platform, channelFlow);
//        Set<String> org = set.difference(channelFlow, platform);
//
//        Set<String> flatSet = set.intersect(platform, channelFlow);

        System.out.println();

        String s = "1231234567";
        String o = redisTemplateString.opsForValue().get(s);
        System.out.println(o+"------------111");
        redisTemplateString.opsForValue().increment(s);
        o = redisTemplateString.opsForValue().get(s);
        System.out.println(o+"------------222");
    }


}
