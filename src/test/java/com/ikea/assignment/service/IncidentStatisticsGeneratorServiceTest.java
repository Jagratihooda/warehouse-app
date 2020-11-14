/*
package com.ing.assignment.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class IncidentStatisticsGeneratorServiceTest {
    @InjectMocks
    private IncidentStatisticsGeneratorServiceImpl service;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(service, "inputFilePath", "src/test/resources/input.csv");
        ReflectionTestUtils.setField(service, "outputFilePath", "src/test/resources/output");
        ReflectionTestUtils.setField(service, "outputFileName", "/output.csv");
    }
    @Test
    public void generateReportTest() throws IOException {
        String fileName = service.generateReport();
        List<String> list = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {

            //br returns as stream and convert it into a List
            list = br.lines().collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        assertTrue(fileName.contains("output.csv"));
        assertEquals(3, list.size());
        assertTrue(list.contains("Asset Name,Total Incidents,Down Time,Uptime Percentage,Rating"));
        assertTrue(list.contains("PG,2,43200,50.0,40"));
        assertTrue(list.contains("Ins,3,21600,75.0,50"));
    }

    @After
    public void cleanupAllTestFiles() throws IOException {
        File file = new File("src/test/resources/output");
        deleteDirectory(file);
    }

    private void deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
       directoryToBeDeleted.delete();
    }
}


*/
