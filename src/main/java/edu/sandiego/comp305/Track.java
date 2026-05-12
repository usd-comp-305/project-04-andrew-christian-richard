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

    public List<Integer> getPlacementCheckpoints() {
        List<Integer> checkpoints = new ArrayList<>();
        checkpoints.add(20);
        checkpoints.add(lengthInMeters / 2);
        checkpoints.add(lengthInMeters);
        return checkpoints;
    }

    public List<Integer> getEventCheckpoints() {
        List<Integer> checkpoints = new ArrayList<>();
        checkpoints.add(lengthInMeters / 4);
        checkpoints.add(lengthInMeters / 2);
        return checkpoints;
    }
}