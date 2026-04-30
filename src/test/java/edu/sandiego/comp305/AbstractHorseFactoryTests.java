package edu.sandiego.comp305;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class AbstractHorseFactoryTests {
    @Test
    void createPlayerHorse_returnsHorseWithMinStats() {
        AbstractHorseFactory testFactory = new AbstractHorseFactory();
        Horse playerHorse = testFactory.createPlayerHorse("SeaBiscuit");

        assertEquals(1, playerHorse.getStats().getSpeed());
        assertEquals(1, playerHorse.getStats().getPower());
        assertEquals(1, playerHorse.getStats().getStamina());
    }
}
