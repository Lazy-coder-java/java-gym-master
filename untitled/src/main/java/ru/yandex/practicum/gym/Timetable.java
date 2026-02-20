package ru.yandex.practicum.gym;

import java.util.*;

public class Timetable {

    private Map<DayOfWeek,
            TreeMap<TimeOfDay, List<TrainingSession>>> timetable
            = new HashMap<>();

    public void addNewTrainingSession(TrainingSession session) {

        timetable
                .computeIfAbsent(session.getDayOfWeek(),
                        d -> new TreeMap<>())
                .computeIfAbsent(session.getTimeOfDay(),
                        t -> new ArrayList<>())
                .add(session);
    }

    public List<TrainingSession> getTrainingSessionsForDay(
            DayOfWeek day) {

        TreeMap<TimeOfDay,
                List<TrainingSession>> dayMap = timetable.get(day);

        if (dayMap == null)
            return Collections.emptyList();

        List<TrainingSession> result = new ArrayList<>();

        for (List<TrainingSession> list : dayMap.values()) {
            result.addAll(list);
        }

        return result;
    }

    public List<TrainingSession> getTrainingSessionsForDayAndTime(
            DayOfWeek day,
            TimeOfDay time) {

        TreeMap<TimeOfDay,
                List<TrainingSession>> dayMap = timetable.get(day);

        if (dayMap == null)
            return Collections.emptyList();

        return dayMap.getOrDefault(time,
                Collections.emptyList());
    }

    public List<CounterOfTrainings> getCountByCoaches() {

        Map<Coach, Integer> counter = new HashMap<>();

        for (TreeMap<TimeOfDay,
                List<TrainingSession>> day : timetable.values()) {

            for (List<TrainingSession> sessions : day.values()) {

                for (TrainingSession s : sessions) {
                    counter.merge(
                            s.getCoach(),
                            1,
                            Integer::sum);
                }
            }
        }

        List<CounterOfTrainings> result = new ArrayList<>();

        for (var entry : counter.entrySet()) {
            result.add(
                    new CounterOfTrainings(
                            entry.getKey(),
                            entry.getValue()));
        }

        result.sort((a, b) ->
                Integer.compare(b.getCount(), a.getCount()));

        return result;
    }
}