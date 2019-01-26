package reader;

import java.io.Closeable;
import java.util.stream.Stream;

public interface StreamReader extends Closeable {
    Stream<String> getLines();
}
