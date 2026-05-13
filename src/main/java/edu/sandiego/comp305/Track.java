package edu.sandiego.comp305;

import java.util.*;

public class Track {
    private final TrackType trackType;
    private final int lengthInMeters;

    public Track(TrackType trackType, int lengthInMeters) {
        this.trackType = trackType;
        this.lengthInMeters = lengthInMeters;
    }

    public int getLengthInMeters() {
        return lengthInMeters;
    }
}