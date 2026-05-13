package edu.sandiego.comp305;

import java.util.*;

public class Race {
    private final String raceName;

    private final Difficulty difficulty;

    private final Track track;

    private final List<RaceParticipant> participants;

    private final List<Event> events;

    private RaceState state;

    public Race(
            final String raceName,
            final Difficulty difficulty,
            final Track track,
            final List<RaceParticipant> participants) {
        this.raceName = raceName;
        this.difficulty = difficulty;
        this.track = track;
        this.participants = new ArrayList<>(participants);
        this.events = new ArrayList<>();
        this.state = RaceState.NOT_STARTED;
    }

    public void startRace() {

    }

    public void advanceRound() {

    }

    public void triggerCheckpointEvents() {

    }

    public void showPlacementAt20m() {

    }

    public List<RaceParticipant> getCurrentStandings() {
        return null;
    }

    public Placement getPlacement(final Horse horse) {
        return null;
    }
}
