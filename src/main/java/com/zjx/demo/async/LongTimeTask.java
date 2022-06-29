package com.zjx.demo.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.TimeUnit;

/**
 * @author zjx
 */
@Component
@Slf4j
public class LongTimeTask {
    @Async
    public void execute(DeferredResult<String> deferred) {
        log.info(Thread.currentThread().getName() + "进入 taskService 的 execute方法");
        try {
            // 模拟长时间任务调用，睡眠2s
            TimeUnit.SECONDS.sleep(2);
            // 2s后给Deferred发送成功消息，告诉Deferred，我这边已经处理完了，可以返回给客户端了
            deferred.setResult("world");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
