package parser;

import domain.City;
import domain.Person;
import domain.Visitor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    private static final String ANY_NAME = "NAME SURNAME";
    private static final String ANY_CITY_NAME = "CITY_NAME";
    private static final City ANY_CITY = new City(ANY_CITY_NAME);
    private static final String ANY_F1_ID = "12345678A";
    private static final String ANY_F2_ID = "12345678-A";
    private static final String ANY_DATA_F1_LINE = "D " + ANY_NAME + "," + ANY_CITY_NAME + "," + ANY_F1_ID;
    private static final String ANY_DATA_F2_LINE = "D " + ANY_NAME + " ; " + ANY_CITY_NAME + " ; " + ANY_F2_ID;
    private Parser parser;

    @BeforeEach
    public void beforeEach() {
        parser = new Parser();
    }

    @ParameterizedTest
    @ValueSource(strings = {"F1", "F2"})
    public void shouldReturnEmptyVisitorWhenLineStartWithFormat(String formatLine) {

        Optional<Visitor> result = parser.parse(formatLine);

        assertEquals(Optional.empty(), result);
    }

    @Test
    public void shouldSuccessfullyParseVisitorFromF1DataLine() {
        //set F1 Parser
        parser.parse("F1");

        Optional<Visitor> result = parser.parse(ANY_DATA_F1_LINE);
        Visitor expectedVisitor = new Visitor(new Person(ANY_NAME, ANY_F1_ID), new City(ANY_CITY_NAME));
        assertEquals(Optional.of(expectedVisitor), result);
    }

    @Test
    public void shouldSuccessfullyParseVisitorFromF2DataLine() {
        //set F2 Parser
        parser.parse("F2");

        Optional<Visitor> result = parser.parse(ANY_DATA_F2_LINE);
        Person person = new Person(ANY_NAME, Utils.normalizeF2Id(ANY_F2_ID));
        Visitor expectedVisitor = new Visitor(person, ANY_CITY);

        assertEquals(Optional.of(expectedVisitor), result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"D", "D NAME;CITY_NAME;12345678-A", "D NAME,CITY_NAME"})
    public void shouldFailWhenParsingMalformedF1DataLine(String dataLineF1) {
        //set F1 Parser
        parser.parse("F1");

        assertThrows(IllegalArgumentException.class, () -> parser.parse(dataLineF1));
    }

    @ParameterizedTest
    @ValueSource(strings = {"D", "D NAME,CITY_NAME,12345678A", "D NAME;CITY_NAME",
            "D NAME;CITY_NAME;12345678-A"})
    public void shouldFailWhenParsingMalformedF2DataLine(String dataLineF2) {
        //set F2 Parser
        parser.parse("F2");

        assertThrows(IllegalArgumentException.class, () -> parser.parse(dataLineF2));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "A", "\n"})
    public void shouldFailWhenLineDoesNotStartWithValidToken(String line) {
        assertThrows(IllegalArgumentException.class, () -> parser.parse(line));
    }

    @ParameterizedTest
    @ValueSource(strings = {"D NAME,CITY_NAME,12345678A", "D NAME ; CITY_NAME ; 12345678-A"})
    public void shouldFailWhenDataLineArrivesBeforeFormatLine(String dataLine) {
        assertThrows(IllegalArgumentException.class, () -> parser.parse(dataLine));
    }

}
