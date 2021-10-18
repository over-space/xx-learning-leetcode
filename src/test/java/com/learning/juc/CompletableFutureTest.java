package com.learning.juc;

import com.learning.BaseTest;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lifang
 * @since 2021/10/14
 */
public class CompletableFutureTest extends BaseTest {

    private ExecutorService executorService = Executors.newFixedThreadPool(5);

    @Test
    public void test(){
        CompletableFuture.runAsync(() -> {
            printLog("runAsync");
        }, executorService).thenAccept((e) -> {
            printLog("thenAccept");
        }).whenComplete((v, e) -> {
            printLog("whenComplete");
        });

        wait(5 * 1000);
    }

    private void printLog(String msg){
        sleep(new Random().nextInt(400));
        logger.info("threadName:{}, msg:{}", Thread.currentThread().getName(), msg);
    }
}
