package ru.yandex.practicum.gym;

import java.util.Map;
import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;

public class Timetable {

    private final Map<DayOfWeek, TreeMap<TimeOfDay, List<TrainingSession>>> timetable;

    public Timetable() {
        this.timetable = new EnumMap<>(DayOfWeek.class);
    }

    public void addNewTrainingSession(TrainingSession session) {
        if (session == null) {
            throw new NullPointerException("TrainingSession не может быть null");
        }
        DayOfWeek day = session.getDay();
        TimeOfDay time = session.getTimeOfDay();

        timetable
                .computeIfAbsent(day, d -> new TreeMap<>())
                .computeIfAbsent(time, t -> new ArrayList<>())
                .add(session);
    }

    public List<TrainingSession> getTrainingSessionsForDay(DayOfWeek day) {
        TreeMap<TimeOfDay, List<TrainingSession>> dayMap = timetable.get(day);
        if (dayMap == null) {
            return Collections.emptyList();
        }
        List<TrainingSession> allSessions = new ArrayList<>();
        for (List<TrainingSession> sessions : dayMap.values()) {
            allSessions.addAll(sessions);
        }
        return allSessions;
    }

    public List<TrainingSession> getTrainingSessionsForDayAndTime(DayOfWeek day, TimeOfDay time) {
        TreeMap<TimeOfDay, List<TrainingSession>> dayMap = timetable.get(day);
        if (dayMap == null) {
            return Collections.emptyList();
        }
        return dayMap.getOrDefault(time, Collections.emptyList());
    }

    public List<CoachCount> getCountByCoaches() {
        Map<Coach, Integer> counts = new HashMap<>();
        for (TreeMap<TimeOfDay, List<TrainingSession>> dayMap : timetable.values()) {
            for (List<TrainingSession> sessions : dayMap.values()) {
                for (TrainingSession session : sessions) {
                    counts.put(session.getCoach(),
                            counts.getOrDefault(session.getCoach(), 0) + 1);
                }
            }
        }
        List<CoachCount> result = new ArrayList<>();
        for (Map.Entry<Coach, Integer> entry : counts.entrySet()) {
            result.add(new CoachCount(entry.getKey(), entry.getValue()));
        }
        return result;
    }

    public static class CoachCount {
        private final Coach coach;
        private final int count;

        public CoachCount(Coach coach, int count) {
            this.coach = coach;
            this.count = count;
        }

        public Coach getCoach() {
            return coach;
        }

        public int getCount() {
            return count;
        }
    }
}

