package ru.yandex.practicum.gym;

public class TrainingSession {

    private final Group group;
    private final Coach coach;

    public TrainingSession(Group group, Coach coach) {
        this.group = group;
        this.coach = coach;
    }

    public Group getGroup() {
        return group;
    }

    public Coach getCoach() {
        return coach;
    }
}
