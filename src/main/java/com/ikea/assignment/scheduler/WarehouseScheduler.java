package com.ikea.assignment.scheduler;

import com.ikea.assignment.service.WarehouseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Jagrati
 * The Warehouse Scheduler
 */
@Component
public class WarehouseScheduler {
    private static final Logger LOGGER = LoggerFactory.getLogger(WarehouseScheduler.class);
    @Autowired
    private WarehouseService warehouseService;

    @Value("${cron.fixed.rate}")
    private long cronFixedRate;

    /**
     * This method is scheduled to call in every 86400000 milliseconds (24 hours)
     * - Uploads Input files data to the data base
     */
    @Scheduled(fixedRate = 86400000)
    public void readInputFiles() {
        LOGGER.info("Scheduler invoked. Reading input files");
        warehouseService.readInputFiles();
    }
}
