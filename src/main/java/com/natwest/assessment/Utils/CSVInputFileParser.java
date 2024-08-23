package com.natwest.assessment.Utils;

import com.natwest.assessment.Models.Input;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.io.*;
import java.math.BigDecimal;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Component("input-csv")
public class CSVInputFileParser implements FileParser<Input> {

    @Override
    public Stream<Input> parseFile(File file) throws IOException {
        CSVParser parser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(new FileReader(file));
        return StreamSupport.stream(parser.spliterator(), false)
                .map(this::parseRecordToInputData);
    }
    private Input parseRecordToInputData(CSVRecord record) {
        return new Input(
                record.get("field1"),
                record.get("field2"),
                record.get("field3"),
                record.get("field4"),
                new BigDecimal(record.get("field5")),
                record.get("refkey1"),
                record.get("refkey2")
        );
    }

//
//    public InputStreamReader newReader(final InputStream inputStream) {
//        return new InputStreamReader(new (inputStream), StandardCharsets.UTF_8);
//    }

}

