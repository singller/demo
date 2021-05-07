package com.zjx.demo.blockQueue;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 延时队列demo
 */
public class DelayQueueDemo {


    public static void main(String[] args) throws InterruptedException {
        ChannelTask channelTask = new ChannelTask("1234567890", 5, TimeUnit.SECONDS);
        DelayQueue<ChannelTask> delayQueue = new DelayQueue<ChannelTask>();
        delayQueue.put(channelTask);

        System.out.println("订单延迟队列开始时间:" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        while (delayQueue.size() != 0) {
            /**
             * 取队列头部元素是否过期
             */
            ChannelTask task = delayQueue.poll();
            if (task != null) {
                System.out.format("订单:{%s}被取消, 取消时间:{%s}\n", task.getFinChannelId(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            }
            Thread.sleep(1000);
        }

    }
}

@Getter
@Setter
class ChannelTask implements Delayed {
    private String finChannelId;
    private long time;


    public ChannelTask(String finChannelId, long time, TimeUnit unit) {
        this.finChannelId = finChannelId;
        this.time = System.currentTimeMillis() + (time > 0 ? unit.toMillis(time) : 0);
    }


    @Override
    public long getDelay(TimeUnit unit) {
        return time - System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed o) {
        ChannelTask channelTask = (ChannelTask) o;
        long diff = this.time - channelTask.time;
        if (diff <= 0) {
            return -1;
        } else {
            return 1;
        }
    }

}