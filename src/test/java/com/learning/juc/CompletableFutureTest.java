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
    public void test() {
        CompletableFuture.runAsync(() -> {
            printLog("runAsync");
        }, executorService).thenAccept((e) -> {
            printLog("thenAccept");
        }).whenComplete((v, e) -> {
            printLog("whenComplete");
        });

        wait(5 * 1000);
    }

    @Test
    public void test02() {
        CompletableFuture future01 = CompletableFuture.runAsync(() -> {
            printLog("future01");
        }, executorService);

        CompletableFuture future02 = CompletableFuture.runAsync(() -> {
            printLog("future02");
        }, executorService);

        CompletableFuture future03 = CompletableFuture.runAsync(() -> {
            printLog("future03");
        }, executorService);

        CompletableFuture future04 = CompletableFuture.runAsync(() -> {
            printLog("future04");
        }, executorService);

        CompletableFuture future05 = CompletableFuture.runAsync(() -> {
            printLog("future05");
        }, executorService);

        CompletableFuture future06 = CompletableFuture.runAsync(() -> {
            printLog("future06");
            int i = 10 / 0;
        }, executorService);

        CompletableFuture.allOf(future01, future02, future03, future04, future05, future06)
                .exceptionally((e) -> {
                    return null;
                })
                .handle((v, e) -> {
                    System.out.println("finished " + e);
                    return "finished";
                });

        System.out.println("----------------------------");
        wait(5 * 1000);
    }

    private void printLog(String msg) {
        sleep(new Random().nextInt(400));
        logger.info("threadName:{}, msg:{}", Thread.currentThread().getName(), msg);
    }
}
