package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HorseTests {
    @Test
    void testHorse() {
        Stats testStats = new Stats(10, 5, 3);
        Horse testHorse = new Horse("Thunder", testStats);

        assertEquals("Thunder", testHorse.getName());
        assertEquals(testStats, testHorse.getStats());
        assertEquals(0, testHorse.getCurrentDistance());
        assertEquals(0, testHorse.getTrophyCount());

        testHorse.addTrophies(2);
        assertEquals(2, testHorse.getTrophyCount());

        testHorse.move();
        assertEquals(10, testHorse.getCurrentDistance());

        assertFalse(testHorse.hasFinished(20));

        testHorse.move();
        assertEquals(20, testHorse.getCurrentDistance());
        assertTrue(testHorse.hasFinished(20));

        testHorse.resetForCurrentRace();
        assertEquals(0, testHorse.getCurrentDistance());
    }
}