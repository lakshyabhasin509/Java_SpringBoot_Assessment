package com.natwest.assessment.Service;



import com.natwest.assessment.Config.TransformationConfig;
import com.natwest.assessment.Models.Input;
import com.natwest.assessment.Models.Output;
import com.natwest.assessment.Models.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.script.ScriptEngineManager;
import java.math.BigDecimal;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class    TransformationService {


    private static final Logger logger= LoggerFactory.getLogger(TransformationService.class);
    @Autowired
    private TransformationConfig transformationConfig;

    public List<Output> transformData(Stream<Input> inputDataStream, Map<String, Reference> referenceDataMap) {
        logger.info("Starting data transformation for input records.");
        return inputDataStream
                .map(inputData ->{
                    String key=inputData.getRefkey1()+inputData.getRefkey2();
                    Reference referenceData=referenceDataMap.get(key);


                    if (referenceData == null) {
                        logger.warn("No reference data found for key: {}", key);
                        return null;
                    }

                    return applyTransformationRules(inputData,referenceData);
                })
                .collect(Collectors.toList());
    }

    private Output applyTransformationRules(Input inputData, Reference referenceData) {
        logger.debug("Transforming data for input: {}", inputData);
        Map<String, String> rules = transformationConfig.getRules();

        String outfield1 = evaluateExpression(rules.get("outfield1"), inputData, referenceData);
        String outfield2 = evaluateExpression(rules.get("outfield2"), inputData, referenceData);
        String outfield3 = evaluateExpression(rules.get("outfield3"), inputData, referenceData);
        String outfield4 = evaluateExpression(rules.get("outfield4"), inputData, referenceData);
        BigDecimal outfield5 = new BigDecimal(evaluateExpression(rules.get("outfield5"), inputData, referenceData));


        logger.debug("Generated output: outfield1={}, outfield2={}, outfield3={}, outfield4={}, outfield5={}",
                outfield1, outfield2, outfield3, outfield4, outfield5);

        return new Output(outfield1, outfield2, outfield3, outfield4, outfield5);
    }


    private String evaluateExpression(String expression, Input inputData, Reference referenceData) {
        logger.debug("Evaluating expression: {}", expression);

        expression = expression
                .replace("concat(field1,field2)", inputData.getField1()+""+ inputData.getField2())
                .replace("concat(refdata2,refdata3)", referenceData.getRefdata2()+""+ referenceData.getRefdata3())
                .replace("field1", inputData.getField1())
                .replace("field2", inputData.getField2())
                .replace("refdata1", referenceData.getRefdata1())
                .replace("refdata2", referenceData.getRefdata2())
                .replace("refdata3", referenceData.getRefdata3());

        // Implement additional logic to handle mathematical operations like max, multiply, etc.
        expression = expression.replace("multiply(field4,max(field5,refdata4))", multiply(inputData.getField4(),(max(inputData.getField5(), referenceData.getRefdata4()))));

        expression = expression.replace("max(field5,refdata4)", max(inputData.getField5(), referenceData.getRefdata4()).toString());

        logger.debug("Evaluated expression result: {}", expression);
        return expression;
    }


    private String multiply(String value1, BigDecimal value2) {
        return value1.toString()+value2.toPlainString();

    }
    private BigDecimal max(BigDecimal value1, BigDecimal value2) {
        return value1.compareTo(value2) >= 0 ? value1 : value2;
    }
//    private String evaluateMathExpression(String expression) {
//        // Simple evaluation logic (for demo purposes); you can use libraries for complex expressions
//        System.out.println(expression);
//        try {
//            return new ScriptEngineManager().getEngineByName("JavaScript").eval(expression).toString();
//        } catch (Exception e) {
//            throw new RuntimeException("Error evaluating expression: " + expression, e);
//        }
//    }
}
