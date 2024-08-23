package com.natwest.assessment.Controller;

//import com.natwest.assessment.Service.ReportGenerationService;
import com.natwest.assessment.Service.ReportGenerationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;


@RestController
@RequestMapping("/api/reports")
public class ReportGenerationController {


    @Value("${input.file.path}")
    String inputFilePath;
    @Value("${reference.file.path}")
    String referenceFilePath;
    @Value("${output.file.path}")
    String outputFilePath;


    @Autowired
    private ReportGenerationService reportGenerationService;
    private static final Logger logger= LoggerFactory.getLogger(ReportGenerationController.class);

    @PostMapping("/generate")
    public ResponseEntity<String> generateReport() {

        logger.info("Generate enpoint hit");
       try {
            reportGenerationService.generateReport(inputFilePath, "input-csv", referenceFilePath, "reference-csv", outputFilePath, "output-csv");
            return ResponseEntity.ok("Report generated successfully at Ouput.csv");
        } catch (Exception e) {
           logger.error("Internal Server Error");
            return ResponseEntity.status(500).body("Error generating report: " + e.getMessage());
        }
    }
}

