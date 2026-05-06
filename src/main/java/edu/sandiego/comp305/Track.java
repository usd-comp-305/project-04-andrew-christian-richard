package edu.sandiego.comp305;

import java.util.ArrayList;
import java.util.List;

public class Track {
    private static final int PLACEMENT_CHECKPOINT_INTERVAL = 20;
    private static final int EVENT_CHECKPOINT_INTERVAL = 10;

    private final TrackType trackType;

    public Track(TrackType trackType) {
        this.trackType = trackType;
    }

    public TrackType getTrackType() {
        return trackType;
    }

    public int getLengthInMeters() {
        return trackType.getLength();
    }

    public List<Integer> getPlacementCheckpoints() {
        List<Integer> checkpoints = new ArrayList<>();

        for (int checkpoint = PLACEMENT_CHECKPOINT_INTERVAL;
             checkpoint < getLengthInMeters();
             checkpoint += PLACEMENT_CHECKPOINT_INTERVAL) {
            checkpoints.add(checkpoint);
        }

        return checkpoints;
    }

    public List<Integer> getEventCheckpoints() {
        List<Integer> checkpoints = new ArrayList<>();

        for (int checkpoint = EVENT_CHECKPOINT_INTERVAL;
             checkpoint < getLengthInMeters();
             checkpoint += EVENT_CHECKPOINT_INTERVAL) {
            checkpoints.add(checkpoint);
        }

        return checkpoints;
    }
}