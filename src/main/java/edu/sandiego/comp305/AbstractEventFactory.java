package edu.sandiego.comp305;

import java.util.Random;

public class AbstractEventFactory implements EventFactory{
    private static final int NEUTRAL_EVENT_THRESHOLD = 65;
    private static final int GOOD_EVENT_THRESHOLD = 85;
    private static final int FULL_PERCENTAGE = 100;

    public Event createRandomEvent(Random random) {
        int roll = RandomRoll(random);

        if (roll < NEUTRAL_EVENT_THRESHOLD){
            return new NeutralEvent();
        } else if (roll < GOOD_EVENT_THRESHOLD){
            return new GoodEvent();
        } else {
            return new BadEvent();
        }
    }

    private int RandomRoll(Random random){
        return random.nextInt(FULL_PERCENTAGE);
    }
}
