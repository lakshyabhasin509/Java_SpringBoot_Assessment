package com.natwest.assessment.Utils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface FileWriter<T> {
    void writeFile(File file, List<T> data)
            throws IOException;
}
