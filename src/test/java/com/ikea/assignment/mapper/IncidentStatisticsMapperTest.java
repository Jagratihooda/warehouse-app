/*
package com.ing.assignment.mapper;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;

@RunWith(MockitoJUnitRunner.class)
public class IncidentStatisticsMapperTest {
    @InjectMocks
    private IncidentStatisticsMapper incidentStatisticsMapper;

    @Test
    public void testMap() {
        IncidentStatisticsInputData result = incidentStatisticsMapper.map("Insurance;2019-04-01 19:23:54;2019-04-01 19:25:23;2");

        assertEquals("Insurance", result.getAssetName());
        assertEquals(LocalDateTime.of(2019, 04, 01, 19, 23, 54), result.getStartTime());
        assertEquals(LocalDateTime.of(2019, 04, 01, 19, 25, 23), result.getEndTime());
        assertEquals(2, result.getSeverity());

    }
}
*/
