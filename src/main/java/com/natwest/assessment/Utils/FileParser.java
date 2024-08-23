package com.natwest.assessment.Utils;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

public interface FileParser<T> {
    Stream<T> parseFile(File file) throws IOException;

}
