package edu.sandiego.comp305;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AbstractEventFactoryTests {
    private Random random;
    private AbstractEventFactory testFactory;

    @BeforeEach
    void init() {
        random = mock(Random.class);

        Stats testStats = new Stats(1, 1, 1);
        Horse testHorse = new Horse("SeaBiscuit", testStats);

        EventDescriptionProvider descriptionProvider = new EventDescriptionProvider();
        testFactory = new AbstractEventFactory(testHorse, descriptionProvider);
    }

    @Test
    void createRandomEvent_returnsNeutralEvent() {
        when(random.nextInt(anyInt())).thenReturn(1).thenReturn(0);

        Event testEvent = testFactory.createRandomEvent(random);

        assertInstanceOf(NeutralEvent.class, testEvent);
    }

    @Test
    void createRandomEvent_returnsGoodEvent() {
        when(random.nextInt(anyInt())).thenReturn(70).thenReturn(0);

        Event testEvent = testFactory.createRandomEvent(random);

        assertInstanceOf(GoodEvent.class, testEvent);
    }

    @Test
    void createRandomEvent_returnsBadEvent() {
        when(random.nextInt(anyInt())).thenReturn(90).thenReturn(0);

        Event testEvent = testFactory.createRandomEvent(random);

        assertInstanceOf(BadEvent.class, testEvent);
    }
}