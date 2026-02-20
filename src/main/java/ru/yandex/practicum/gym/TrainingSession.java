package ru.yandex.practicum.gym;

public class TrainingSession {

    private final Group group;
    private final Coach coach;
    private final DayOfWeek dayOfWeek;
    private final TimeOfDay timeOfDay;

    public TrainingSession(Coach coach,
                           DayOfWeek dayOfWeek,
                           TimeOfDay timeOfDay) {
        this.group = group;
        this.coach = coach;
        this.dayOfWeek = dayOfWeek;
        this.timeOfDay = timeOfDay;
    }

    public TrainingSession(Coach coach, DayOfWeek dayOfWeek, TimeOfDay timeOfDay, Group group, Coach coach1, DayOfWeek dayOfWeek1, TimeOfDay timeOfDay1) {
        this.group = group;
        this.coach = coach1;
        this.dayOfWeek = dayOfWeek1;
        this.timeOfDay = timeOfDay1;
    }

    public Group getGroup() {
        return group;
    }

    public Coach getCoach() {
        return coach;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public TimeOfDay getTimeOfDay() {
        return timeOfDay;
    }
}
