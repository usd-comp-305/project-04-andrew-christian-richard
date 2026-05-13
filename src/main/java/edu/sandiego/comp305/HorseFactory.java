package edu.sandiego.comp305;

public interface HorseFactory {
    static final int MIN_STAT = 1;
    static final int MAX_STAT = 25;
    Horse createHorse(String name);
}
