package com.natwest.assessment.Service;

import com.natwest.assessment.Models.Input;
import com.natwest.assessment.Models.Reference;
import com.natwest.assessment.Utils.FileParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FileIngestionService {

    @Autowired
    private Map<String, FileParser<Input>> inputfileParsers;
    @Autowired
    private Map<String, FileParser<Reference>> referencefileParsers;
    public Stream<Input> ingestInputFile(File file, String fileType) throws IOException {
        System.out.println("input file ingested");
        return inputfileParsers.get(fileType).parseFile(file);
    }

    public Map<String, Reference> ingestReferenceFile(File file, String fileType) throws IOException {
        System.out.println("reference file ingested");
       return (Map)referencefileParsers.get(fileType).parseFile(file)
                .collect(Collectors.toMap(
                        ref -> (String)(ref.getRefkey1() + ref.getRefkey2()),
                        ref -> ref));
    }
}
