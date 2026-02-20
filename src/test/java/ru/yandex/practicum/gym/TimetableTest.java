package ru.yandex.practicum.gym;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TimetableTest {

    @Test
    void testAddNewTrainingSession() {
        Timetable timetable = new Timetable();
        Coach coach = new Coach("John");
        TrainingSession session = new TrainingSession(coach, DayOfWeek.MONDAY, TimeOfDay.MORNING);

        timetable.addNewTrainingSession(session);

        assertEquals(1, timetable.getTrainingSessionsForDay(DayOfWeek.MONDAY).size());
        assertTrue(timetable.getTrainingSessionsForDay(DayOfWeek.MONDAY).contains(session));
    }

    @Test
    void testGetTrainingSessionsForDayAndTimeEmpty() {
        Timetable timetable = new Timetable();
        assertTrue(timetable.getTrainingSessionsForDayAndTime(DayOfWeek.MONDAY, TimeOfDay.MORNING).isEmpty());
    }

    @Test
    void testGetCountByCoaches() {
        Timetable timetable = new Timetable();
        Coach coach = new Coach("John");
        TrainingSession session1 = new TrainingSession(coach, DayOfWeek.MONDAY, TimeOfDay.MORNING);
        TrainingSession session2 = new TrainingSession(coach, DayOfWeek.MONDAY, TimeOfDay.EVENING);

        timetable.addNewTrainingSession(session1);
        timetable.addNewTrainingSession(session2);

        assertEquals(2, timetable.getCountByCoaches().get(0).getCount());
        assertEquals(coach, timetable.getCountByCoaches().get(0).getCoach());
    }

    @Test
    void testAddNullTrainingSessionThrows() {
        Timetable timetable = new Timetable();
        assertThrows(NullPointerException.class, () -> timetable.addNewTrainingSession(null));
    }
}
