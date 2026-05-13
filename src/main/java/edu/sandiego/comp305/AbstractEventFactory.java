package edu.sandiego.comp305;

import java.util.Random;

public class AbstractEventFactory implements EventFactory {
    private static final int NEUTRAL_EVENT_THRESHOLD = 65;

    private static final int GOOD_EVENT_THRESHOLD = 85;

    private static final int EVENT_ROLL_BOUND = 100;

    private final Horse horse;

    private final EventDescriptionProvider descriptionProvider;

    public AbstractEventFactory(
            final Horse horse,
            final EventDescriptionProvider descriptionProvider) {
        this.horse = horse;
        this.descriptionProvider = descriptionProvider;
    }

    @Override
    public Event createRandomEvent(final Random random) {
        final int roll = getRandomRoll(random);

        if (roll < NEUTRAL_EVENT_THRESHOLD) {
            return new NeutralEvent(
                    getDescription(EventType.NEUTRAL, random),
                    horse);
        } else if (roll < GOOD_EVENT_THRESHOLD) {
            return new GoodEvent(
                    getDescription(EventType.GOOD, random),
                    horse);
        } else {
            return new BadEvent(
                    getDescription(EventType.BAD, random),
                    horse);
        }
    }

    private String getDescription(
            final EventType eventType,
            final Random random) {
        return descriptionProvider.getRandomDescription(eventType, random);
    }

    private int getRandomRoll(final Random random) {
        return random.nextInt(EVENT_ROLL_BOUND);
    }
}
