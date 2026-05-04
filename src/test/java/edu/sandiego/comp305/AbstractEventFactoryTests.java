package edu.sandiego.comp305;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AbstractEventFactoryTests{
    private List<String> mockDescriptions;
    private Random random;
    private Horse horse;
    private AbstractEventFactory testFactory;

    @BeforeEach
    void init(){
        mockDescriptions = List.of("Mock event description");
        random = mock(Random.class);
        Stats stat = new Stats(1, 1, 1);
        horse = new Horse("SeaBiscuit", stat);
        testFactory = new AbstractEventFactory(mockDescriptions, mockDescriptions,
                mockDescriptions, horse);
    }

    @Test
    void createRandomEvent_returnsNeutralEvent(){
        when(random.nextInt(anyInt())).thenReturn(1).thenReturn(0);

        Event testEvent = testFactory.createRandomEvent(random);

        assertInstanceOf(NeutralEvent.class, testEvent);
    }

    @Test
    void createRandomEvent_returnsGoodEvent(){
        when(random.nextInt(anyInt())).thenReturn(70).thenReturn(0);

        Event testEvent = testFactory.createRandomEvent(random);

        assertInstanceOf(GoodEvent.class, testEvent);
    }

    @Test
    void createRandomEvent_returnsBadEvent(){
        when(random.nextInt(anyInt())).thenReturn(90).thenReturn(0);

        Event testEvent = testFactory.createRandomEvent(random);

        assertInstanceOf(BadEvent.class, testEvent);
    }
}
