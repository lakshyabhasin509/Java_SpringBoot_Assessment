package com.natwest.assessment.Service;



import com.natwest.assessment.Models.Input;
import com.natwest.assessment.Models.Output;
import com.natwest.assessment.Models.Reference;
import com.natwest.assessment.Service.FileIngestionService;
import com.natwest.assessment.Service.TransformationService;
import com.natwest.assessment.Utils.FileWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Service
public class ReportGenerationService {


    private static final Logger logger= LoggerFactory.getLogger(ReportGenerationService.class);
    @Autowired
    private FileIngestionService fileIngestionService;

    @Autowired
    private TransformationService transformationService;

    @Autowired
    private FileWriter<Output> fileWriters;

    public void generateReport(String inputFilePath, String inputType, String referenceFilePath, String referenceType, String outputFilePath, String outputType) throws IOException {


        logger.info("Report Generation Started");

        File inputFile = new File(inputFilePath);
        File referenceFile = new File(referenceFilePath);
        File outputFile = new File(outputFilePath);



        Stream<Input> inputDataStream = fileIngestionService.ingestInputFile(inputFile, inputType);
        Map<String, Reference> referenceDataMap = fileIngestionService.ingestReferenceFile(referenceFile, referenceType);

        List<Output> outputDataList = transformationService.transformData(inputDataStream, referenceDataMap);



        fileWriters.writeFile(outputFile, outputDataList);
    }
}
