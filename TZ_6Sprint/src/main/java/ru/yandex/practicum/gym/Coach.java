package ru.yandex.practicum.gym;

import java.util.Objects;

public class Coach {

    private final String firstName;
    private final String lastName;

    public Coach(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coach)) return false;
        Coach coach = (Coach) o;
        return Objects.equals(firstName, coach.firstName) &&
                Objects.equals(lastName, coach.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}
