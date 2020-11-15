package com.ikea.assignment.scheduler;

import com.ikea.assignment.controller.WarehouseController;
import com.ikea.assignment.service.WarehouseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class WarehouseSchedulerTest {
    @InjectMocks
    private WarehouseScheduler WarehouseScheduler;
    @Mock
    private WarehouseService warehouseService;

    @Test
    public void readInputFilesTest() {
        WarehouseScheduler.readInputFiles();
        Mockito.verify(warehouseService).readInputFiles();

    }

}
