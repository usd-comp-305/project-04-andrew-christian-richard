package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AbstractEventFactoryTests{
    @Test
    void createRandomEvent_returnsNeutralEvent(){
        Random random = mock(Random.class);
        when(random.nextInt(anyInt())).thenReturn(1);

        AbstractEventFactory testFactory = new AbstractEventFactory();
        Event testEvent = testFactory.createRandomEvent(random);

        assertInstanceOf(NeutralEvent.class, testEvent);
    }

    @Test
    void createRandomEvent_returnsGoodEvent(){
        Random random = mock(Random.class);
        when(random.nextInt(anyInt())).thenReturn(70);

        AbstractEventFactory testFactory = new AbstractEventFactory();
        Event testEvent = testFactory.createRandomEvent(random);

        assertInstanceOf(GoodEvent.class, testEvent);
    }

    @Test
    void createRandomEvent_returnsBadEvent(){
        Random random = mock(Random.class);
        when(random.nextInt(anyInt())).thenReturn(90);

        AbstractEventFactory testFactory = new AbstractEventFactory();
        Event testEvent = testFactory.createRandomEvent(random);

        assertInstanceOf(GoodEvent.class, testEvent);
    }
}
