package parser;

import domain.City;
import domain.Person;
import domain.Visitor;

import java.util.Optional;
import java.util.function.Function;

public class Parser {

    private Function<String, Visitor> lineParserFnc;

    private static String getLineType(String nextLine) {
        return nextLine.split(" ")[0].trim();
    }

    private static Visitor lineParserF1(String line) {
        String[] elements = line.split(",");
        if (elements.length != 3) {
            throw new IllegalArgumentException("Invalid File Format. Data format should be [NAME,CITY,ID]: " + line);
        }
        Person person = new Person(elements[0].trim(), elements[2].trim());
        return new Visitor(person, new City(elements[1].trim()));
    }

    private static Visitor lineParserF2(String line) {
        String[] elements = line.split(" ; ");
        if (elements.length != 3) {
            throw new IllegalArgumentException("Invalid File Format. Data format should be [NAME ; CITY ; ID]: " + line);
        }

        String normalizedId = Utils.normalizeF2Id(elements[2]).trim();
        Person person = new Person(elements[0].trim(), normalizedId);
        return new Visitor(person, new City(elements[1].trim()));
    }

    public Optional<Visitor> parse(String nextLine) {

        String lineTypeToken = getLineType(nextLine);
        switch (lineTypeToken) {
            case "F1":
                lineParserFnc = Parser::lineParserF1;
                return Optional.empty();
            case "F2":
                lineParserFnc = Parser::lineParserF2;
                return Optional.empty();
            case "D":
                if (lineParserFnc == null) {
                    throw new IllegalArgumentException("Invalid File Format. Data line should be after Format line. Error line: " + nextLine);
                }
                String lineData = nextLine.substring(1);
                return Optional.of(lineParserFnc.apply(lineData));
            default:
                throw new IllegalArgumentException("Invalid File Format. Line should start with: [F1|F2|D]");
        }
    }

}
