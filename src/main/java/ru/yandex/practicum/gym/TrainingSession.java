package ru.yandex.practicum.gym;

public class TrainingSession {

    private final Coach coach;
    private final DayOfWeek day;
    private final TimeOfDay timeOfDay;

    public TrainingSession(Coach coach, DayOfWeek day, TimeOfDay timeOfDay) {
        this.coach = coach;
        this.day = day;
        this.timeOfDay = timeOfDay;
    }

    public Coach getCoach() {
        return coach;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public TimeOfDay getTimeOfDay() {
        return timeOfDay;
    }
}
