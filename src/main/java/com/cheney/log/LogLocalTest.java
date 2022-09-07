package com.cheney.log;

import java.util.Collections;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author linch
 * @create 2021/12/14 15:02
 */
public class LogLocalTest {

    public static void main(String[] args) {
//        Logger logger1 = Logger.getLogger("com.cheney.log.LogLocalTest");
//        logger1.info("test Log");
        Logger logger = Logger.getLogger("com.cheney.log.LogLocalTest", "LogLocalTest");
        logger.log(Level.INFO, "readFile", "my read 0");
        ResourceBundle logLocalTest = ResourceBundle.getBundle("LogLocalTest");
        Logger logger1 = Logger.getLogger("com.cheney.log.LogLocalTest");
        logger1.log(Level.INFO, "LogLocalTest --------- " + logLocalTest.getBaseBundleName());

//        Collections.sort();
    }
}
