package edu.sandiego.comp305;

import java.util.Random;

public interface EventFactory {
    QuickTimeEvent createEvent(Horse horse, Random random);
}
