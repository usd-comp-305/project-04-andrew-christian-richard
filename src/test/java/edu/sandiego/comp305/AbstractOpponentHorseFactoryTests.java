package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;

import java.util.function.IntUnaryOperator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AbstractOpponentHorseFactoryTests {
    @Test
    void createOpponentHorse_returnsHorseWithRandomStats() {
        final IntUnaryOperator randomIntGenerator =
                mock(IntUnaryOperator.class);

        when(randomIntGenerator.applyAsInt(anyInt()))
                .thenReturn(0)
                .thenReturn(0)
                .thenReturn(1)
                .thenReturn(1)
                .thenReturn(2)
                .thenReturn(2)
                .thenReturn(1);

        final HorseFactory testFactory =
                new AbstractOpponentHorseFactory(
                        Difficulty.EASY,
                        TrackType.ONE_HUNDRED_METER,
                        randomIntGenerator);

        final Horse opponentHorse = testFactory.createHorse("SeaBiscuit");

        assertEquals(3, opponentHorse.getStats().getSpeed());
        assertEquals(6, opponentHorse.getStats().getStamina());
        assertEquals(3, opponentHorse.getStats().getPower());
    }
}
