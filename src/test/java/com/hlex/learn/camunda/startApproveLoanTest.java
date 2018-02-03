package com.hlex.learn.camunda;

import com.google.common.collect.ImmutableMap;
import com.hlex.learn.camunda.bean.GetMessage;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class startApproveLoanTest {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    RuntimeService runtimeService;

//    @Ignore
    @Test
    public void addValueShouldSeHello2x2time() {
        runtimeService.startProcessInstanceByKey("add-value", UUID.randomUUID().toString(),  (ImmutableMap.of("k1", (Object)"v1", "k2", "v2")));

    }


    @Test
    public void singalDummy() {
        long signalEventReceivedTime=initTime();
        runtimeService.signalEventReceived("dummyMessage");
        logTime(signalEventReceivedTime,"dummyMessage time");

    }

    @Test
    public void createLotOfInstantAndGetSignalToCompleteFlow() {

        int cnt=100;
        long insertTime=initTime();
        for (int i = 0; i <cnt ; i++) {
            runtimeService.startProcessInstanceByKey("wait-message", UUID.randomUUID().toString(),  (ImmutableMap.of("k1", (Object)"v1", "k2", "v2")));
        }
        logTime(insertTime,"add data"+cnt+" rows");
        monitorCount();
        long signalEventReceivedTime=initTime();
        runtimeService.signalEventReceived("broadcastMessage1");
        logTime(signalEventReceivedTime,"signalEventReceived");
//        long getMessageTime=initTime();
//
//        while(
//                (GetMessage.atomicInteger.get()<cnt)
//                ) {
//            sleep(100);
//        }
//        logTime(getMessageTime,"Get message complete");
    }




    @Test
    public void testLoopMessage(){
        runtimeService.startProcessInstanceByKey("loop-wait-message", UUID.randomUUID().toString(),  (ImmutableMap.of("k1", (Object)"v1", "k2", "v2")));
        runtimeService.signalEventReceived("loop-wait-message-wakeup");
        runtimeService.signalEventReceived("loop-wait-message-wakeup");
        runtimeService.signalEventReceived("loop-wait-message-wakeup");

    }




    private void monitorCount() {
        new Thread(new Runnable() {
            public void run() {
                while(true) {
                    logger.info("wait , current cnt:" + GetMessage.atomicInteger.get());
                    sleep(1000);
                }
            }
        }).start();
    }

    private void logTime(long l,String caption) {
        logger.info("{} :use time:{} msec",caption,(System.currentTimeMillis()-l));
    }

    private long initTime() {
        return System.currentTimeMillis();
    }

    private void sleep(int sleepMSec) {
        try {
            Thread.sleep(sleepMSec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}