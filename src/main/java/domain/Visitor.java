package domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Visitor {
    private final Person person;
    private final City city;

    public Visitor(Person person, City city) {
        this.person = person;
        this.city = city;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(person).append(city).hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        Visitor otherVisitor = (Visitor) other;
        return new EqualsBuilder()
                .append(person, otherVisitor.getPerson())
                .append(city, otherVisitor.getCity()).isEquals();
    }

    public Person getPerson() {
        return person;
    }

    public City getCity() {
        return city;
    }
}
