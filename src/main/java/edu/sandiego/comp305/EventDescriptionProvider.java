package edu.sandiego.comp305;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class EventDescriptionProvider {
    private final Map<EventType, List<String>> descriptionsByType;

    public EventDescriptionProvider() {
        descriptionsByType = Map.of(
                EventType.NEUTRAL, List.of(
                        "Another horse is neck-to-neck to you",
                        "Your horse is intimidated",
                        "Your horse's stride remains the same",
                        "Your horse is getting antsy"
                ),
                EventType.GOOD, List.of(
                        "Your horse is in flow state!",
                        "There is a clear gap!",
                        "The crowd is cheering for you to win!",
                        "Your horse's footing is perfect!",
                        "Your horse's strides are getting faster!",
                        "Your horse turned a corner perfectly!"
                ),
                EventType.BAD, List.of(
                        "Your horse got startled!",
                        "Your horse has lost its footing!",
                        "Your horse got distracted!",
                        "Your horse's breathing is out of control!",
                        "Your horse just stumbled!",
                        "Your horse is losing its pacing!"
                )
        );
    }

    public String getRandomDescription(
            final EventType type,
            final Random random) {
        final List<String> descriptions = descriptionsByType.get(type);

        final int randomIndex = random.nextInt(descriptions.size());
        return descriptions.get(randomIndex);
    }
}
