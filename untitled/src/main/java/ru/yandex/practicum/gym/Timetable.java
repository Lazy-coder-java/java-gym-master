package ru.yandex.practicum.gym;

import java.util.*;

public class Timetable {

    private Map<DayOfWeek, TreeMap<TimeOfDay, List<TrainingSession>>> timetable = new HashMap<>();

    public Timetable() {
    }

    public void addNewTrainingSession(TrainingSession session) {
        DayOfWeek day = session.getDayOfWeek();
        TimeOfDay time = session.getTimeOfDay();

        TreeMap<TimeOfDay, List<TrainingSession>> dayMap = timetable.get(day);
        if (dayMap == null) {
            dayMap = new TreeMap<>();
            timetable.put(day, dayMap);
        }

        List<TrainingSession> sessionsAtTime = dayMap.get(time);
        if (sessionsAtTime == null) {
            sessionsAtTime = new ArrayList<>();
            dayMap.put(time, sessionsAtTime);
        }

        sessionsAtTime.add(session);
    }

    public List<TrainingSession> getTrainingSessionsForDay(DayOfWeek day) {
        TreeMap<TimeOfDay, List<TrainingSession>> dayMap = timetable.get(day);
        if (dayMap == null) return Collections.emptyList();

        List<TrainingSession> result = new ArrayList<>();
        for (List<TrainingSession> list : dayMap.values()) {
            result.addAll(list);
        }
        return result;
    }

    public List<TrainingSession> getTrainingSessionsForDayAndTime(DayOfWeek day, TimeOfDay time) {
        TreeMap<TimeOfDay, List<TrainingSession>> dayMap = timetable.get(day);
        if (dayMap == null) return Collections.emptyList();
        return dayMap.getOrDefault(time, Collections.emptyList());
    }

    public List<CounterOfTrainings> getCountByCoaches() {
        Map<Coach, Integer> counter = new HashMap<>();
        for (TreeMap<TimeOfDay, List<TrainingSession>> dayMap : timetable.values()) {
            for (List<TrainingSession> sessions : dayMap.values()) {
                for (TrainingSession session : sessions) {
                    counter.merge(session.getCoach(), 1, Integer::sum);
                }
            }
        }

        List<CounterOfTrainings> result = new ArrayList<>();
        for (Map.Entry<Coach, Integer> entry : counter.entrySet()) {
            result.add(new CounterOfTrainings(entry.getKey(), entry.getValue()));
        }

        result.sort((a, b) -> Integer.compare(b.getCount(), a.getCount()));
        return result;
    }
}