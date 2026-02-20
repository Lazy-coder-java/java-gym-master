package ru.yandex.practicum.gym;

import java.util.*;

public class Timetable {

    private final Map<DayOfWeek, TreeMap<TimeOfDay, List<TrainingSession>>> timetable;

    public Timetable() {
        this.timetable = new HashMap<>();
    }

    public void addNewTrainingSession(TrainingSession session) {
        TreeMap<TimeOfDay, List<TrainingSession>> dayMap = timetable.get(session.getDayOfWeek());
        if (dayMap == null) {
            dayMap = new TreeMap<>();
            timetable.put(session.getDayOfWeek(), dayMap);
        }

        List<TrainingSession> sessionsAtTime = dayMap.get(session.getTimeOfDay());
        if (sessionsAtTime == null) {
            sessionsAtTime = new ArrayList<>();
            dayMap.put(session.getTimeOfDay(), sessionsAtTime);
        }

        sessionsAtTime.add(session);
    }

    public List<TrainingSession> getTrainingSessionsForDay(DayOfWeek day) {
        TreeMap<TimeOfDay, List<TrainingSession>> dayMap = timetable.get(day);
        if (dayMap == null) {
            return Collections.emptyList();
        }

        List<TrainingSession> result = new ArrayList<>();
        for (List<TrainingSession> sessions : dayMap.values()) {
            result.addAll(sessions);
        }
        return result;
    }

    public List<TrainingSession> getTrainingSessionsForDayAndTime(DayOfWeek day, TimeOfDay time) {
        TreeMap<TimeOfDay, List<TrainingSession>> dayMap = timetable.get(day);
        if (dayMap == null) {
            return Collections.emptyList();
        }

        List<TrainingSession> sessionsAtTime = dayMap.get(time);
        if (sessionsAtTime == null) {
            return Collections.emptyList();
        }

        return sessionsAtTime;
    }

    public List<CounterOfTrainings> getCountByCoaches() {
        Map<Coach, Integer> counter = new HashMap<>();

        for (TreeMap<TimeOfDay, List<TrainingSession>> dayMap : timetable.values()) {
            for (List<TrainingSession> sessions : dayMap.values()) {
                for (TrainingSession s : sessions) {
                    Coach coach = s.getCoach();
                    Integer count = counter.get(coach);
                    if (count == null) {
                        counter.put(coach, 1);
                    } else {
                        counter.put(coach, count + 1);
                    }
                }
            }
        }

        List<CounterOfTrainings> result = new ArrayList<>();
        for (Map.Entry<Coach, Integer> entry : counter.entrySet()) {
            result.add(new CounterOfTrainings(entry.getKey(), entry.getValue()));
        }

        result.sort(new Comparator<CounterOfTrainings>() {
            @Override
            public int compare(CounterOfTrainings o1, CounterOfTrainings o2) {
                return Integer.compare(o2.getCount(), o1.getCount());
            }
        });

        return result;
    }
}