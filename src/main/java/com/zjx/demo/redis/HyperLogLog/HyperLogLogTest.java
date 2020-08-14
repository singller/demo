package com.zjx.demo.redis.HyperLogLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.redis.HyperLogLog
 * @date:2020/8/14
 **/
@Service
public class HyperLogLogTest {
    static class BitKeeper{
        private int maxbit;

        public void random() {
            long value = ThreadLocalRandom.current().nextLong(2L << 32);
            int bit = lowZeros(value);
            if (bit > this.maxbit) {
                this.maxbit = bit;
            }
        }

        private int lowZeros(long value) {
            int i = 0;
            for (; i < 32; i++) {
                if (value >> i << i != value) {
                    break;
                }
            }
            return i - 1;
        }
    }



    static class Experiment {

        private int n;
        private BitKeeper keeper;

        public Experiment(int n) {
            this.n = n;
            this.keeper = new BitKeeper();
        }

        public void work() {
            for (int i = 0; i < n; i++) {
                this.keeper.random();
            }
        }

        public void debug() {
            System.out
                    .printf("%d %.2f %d\n", this.n, Math.log(this.n) / Math.log(2), this.keeper.maxbit);
        }
    }


    public static void main(String[] args) {
        for (int i = 1000; i < 100000; i += 100) {
            Experiment exp = new Experiment(i);
            exp.work();
            exp.debug();
        }
    }

    @Autowired
    RedisTemplate redisTemplate;

    public  void count(){
        String uv1 = "uv96";
        String uv2 = "uv97";

        //模拟点击数
        IntStream.rangeClosed(1,100)
                .forEach(i -> {
                    System.out.println(i);
                    redisTemplate.opsForHyperLogLog()
                            .add(uv1,"user"+i);
                    redisTemplate.opsForHyperLogLog()
                            .add(uv2,"user"+i/2);
                });

        //uv1的点击数
        long uv1Count = redisTemplate.opsForHyperLogLog().size(uv1);
        System.out.println(uv1+"------"+uv1Count);

        //uv2的点击数
        long uv2Count = redisTemplate.opsForHyperLogLog().size(uv2);
        System.out.println(uv2+"------"+uv2Count);

        String uv1uv2 = "uv67";
        Long uv1uv2Count = redisTemplate.opsForHyperLogLog().union(uv1uv2,uv1,uv2);
        System.out.println("union----"+uv1uv2Count);
        Long realCount = redisTemplate.opsForHyperLogLog().size(uv1uv2);
        System.out.println(uv1uv2+"======="+realCount);
    }

}
