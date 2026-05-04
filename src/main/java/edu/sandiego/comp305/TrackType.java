package edu.sandiego.comp305;

public enum TrackType {
    ONE_HUNDRED_METER(100),
    TWO_HUNDRED_METER(200),
    FOUR_HUNDRED_METER(400);

    private final int length;

    TrackType(int length) {
        this.length = length;
    }

    public int getLength(){
        return length;
    }
}
