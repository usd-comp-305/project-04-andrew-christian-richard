package edu.sandiego.comp305;

import java.util.Random;

public interface EventFactory {
    public abstract Event createRandomEvent(final Random random);
}
