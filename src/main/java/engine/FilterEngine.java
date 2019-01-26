package engine;

import domain.Visitor;
import parser.Parser;
import reader.StreamReader;

import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FilterEngine<Result> {

    public Set<Result> filterEngine(StreamReader reader, Parser parser, Predicate<Visitor> filter, Function<Visitor, Result> map) {
        return reader.getLines()
                .map(parser::parse)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter(filter)
                .map(map)
                .collect(Collectors.toSet());
    }

}
