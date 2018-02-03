package com.hlex;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloClass implements JavaDelegate {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    public void execute(DelegateExecution delegateExecution) throws Exception {
        logger.info("hello {}",this);
    }
}
