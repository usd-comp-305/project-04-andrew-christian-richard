package edu.sandiego.comp305;

public interface HorseFactory {
    public static final int MIN_STAT = 1;
    public static final int MAX_STAT = 100;

    public abstract Horse createHorse(String name);
}
