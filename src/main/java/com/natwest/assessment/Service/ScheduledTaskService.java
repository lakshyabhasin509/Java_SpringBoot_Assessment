//package com.natwest.assessment.Service;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//import java.io.File;
//import java.io.IOException;
//
//@Service
//public class ScheduledTaskService {
//
//    @Autowired
//    private ReportGenerationService reportGenerationService;
//
//    @Value("${input.file.path}")
//    String inputFilePath;
//    @Value("${reference.file.path}")
//    String referenceFilePath;
//    @Value("${output.file.path}")
//    String outputFilePath;
//    @Scheduled
//    public void scheduledReportGeneration() {
//
//        try {
//
//            reportGenerationService.generateReport(inputFilePath, "csv", referenceFilePath, "csv", outputFilePath, "csv");
//
//
//        } catch (IOException e) {
//            // Handle exceptions
//            e.printStackTrace();
//        }
//    }
//}
