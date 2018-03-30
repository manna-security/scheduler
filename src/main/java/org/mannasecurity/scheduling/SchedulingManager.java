package org.mannasecurity.scheduling;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.mannasecurity.processing.NoopProcessor;
import org.mannasecurity.processing.TaskProcessor;
import org.mannasecurity.redis.TaskProcessorManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by jtmelton on 6/30/17.
 */
@Component
public class SchedulingManager {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final NoopProcessor noopProcessor;

    private final TaskProcessorManager taskProcessorManager;

    @Autowired
    public SchedulingManager(final NoopProcessor noopProcessor,
                             final TaskProcessorManager taskProcessorManager) {
        this.noopProcessor = noopProcessor;
        this.taskProcessorManager = taskProcessorManager;
    }

    @PostConstruct
    public void initialize() {
        Map<String, TaskProcessor> processorMap = new HashMap<>();

        taskProcessorManager.setChannelProcessorMap(processorMap);

        taskProcessorManager.start();

        log.debug("Started scheduling manager.");
    }

    @PreDestroy
    public void shutdown() {
        taskProcessorManager.stop();

        log.debug("Stopped scheduling manager.");
    }

}
