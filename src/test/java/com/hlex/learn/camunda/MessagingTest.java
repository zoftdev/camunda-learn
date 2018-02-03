package com.hlex.learn.camunda;

import com.google.common.collect.ImmutableMap;
import org.camunda.bpm.engine.RuntimeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MessagingTest {

    @Autowired
    RuntimeService runtimeService;


    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void createLoop(){
        int cnt=1000;
        int []i=new int[1];
        i[0]=0;
        monitorCount(i);
        for (; i[0] <cnt ; i[0]++) {
            runtimeService.startProcessInstanceByKey("loop-wait-message", UUID.randomUUID().toString(),  (ImmutableMap.of("k1", (Object)"v1", "k2", "v2")));
        }

    }

    private void monitorCount(final int[] i) {
        new Thread(new Runnable() {
            public void run() {
                while(true) {
                    logger.info("wait , current cnt:" + i[0]);
                    sleep(1000);
                }
            }
        }).start();
    }

    private void sleep(int sleepMSec) {
        try {
            Thread.sleep(sleepMSec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
