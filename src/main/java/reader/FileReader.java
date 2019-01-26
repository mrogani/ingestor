package reader;

import exceptions.IngestorException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

public class FileReader implements StreamReader {

    private static final int BUFFER_SIZE = 1000000;
    private BufferedReader reader;

    public FileReader(File file) {
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8), BUFFER_SIZE);
        } catch (IOException e) {
            throw new IngestorException("Failed to open file: " + file.getAbsolutePath(), e);
        }
    }

    @Override
    public Stream<String> getLines() {
        return reader.lines();
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
}
