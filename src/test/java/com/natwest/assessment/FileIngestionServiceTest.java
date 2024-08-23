package com.natwest.assessment;




import com.natwest.assessment.Models.Input;
import com.natwest.assessment.Models.Reference;
import com.natwest.assessment.Service.FileIngestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class FileIngestionServiceTest {

    @Autowired
    private FileIngestionService fileIngestionService;
    @Value("${input.file.path}")
    String inputfilePath;
    @Test
    public void testIngestInputFile() throws Exception {

        File inputfile = new File(inputfilePath);
        Stream<Input> inputDataList = fileIngestionService.ingestInputFile(inputfile, "input-csv");

        assertNotNull(inputDataList);
    }

    @Value( "${input.file.path}")
    String referencefilePath;
    @Test
    public void testIngestReferenceFile() throws Exception {
        File referenceFile=new File(referencefilePath);
        Map<String, Reference> referenceDataMap = fileIngestionService.ingestReferenceFile(referenceFile, "csv");

        assertNotNull(referenceDataMap);
        assertEquals(1, referenceDataMap.size());

        Reference referenceData = referenceDataMap.get("ref1ref2");
        assertNotNull(referenceData);
        assertEquals("Data1", referenceData.getRefdata1());
    }
}
