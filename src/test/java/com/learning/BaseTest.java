package com.learning;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author lifang
 * @since 2021/1/14
 */
@Test()
public class BaseTest {

    private long startTime;

    public static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(BaseTest.class);

    @BeforeTest
    public void beforeTest(){
        startTime = System.currentTimeMillis();
        logger.info("************************************************************************************************");
    }

    @AfterTest
    public void afterTest(){
        long endTime = System.currentTimeMillis();
        logger.info("耗时：{}s", (endTime - startTime) / 1000.0F);
        logger.info("************************************************************************************************");
    }

    protected final void sleep(int milliseconds){
        sleep(milliseconds, true);
    }

    protected final void sleep(int milliseconds, boolean ignoreException){
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            if(ignoreException) {
                logger.error(e.getMessage(), e);
            }else{
                throw new RuntimeException(e.getMessage(), e);
            }
        }
    }

    protected final void wait(int milliseconds){
        logger.info("waiting");
        int total = 0;
        while (true){
            total += 10;
            sleep(10);
            if(total >= milliseconds){
                break;
            }
        }
    }
}
