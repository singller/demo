package com.zjx.demo.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreMutex;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreV2;
import org.apache.curator.framework.recipes.locks.Lease;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @author 65454
 */
public class CuratorLock {

    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(
                "192.168.31.184:2181,192.168.31.207:2181,192.168.31.192:2181",
                retryPolicy);
        client.start();

        client.create()
                .creatingParentsIfNeeded()
                .forPath("/user/dir", "hello world".getBytes());

        System.out.println(new String(client.getData().forPath("/my/path")));

        /*  可重入锁 */
        InterProcessMutex lock = new InterProcessMutex(client, "/locks/lock_01");
        lock.acquire();
        lock.release();


        /*  信号量 */
        InterProcessSemaphoreV2 smeaphore = new InterProcessSemaphoreV2(client, "/semaphores/semaphore_01", 3);

        Lease acquire = smeaphore.acquire();
        smeaphore.returnLease(acquire);

        /*不可重入锁   基于semaphore实现   允许一个客户端加锁*/
        InterProcessSemaphoreMutex interProcessSemaphoreMutex = new InterProcessSemaphoreMutex(client, "/locks/lock_02");
        interProcessSemaphoreMutex.acquire();
        interProcessSemaphoreMutex.release();

    }
}
