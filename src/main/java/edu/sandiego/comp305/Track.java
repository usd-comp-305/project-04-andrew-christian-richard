package edu.sandiego.comp305;

import java.util.*;

public class Track {
    private final TrackType trackType;

    private final int lengthInMeters;

    public Track(final TrackType trackType, final int lengthInMeters) {
        this.trackType = trackType;
        this.lengthInMeters = lengthInMeters;
    }

    public int getLengthInMeters() {
        return lengthInMeters;
    }

    public List<Integer> getPlacementCheckpoints() {
        return null;
    }

    public List<Integer> getEventCheckpoints() {
        return null;
    }
}
