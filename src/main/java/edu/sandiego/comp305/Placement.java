package edu.sandiego.comp305;

public enum Placement {
    FIRST(5),
    SECOND(4),
    THIRD(3),
    FOURTH(2),
    FIFTH(1),
    SIXTH(0);

    private final int trophyValue;

    Placement(final int trophyValue) {
        this.trophyValue = trophyValue;
    }

    public int getTrophyValue() {
        return trophyValue;
    }
}
