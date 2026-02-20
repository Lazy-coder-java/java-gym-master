package ru.yandex.practicum.gym;

public class Coach {
    private String name;

    public Coach(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // Нужно для assertEquals в тестах
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coach)) return false;
        Coach coach = (Coach) o;
        return name.equals(coach.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
