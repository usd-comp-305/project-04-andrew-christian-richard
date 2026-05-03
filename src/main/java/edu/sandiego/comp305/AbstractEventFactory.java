package edu.sandiego.comp305;

import java.util.Random;

public class AbstractEventFactory implements EventFactory{
    private static final int NEUTRAL_EVENT_THRESHOLD = 65;
    private static final int GOOD_EVENT_THRESHOLD = 85;

    private static final String[] GOOD_DESCRIPTIONS ={
            "Your horse is in flow state!",
            "There is a clear gap!",
            "The crowd is cheering for you to win!",
            "Your horse's footing is perfect!",
            "Your horse's strides are getting faster!",
            "Your horse turned a corner perfectly!"
    };

    private static final String[] NEUTRAL_DESCRIPTIONS ={
            "Another horse is neck-to-neck to you",
            "Your horse is intimidated",
            "Your horse's stride remains the same",
            "Your horse is getting antsy!"
    };

    private static final String[] BAD_DESCRIPTIONS ={
            "Your horse got startled!",
            "Your horse has lost its footing!",
            "Your horse got distracted!",
            "Your horse's breathing is out of control!",
            "Your horse just stumbled!",
            "Your horse is losing its pacing!"
    };

    public QuickTimeEvent createEvent(Horse horse, Random random) {
        return null;
    }
}
