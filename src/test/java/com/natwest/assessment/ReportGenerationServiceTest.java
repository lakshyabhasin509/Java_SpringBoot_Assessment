package com.natwest.assessment;

import com.natwest.assessment.Service.ReportGenerationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ReportGenerationServiceTest {

    @Autowired
    private ReportGenerationService reportGenerationService;

    @Test
    public void testGenerateReport() throws Exception {
        String inputFilePath = "src/test/resources/input.csv";
        String referenceFilePath = "src/test/resources/reference.csv";
        String outputFilePath = "src/test/resources/output.csv";

        reportGenerationService.generateReport(
                inputFilePath,
                "input-csv",
                referenceFilePath,
                "reference-csv",
                outputFilePath,
                "output-csv"
        );

        // Verify that the output file is created
        File outputFile = new File(outputFilePath);
        assertTrue(outputFile.exists());

        // Optionally, read and verify the contents of the output file
        String content = Files.readString(Paths.get(outputFilePath));
        assertTrue(content.contains("HelloWorld"));
    }
}
