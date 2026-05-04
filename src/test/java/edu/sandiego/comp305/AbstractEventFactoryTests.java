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

    @BeforeEach
    void init(){
        List<String> mockDescriptions = List.of("Mock event description");
        Random random = mock(Random.class);
    }
    @Test
    void createRandomEvent_returnsNeutralEvent(){
        when(random.nextInt(anyInt())).thenReturn(1);

        AbstractEventFactory testFactory = new AbstractEventFactory(mockDescriptions, mockDescriptions, mockDescriptions);
        Event testEvent = testFactory.createRandomEvent(random);

        assertInstanceOf(NeutralEvent.class, testEvent);
    }

    @Test
    void createRandomEvent_returnsGoodEvent(){
        when(random.nextInt(anyInt())).thenReturn(70);

        AbstractEventFactory testFactory = new AbstractEventFactory(mockDescriptions, mockDescriptions, mockDescriptions);
        Event testEvent = testFactory.createRandomEvent(random);

        assertInstanceOf(GoodEvent.class, testEvent);
    }

    @Test
    void createRandomEvent_returnsBadEvent(){
        when(random.nextInt(anyInt())).thenReturn(90);

        AbstractEventFactory testFactory = new AbstractEventFactory(mockDescriptions, mockDescriptions, mockDescriptions);
        Event testEvent = testFactory.createRandomEvent(random);

        assertInstanceOf(BadEvent.class, testEvent);
    }
}
