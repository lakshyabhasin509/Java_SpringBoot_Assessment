package com.natwest.assessment.Utils;


import com.natwest.assessment.Models.Output;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Component
public class CSVFileWriter implements FileWriter<Output> {

    @Override
    public void writeFile(File file, List<Output> data) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(file.toPath());
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {

            // Write header
            csvPrinter.printRecord("outfield1", "outfield2", "outfield3", "outfield4", "outfield5");

            // Write data
            for (Output output : data) {
                csvPrinter.printRecord(output.getOutfield1(), output.getOutfield2(),
                        output.getOutfield3(), output.getOutfield4(), output.getOutfield5());
            }
        }
    }
}