package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AbstractPlayerHorseFactoryTests {
    @Test
    void createPlayerHorse_returnsHorseWithMinStats() {
        HorseFactory testFactory = new AbstractPlayerHorseFactory();
        Horse playerHorse = testFactory.createHorse("SeaBiscuit");

        assertEquals(1, playerHorse.getStats().getSpeed());
        assertEquals(1, playerHorse.getStats().getPower());
        assertEquals(1, playerHorse.getStats().getStamina());
    }
}
