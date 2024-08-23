package com.natwest.assessment.Utils;

import com.natwest.assessment.Models.Input;

import com.natwest.assessment.Models.Reference;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.io.*;
import java.math.BigDecimal;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Component("reference-csv")
public class CSVReferenceFileParser implements FileParser<Reference> {

    @Override
    public Stream<Reference> parseFile(File file) throws IOException {
        CSVParser parser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(new FileReader(file));
        return StreamSupport.stream(parser.spliterator(), false)
                .map(this::parseRecordToInputData);
    }
    private Reference parseRecordToInputData(CSVRecord record) {
        return new Reference(
                record.get("refkey1"),
                record.get("refdata1"),
                record.get("refkey2"),
                record.get("refdata2"),
                record.get("refdata3"),
                new BigDecimal(record.get("refdata4"))
        );
    }

//
//    public InputStreamReader newReader(final InputStream inputStream) {
//        return new InputStreamReader(new (inputStream), StandardCharsets.UTF_8);
//    }

}

