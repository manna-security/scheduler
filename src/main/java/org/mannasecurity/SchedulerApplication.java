package org.mannasecurity;

import org.mannasecurity.processing.NoopProcessor;
import org.mannasecurity.redis.TaskProcessorManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SchedulerApplication {

    @Autowired
    NoopProcessor noopProcessor;

    @Autowired
    TaskProcessorManager taskProcessorManager;

    public static void main(String[] args) {
        SpringApplication.run(SchedulerApplication.class, args);
    }
}
