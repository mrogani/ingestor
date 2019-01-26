package domain;

import org.apache.commons.lang3.builder.EqualsBuilder;

public class City {
    private final String name;

    public City(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        City otherCity = (City) other;
        return new EqualsBuilder().append(name, otherCity.getName()).isEquals();
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }
}
