package ru.yandex.practicum.gym;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Timetable {

    private Map<DayOfWeek, TreeMap<TimeOfDay, List<TrainingSession>>> timetable;

    public Timetable() {
        timetable = new HashMap<>();
    }

    public void addNewTrainingSession(TrainingSession session) {
        DayOfWeek day = session.getDayOfWeek();
        TimeOfDay time = session.getTimeOfDay();

        TreeMap<TimeOfDay, List<TrainingSession>> dayMap = timetable.get(day);
        if (dayMap == null) {
            dayMap = new TreeMap<TimeOfDay, List<TrainingSession>>();
            timetable.put(day, dayMap);
        }

        List<TrainingSession> sessionsAtTime = dayMap.get(time);
        if (sessionsAtTime == null) {
            sessionsAtTime = new ArrayList<TrainingSession>();
            dayMap.put(time, sessionsAtTime);
        }

        sessionsAtTime.add(session);
    }

    public List<TrainingSession> getTrainingSessionsForDay(DayOfWeek day) {
        TreeMap<TimeOfDay, List<TrainingSession>> dayMap = timetable.get(day);
        if (dayMap == null) {
            return Collections.emptyList();
        }

        List<TrainingSession> result = new ArrayList<TrainingSession>();
        for (List<TrainingSession> list : dayMap.values()) {
            result.addAll(list);
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
        Map<Coach, Integer> counter = new HashMap<Coach, Integer>();

        for (TreeMap<TimeOfDay, List<TrainingSession>> dayMap : timetable.values()) {
            for (List<TrainingSession> sessions : dayMap.values()) {
                for (TrainingSession session : sessions) {
                    Coach coach = session.getCoach();
                    if (counter.containsKey(coach)) {
                        counter.put(coach, counter.get(coach) + 1);
                    } else {
                        counter.put(coach, 1);
                    }
                }
            }
        }

        List<CounterOfTrainings> result = new ArrayList<CounterOfTrainings>();
        for (Map.Entry<Coach, Integer> entry : counter.entrySet()) {
            result.add(new CounterOfTrainings(entry.getKey(), entry.getValue()));
        }

        for (int i = 0; i < result.size(); i++) {
            for (int j = i + 1; j < result.size(); j++) {
                if (result.get(i).getCount() < result.get(j).getCount()) {
                    CounterOfTrainings temp = result.get(i);
                    result.set(i, result.get(j));
                    result.set(j, temp);
                }
            }
        }

        return result;
    }
}