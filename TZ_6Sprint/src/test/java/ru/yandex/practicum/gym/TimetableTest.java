package ru.yandex.practicum.gym;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class TimetableTest {

    @Test
    void testSingleCoach() {
        Timetable timetable = new Timetable();

        Coach coach = new Coach("Ivan", "Petrov");
        Group group = new Group("Group1", Age.ADULT);

        timetable.addNewTrainingSession(DayOfWeek.MONDAY,
                new TimeOfDay(10, 0),
                new TrainingSession(group, coach));

        timetable.addNewTrainingSession(DayOfWeek.TUESDAY,
                new TimeOfDay(12, 0),
                new TrainingSession(group, coach));

        List<CounterOfTrainings> result = timetable.getCountByCoaches();

        assertEquals(1, result.size());
        assertEquals(2, result.get(0).getCount());
    }

    @Test
    void testSeveralCoachesSorted() {
        Timetable timetable = new Timetable();

        Coach coach1 = new Coach("Ivan", "Petrov");
        Coach coach2 = new Coach("Anna", "Ivanova");
        Group group = new Group("Group1", Age.ADULT);

        timetable.addNewTrainingSession(DayOfWeek.MONDAY,
                new TimeOfDay(10, 0),
                new TrainingSession(group, coach1));

        timetable.addNewTrainingSession(DayOfWeek.MONDAY,
                new TimeOfDay(11, 0),
                new TrainingSession(group, coach1));

        timetable.addNewTrainingSession(DayOfWeek.MONDAY,
                new TimeOfDay(12, 0),
                new TrainingSession(group, coach2));

        List<CounterOfTrainings> result = timetable.getCountByCoaches();

        assertEquals(2, result.size());
        assertEquals(2, result.get(0).getCount());
        assertEquals(1, result.get(1).getCount());
    }

    @Test
    void testEmptyTimetable() {
        Timetable timetable = new Timetable();

        List<CounterOfTrainings> result = timetable.getCountByCoaches();

        assertTrue(result.isEmpty());
    }
}
