package com.learning;

import org.junit.Before;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

/**
 * @author lifang
 * @since 2021/1/14
 */
public class BaseTest {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(BaseTest.class);


    @BeforeTest
    public void beforeTest(){
        logger.info("************************************************************************************************");
    }

    @AfterTest
    public void afterTest(){
        logger.info("************************************************************************************************");
    }
}
