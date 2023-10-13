package com.photon.demo.scheduling;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author liuzaoxin
 * @description
 * @date 2023/05/11/ 10:33
 */
@Component
public class ClockTask {


    // 每天23点执行
    @Scheduled(cron = "0 0 23 * * ?")
    public void cleanPushLog() {
        System.out.println();;
    }
}
