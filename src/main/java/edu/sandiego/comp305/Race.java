package edu.sandiego.comp305;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Race {
    private final Difficulty difficulty;
    private final int lengthInMeters;
    private final List<RaceParticipant> participants;
    private final List<RaceParticipant> finishOrder;

    private RaceState state;
    private int round;
    private Event event;

    public Race(
            final Difficulty difficulty,
            final int lengthInMeters) {
        this.difficulty = difficulty;
        this.lengthInMeters = lengthInMeters;
        this.participants = new ArrayList<>();
        this.finishOrder = new ArrayList<>();
        this.state = RaceState.NOT_STARTED;
        this.round = 0;
        this.event = null;
    }

    public void start() {
        state = RaceState.IN_PROGRESS;
        round = 1;
    }

    public boolean isFinished() {
        return state == RaceState.FINISHED;
    }

    public void prepareRound() {
        // Stub for now.
    }

    public boolean hasEvent() {
        return event != null;
    }

    public Event getEvent() {
        return event;
    }

    public void resolveEvent(final EventChoice selectedChoice) {
    }

    public void executeRound() {
        round++;

        // Temporary stub logic so race can eventually end.
        if (round > 10) {
            state = RaceState.FINISHED;
        }
    }

    public Horse getPlayerHorse() {
        for (RaceParticipant participant : participants) {
            if (participant instanceof Horse) {
                return (Horse) participant;
            }
        }

        return null;
    }


    public List<RaceParticipant> getCurrentStandings() {
        return Collections.unmodifiableList(participants);
    }

    public List<RaceParticipant> getFinishOrder() {
        return Collections.unmodifiableList(finishOrder);
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public int getRound() {
        return round;
    }

    public RaceState getState() {
        return state;
    }

    public Placement getPlacement(final Horse horse) {
        return null;
    }

    public int getLengthInMeters(){
        return lengthInMeters;
    }
}