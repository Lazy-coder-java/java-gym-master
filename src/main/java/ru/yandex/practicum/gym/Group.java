package ru.yandex.practicum.gym;

public class Group {

    private final String name;
    private final Age age;

    public Group(String name, Age age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Age getAge() {
        return age;
    }
}
