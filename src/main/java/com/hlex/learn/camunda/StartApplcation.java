package com.hlex.learn.camunda;

import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class StartApplcation {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    public static void main(String[] args) {
        SpringApplication.run(StartApplcation.class);
    }

    @EventListener
    public void ready(PostDeployEvent postDeployEvent){
        logger.info("ready to run!");
    }


}
