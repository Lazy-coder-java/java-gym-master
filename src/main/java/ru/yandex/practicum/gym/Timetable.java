package ru.yandex.practicum.gym;

import java.util.*;

public class Timetable {

    private final Map<DayOfWeek, TreeMap<TimeOfDay, List<TrainingSession>>> timetable = new HashMap<>();

    public void addNewTrainingSession(DayOfWeek day,
                                      TimeOfDay time,
                                      TrainingSession session) {

        timetable
                .computeIfAbsent(day, d -> new TreeMap<>())
                .computeIfAbsent(time, t -> new ArrayList<>())
                .add(session);
    }

    public List<TrainingSession> getTrainingSessionsForDay(DayOfWeek day) {
        if (!timetable.containsKey(day)) {
            return Collections.emptyList();
        }

        List<TrainingSession> result = new ArrayList<>();
        for (List<TrainingSession> sessions : timetable.get(day).values()) {
            result.addAll(sessions);
        }

        return result;
    }

    public List<TrainingSession> getTrainingSessionsForDayAndTime(DayOfWeek day,
                                                                  TimeOfDay time) {

        if (!timetable.containsKey(day)) {
            return Collections.emptyList();
        }

        return timetable.get(day).getOrDefault(time, Collections.emptyList());
    }

    public List<CounterOfTrainings> getCountByCoaches() {

        Map<Coach, Integer> counter = new HashMap<>();

        for (TreeMap<TimeOfDay, List<TrainingSession>> dayMap : timetable.values()) {
            for (List<TrainingSession> sessions : dayMap.values()) {
                for (TrainingSession session : sessions) {
                    Coach coach = session.getCoach();
                    counter.put(coach, counter.getOrDefault(coach, 0) + 1);
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
