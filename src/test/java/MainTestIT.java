import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTestIT {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void shouldSuccessfullyFilterPeopleInSanFrancisco() {

        Main.main(new String[]{getFilePath("input_data"), "CITY", "SAN FRANCISCO"});

        List<String> expected = new ArrayList<>(5);
        expected.add("Mitchell Newton,25384390A");
        expected.add("Rhonda Hopkins,54315871Z");
        expected.add("Alexander Arnold,21743514G");
        expected.add("Susan Holland,04810023X");
        expected.add("Jake Salazar,38399984N");

        List<String> result = Arrays.asList(outContent.toString().split("\n"));

        assertEquals(expected.size(), result.size());
        assertTrue(result.containsAll(expected));
    }

    @Test
    public void shouldSuccessfullyFilterCitiesVisitedBy54808168L() {

        Main.main(new String[]{getFilePath("input_data"), "ID", "54808168L"});
        List<String> expected = new ArrayList<>(3);
        expected.add("MADRID");
        expected.add("BARCELONA");
        expected.add("OVIEDO");

        List<String> result = Arrays.asList(outContent.toString().split("\n"));

        assertEquals(expected.size(), result.size());
        assertTrue(result.containsAll(expected));
    }

    private String getFilePath(String name) {
        return "src/test/" + name;
    }

}
