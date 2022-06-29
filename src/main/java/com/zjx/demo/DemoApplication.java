package com.zjx.demo;

import com.zjx.demo.springstatemachine.Events;
import com.zjx.demo.springstatemachine.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.statemachine.StateMachine;

/**
 * @author 65454
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class DemoApplication implements CommandLineRunner {

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);
    }

    @Autowired
    private StateMachine<States, Events> stateMachine;

    @Override
    public void run(String... args) throws Exception {
        stateMachine.sendEvent(Events.E1);
        stateMachine.sendEvent(Events.E2);
    }

}
