package ru.yandex.practicum.gym;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TimetableTest {

    @Test
    void testGetTrainingSessionsForDaySingleSession() {

        Timetable timetable = new Timetable();

        Group group =
                new Group("Акробатика", Age.CHILD, 60);

        Coach coach =
                new Coach("Иванов", "Иван", "Иванович");

        TrainingSession session =
                new TrainingSession(
                        group,
                        coach,
                        DayOfWeek.MONDAY,
                        new TimeOfDay(13, 0));

        timetable.addNewTrainingSession(session);

        List<TrainingSession> monday =
                timetable.getTrainingSessionsForDay(
                        DayOfWeek.MONDAY);

        List<TrainingSession> tuesday =
                timetable.getTrainingSessionsForDay(
                        DayOfWeek.TUESDAY);

        assertEquals(1, monday.size());
        assertTrue(tuesday.isEmpty());
    }

    @Test
    void testGetTrainingSessionsForDayMultipleSessions() {

        Timetable timetable = new Timetable();

        Coach coach =
                new Coach("Петров", "Петр", "Петрович");

        Group group =
                new Group("Акробатика", Age.ADULT, 90);

        timetable.addNewTrainingSession(
                new TrainingSession(
                        group, coach,
                        DayOfWeek.THURSDAY,
                        new TimeOfDay(20, 0)));

        timetable.addNewTrainingSession(
                new TrainingSession(
                        group, coach,
                        DayOfWeek.THURSDAY,
                        new TimeOfDay(13, 0)));

        List<TrainingSession> result =
                timetable.getTrainingSessionsForDay(
                        DayOfWeek.THURSDAY);

        assertEquals(2, result.size());

        // проверяем порядок
        assertEquals(13,
                result.get(0).getTimeOfDay().getHours());

        assertEquals(20,
                result.get(1).getTimeOfDay().getHours());
    }

    @Test
    void testGetTrainingSessionsForDayAndTime() {

        Timetable timetable = new Timetable();

        Group group =
                new Group("Дети", Age.CHILD, 60);

        Coach coach =
                new Coach("Сидоров", "Илья", "Ильич");

        timetable.addNewTrainingSession(
                new TrainingSession(
                        group,
                        coach,
                        DayOfWeek.MONDAY,
                        new TimeOfDay(13, 0)));

        assertEquals(
                1,
                timetable
                        .getTrainingSessionsForDayAndTime(
                                DayOfWeek.MONDAY,
                                new TimeOfDay(13, 0))
                        .size());

        assertTrue(
                timetable
                        .getTrainingSessionsForDayAndTime(
                                DayOfWeek.MONDAY,
                                new TimeOfDay(14, 0))
                        .isEmpty());
    }

    @Test
    void testEmptyTimetable() {

        Timetable timetable = new Timetable();

        assertTrue(
                timetable
                        .getTrainingSessionsForDay(
                                DayOfWeek.FRIDAY)
                        .isEmpty());
    }

    @Test
    void testGetCountByCoaches() {

        Timetable timetable = new Timetable();

        Coach coach1 =
                new Coach("Иванов","Иван","И");
        Coach coach2 =
                new Coach("Петров","Петр","П");

        Group group =
                new Group("Group", Age.ADULT,60);

        timetable.addNewTrainingSession(
                new TrainingSession(group, coach1,
                        DayOfWeek.MONDAY,
                        new TimeOfDay(10,0)));

        timetable.addNewTrainingSession(
                new TrainingSession(group, coach1,
                        DayOfWeek.TUESDAY,
                        new TimeOfDay(10,0)));

        timetable.addNewTrainingSession(
                new TrainingSession(group, coach2,
                        DayOfWeek.WEDNESDAY,
                        new TimeOfDay(10,0)));

        List<CounterOfTrainings> result =
                timetable.getCountByCoaches();

        assertEquals(2,
                result.get(0).getCount());

        assertEquals(1,
                result.get(1).getCount());
    }
}
