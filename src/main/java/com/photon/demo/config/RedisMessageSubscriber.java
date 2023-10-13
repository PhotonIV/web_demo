package com.photon.demo.config;

/**
 * @author liuzaoxin
 * @description redis消息处理
 * @date 2023/05/31/ 13:52
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@Component
public class RedisMessageSubscriber {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public void handleMessage(String message) {


        logger.info("Received message: " + message);

        // 业务逻辑操作...

    }
}
