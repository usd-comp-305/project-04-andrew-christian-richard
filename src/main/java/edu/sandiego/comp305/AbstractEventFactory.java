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
    public Horse horse;

    public AbstractEventFactory(
            List<String> goodDescriptions,
            List<String> neutralDescriptions,
            List<String> badDescriptions,
            Horse horse) {
        this.goodDescriptions = goodDescriptions;
        this.neutralDescriptions = neutralDescriptions;
        this.badDescriptions = badDescriptions;
        this.horse = horse;

    }

    public Event createRandomEvent(Random random) {
        int roll = RandomRoll(random);

        if (roll < NEUTRAL_EVENT_THRESHOLD){
            return new NeutralEvent(getRandomDescription(neutralDescriptions, random), horse);
        } else if (roll < GOOD_EVENT_THRESHOLD){
            return new GoodEvent(getRandomDescription(goodDescriptions, random), horse);
        } else {
            return new BadEvent(getRandomDescription(badDescriptions, random), horse);
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
