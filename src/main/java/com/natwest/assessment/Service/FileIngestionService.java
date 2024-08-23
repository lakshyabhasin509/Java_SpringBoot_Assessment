package com.natwest.assessment.Service;

import com.natwest.assessment.Models.Input;
import com.natwest.assessment.Models.Reference;
import com.natwest.assessment.Utils.FileParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FileIngestionService {

    private static final Logger logger= LoggerFactory.getLogger(FileIngestionService.class);


    @Autowired
    private Map<String, FileParser<Input>> inputfileParsers;
    @Autowired
    private Map<String, FileParser<Reference>> referencefileParsers;
    public Stream<Input> ingestInputFile(File file, String fileType) throws IOException {
        try {
            Stream<Input> i = inputfileParsers.get(fileType).parseFile(file);
            logger.info("Input file Ingested");
            return i;
        }catch (Exception e){
            logger.error("Input file not Ingested");

        }
        return null;
    }

    public Map<String, Reference> ingestReferenceFile(File file, String fileType) throws IOException {;

       return (Map)referencefileParsers.get(fileType).parseFile(file)
                .collect(Collectors.toMap(
                        ref -> (String)(ref.getRefkey1() + ref.getRefkey2()),
                        ref -> ref));
    }
}
