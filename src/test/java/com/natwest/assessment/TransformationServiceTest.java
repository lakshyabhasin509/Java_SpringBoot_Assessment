package com.natwest.assessment;


import com.natwest.assessment.Models.Input;
import com.natwest.assessment.Models.Output;
import com.natwest.assessment.Models.Reference;
import com.natwest.assessment.Service.TransformationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TransformationServiceTest {

    @Autowired
    private TransformationService transformationService;

    private Input inputData;
    private Reference referenceData;

    @BeforeEach
    public void setUp() {
        // Setup input data
        inputData = new Input("Hello", "World", "Field3", "Field4", new BigDecimal("20.0"), "ref1", "ref2");

        // Setup reference data
        referenceData = new Reference("ref1", "Data1", "ref2", "Data2", "Data3", new BigDecimal("15.0"));
    }

    @Test
    public void testTransformData() {
        List<Output> result = transformationService.transformData(
                Collections.singletonList(inputData).stream(),
                Collections.singletonMap("ref1ref2", referenceData)
        );

        Output output = result.get(0);

        // Validate the transformations
        assertEquals("HelloWorld", output.getOutfield1());
        assertEquals("Data1", output.getOutfield2());
        assertEquals("Data2Data3", output.getOutfield3());
        assertEquals(new BigDecimal("200.0"), output.getOutfield4());
        assertEquals(new BigDecimal("20.0"), output.getOutfield5());
    }

    @Test
    public void testTransformDataWithMissingReference() {
        // No matching reference data
        List<Output> result = transformationService.transformData(
                Collections.singletonList(inputData).stream(),
                Collections.emptyMap()
        );

        // Since reference data is missing, the result should be empty
        assertEquals(0, result.size());
    }
}
