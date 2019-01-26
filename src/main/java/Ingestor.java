import domain.City;
import domain.Person;
import domain.Visitor;
import engine.FilterEngine;
import exceptions.IngestorException;
import parser.Parser;
import reader.FileReader;
import reader.StreamReader;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.function.Function;

public class Ingestor {

    private final File file;

    public Ingestor(File file) {
        this.file = file;
    }

    public <T> Set<T> execute(Function<StreamReader, Set<T>> function) {
        try (StreamReader reader = new FileReader(file)) {
            return function.apply(reader);
        } catch (IOException e) {
            throw new IngestorException("Error reading file: " + file.getAbsolutePath(), e);
        }
    }

    public Set<Person> getPersonsFrom(String cityName) {
        City city = new City(cityName);
        return execute((reader) -> new FilterEngine<Person>().filterEngine(reader, new Parser(),
                (visitor) -> visitor.getCity().equals(city),
                Visitor::getPerson));

    }

    public Set<City> getCitiesVisitedBy(String id) {
        return execute((reader) -> new FilterEngine<City>().filterEngine(reader, new Parser(),
                (visitor) -> visitor.getPerson().getId().equals(id),
                Visitor::getCity));
    }
}
