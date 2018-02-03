package com.hlex.learn.camunda.bean;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class GetMessage implements JavaDelegate {
    public  static AtomicInteger atomicInteger=new AtomicInteger();
    private final Logger logger = LoggerFactory.getLogger(getClass());
    public void execute(DelegateExecution delegateExecution) throws Exception {
        logger.debug("Get Message! {}",System.currentTimeMillis());
        atomicInteger.incrementAndGet();
    }
}
