package domain;

import org.apache.commons.lang3.builder.EqualsBuilder;

public class Person {
    private final String name;
    private final String id;


    public Person(String name, String id) {
        this.name = name;
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        Person otherPerson = (Person) other;
        return new EqualsBuilder()
                .append(name, otherPerson.getName())
                .append(id, otherPerson.getId())
                .isEquals();
    }

    @Override
    public String toString() {
        return name + "," + id;
    }
}
