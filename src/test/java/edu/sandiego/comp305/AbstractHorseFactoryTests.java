package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AbstractHorseFactoryTests {

    @Test
    void createPlayerHorse_returnsHorseWithMinStats() {
        HorseFactory testFactory = new AbstractHorseFactory();
        Horse playerHorse = testFactory.createPlayerHorse("SeaBiscuit");

        assertEquals(1, playerHorse.getStats().getSpeed());
        assertEquals(1, playerHorse.getStats().getPower());
        assertEquals(1, playerHorse.getStats().getStamina());
    }

    @Test
    void createOpponentHorse_returnsHorseWithRandomStats() {
        Random random = mock(Random.class);
        when(random.nextInt(anyInt()))
                .thenReturn(2)
                .thenReturn(3)
                .thenReturn(3);

        HorseFactory testFactory =
                new AbstractHorseFactory(Difficulty.EASY, TrackType.ONE_HUNDRED_METER);

        Horse opponentHorse = testFactory.createOpponentHorse("SeaBiscuit");

        assertEquals(3, opponentHorse.getStats().getSpeed());
        assertEquals(4, opponentHorse.getStats().getPower());
        assertEquals(4, opponentHorse.getStats().getStamina());
    }
}