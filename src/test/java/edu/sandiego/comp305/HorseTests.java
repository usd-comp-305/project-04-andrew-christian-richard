package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HorseTests {
    @Test
    void testHorse() {
        Stats stats = new Stats(10, 5, 3);
        Horse horse = new Horse("Thunder", stats);

        assertEquals("Thunder", horse.getName());
        assertEquals(stats, horse.getStats());
        assertEquals(0, horse.getCurrentDistance());
        assertEquals(0, horse.getTrophyCount());

        horse.addTrophies(2);
        assertEquals(2, horse.getTrophyCount());

        horse.move();
        assertEquals(10, horse.getCurrentDistance());

        assertFalse(horse.hasFinished(20));

        horse.move();
        assertEquals(20, horse.getCurrentDistance());
        assertTrue(horse.hasFinished(20));

        horse.resetForRace();
        assertEquals(0, horse.getCurrentDistance());
    }
}