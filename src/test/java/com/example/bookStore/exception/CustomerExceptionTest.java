package com.example.bookStore.exception;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yuanlei
 * @description
 * @date 2021-06-04
 */
public class CustomerExceptionTest {
    private static final Logger log = LoggerFactory.getLogger(CustomerExceptionTest.class);
    /**
     * @Author yuanlei
     * @Description //发起调用方，测试调用一个会主动抛出异常的方法
     * @Date 10:38 2021/6/4
     * @Param []
     * @return void
     **/
    @Test
    public void testCaller() throws Exception {
        log.info("caller begin!");
        exceptionMethod();
        log.info("caller end!");
    }

    private void exceptionMethod() throws Exception {
        log.info("exceptionMethod begin!");
        try {
            int i = 1/0;
            log.info("exceptionMethod running!");
        }catch (Exception e){
            log.error("",e);
            throw new Exception(e);
        }
        log.info("exceptionMethod execute!");
    }
}
