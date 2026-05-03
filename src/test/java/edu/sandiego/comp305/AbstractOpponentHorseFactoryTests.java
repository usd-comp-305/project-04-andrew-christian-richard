package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AbstractOpponentHorseFactoryTests {
    @Test
    void createOpponentHorse_returnsHorseWithRandomStats() {
        Random random = mock(Random.class);
        when(random.nextInt(anyInt()))
                .thenReturn(0).thenReturn(0)
                .thenReturn(1).thenReturn(1)
                .thenReturn(2);

        HorseFactory testFactory =
                new AbstractOpponentHorseFactory(Difficulty.EASY, TrackType.ONE_HUNDRED_METER, random);

        Horse opponentHorse = testFactory.createHorse("SeaBiscuit");

        assertEquals(3, opponentHorse.getStats().getSpeed());
        assertEquals(3, opponentHorse.getStats().getStamina());
        assertEquals(2, opponentHorse.getStats().getPower());
    }
}