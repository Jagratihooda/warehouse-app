package com.ikea.assignment.controller;
import com.ikea.assignment.service.WarehouseService;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
@RunWith(MockitoJUnitRunner.class)
public class WarehouseControllerTest {
    @InjectMocks
    private WarehouseController warehouseController;
    @Mock
    private WarehouseService warehouseService;

   /* @Test
    public void downloadIncidentsStatisticsTest() {
        InputStreamResource is = new InputStreamResource(new ByteArrayInputStream("testString".getBytes()));
        Mockito.when(incidentStatisticsGeneratorService.downloadReport()).thenReturn(is);
        ResponseEntity<?> result = incidentStatisticsGeneratorController.downloadIncidentsStatistics();
        assertEquals(200, result.getStatusCode().value());
        assertEquals(MediaType.parseMediaType("text/csv"), result.getHeaders().getContentType());
    }*/
}
