package edu.sandiego.comp305;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    }

    public void addParticipant(final RaceParticipant participant) {
        participants.add(participant);
    }

    public void start() {
        state = RaceState.IN_PROGRESS;
        round = 1;
    }

    public boolean isFinished() {
        return state == RaceState.FINISHED;
    }

    public void prepareRound() {
        event = null;
    }

    public boolean hasEvent() {
        return event != null;
    }

    public Event getEvent() {
        return event;
    }

    void setEvent(final Event nextEvent) {
        event = nextEvent;
    }

    public void resolveEvent(final EventChoice selectedChoice) {
        if (selectedChoice == null) {
            throw new IllegalArgumentException(
                    "Selected choice cannot be null.");
        }

        final Horse playerHorse = getPlayerHorse();

        if (playerHorse == null) {
            throw new IllegalStateException(
                    "Race does not have a player horse.");
        }

        final RaceEffect effect = selectedChoice.getEffect();

        playerHorse.getStats().increaseSpeed(effect.getSpeedChange());
        playerHorse.getStats().increasePower(effect.getPowerChange());

        event = null;
    }

    public void executeRound() {
        if (state != RaceState.IN_PROGRESS) {
            return;
        }

        moveParticipants();
        updateFinishOrder();
        sortCurrentStandings();

        if (finishOrder.size() == participants.size()) {
            state = RaceState.FINISHED;
        } else {
            round++;
        }
    }

    private void moveParticipants() {
        for (final RaceParticipant participant : participants) {
            if (!finishOrder.contains(participant)) {
                participant.move();
            }
        }
    }

    private void updateFinishOrder() {
        for (final RaceParticipant participant : participants) {
            final boolean hasFinished =
                    participant.getCurrentDistance() >= lengthInMeters;
            final boolean alreadyRecorded = finishOrder.contains(participant);

            if (hasFinished && !alreadyRecorded) {
                finishOrder.add(participant);
            }
        }
    }

    private void sortCurrentStandings() {
        participants.sort(
                Comparator.comparingInt(
                        RaceParticipant::getCurrentDistance).reversed()
        );
    }

    public Horse getPlayerHorse() {
        for (final RaceParticipant participant : participants) {
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
        final int index = finishOrder.indexOf(horse);

        if (index < 0) {
            throw new IllegalArgumentException(
                    "Horse has not finished the race.");
        }

        return Placement.values()[index];
    }

    public int getLengthInMeters() {
        return lengthInMeters;
    }
}
