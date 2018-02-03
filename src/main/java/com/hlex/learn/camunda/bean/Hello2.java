package com.hlex.learn.camunda.bean;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Hello2  implements JavaDelegate{
    private final Logger logger = LoggerFactory.getLogger(getClass());
    public void execute(DelegateExecution delegateExecution) throws Exception {
        logger.debug("hello2 {}",System.currentTimeMillis());
    }
}
