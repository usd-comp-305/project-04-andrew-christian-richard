package edu.sandiego.comp305;

import java.util.List;
import java.util.Random;

public class AbstractEventFactory implements EventFactory{
    private static final int NEUTRAL_EVENT_THRESHOLD = 65;
    private static final int GOOD_EVENT_THRESHOLD = 85;
    private static final int FULL_PERCENTAGE = 100;

    private final List<String> goodDescriptions;
    private final List<String> neutralDescriptions;
    private final List<String> badDescriptions;

    public AbstractEventFactory(
            List<String> goodDescriptions,
            List<String> neutralDescriptions,
            List<String> badDescriptions) {
        this.goodDescriptions = goodDescriptions;
        this.neutralDescriptions = neutralDescriptions;
        this.badDescriptions = badDescriptions;
    }

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

    private String getRandomDescription(List<String> descriptionList, Random random){
        int randomIndex = random.nextInt(descriptionList.size());
        return descriptionList.get(randomIndex);
    }
}
