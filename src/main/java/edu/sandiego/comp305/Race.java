package edu.sandiego.comp305;

import java.util.*;

public class Race {
    private static final int EVENT_ROUND_INTERVAL = 3;

    private final Difficulty difficulty;

    private final int lengthInMeters;

    private final List<RaceParticipant> participants;

    private final List<RaceParticipant> finishOrder;

    private AbstractEventFactory eventFactory;

    private final EventDescriptionProvider descriptor;

    private RaceState state;

    private int round;
    private Event event;
    private final Random random;

    public Race(
            final Difficulty difficulty,
            final int lengthInMeters) {
        this.difficulty = difficulty;
        this.random = new Random();
        this.lengthInMeters = lengthInMeters;
        this.participants = new ArrayList<>();
        this.finishOrder = new ArrayList<>();
        this.descriptor = new EventDescriptionProvider();
        this.eventFactory = null;
        this.state = RaceState.NOT_STARTED;
        this.round = 0;
        this.event = null;
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
        eventFactory = new AbstractEventFactory(getPlayerHorse(), descriptor);

        if (round % EVENT_ROUND_INTERVAL == 0) {
            event = eventFactory.createRandomEvent(random);
        }
    }

    public boolean hasEvent() {
        return event != null;
    }

    public Event getEvent() {
        return event;
    }

    public void resolveEvent(final EventChoice selectedChoice) {
        if (selectedChoice == null) {
            throw new IllegalArgumentException("Selected choice cannot be null.");
        }

        Horse playerHorse = getPlayerHorse();

        if (playerHorse == null) {
            throw new IllegalStateException("Race does not have a player horse.");
        }

        RaceEffect effect = selectedChoice.getEffect();

        playerHorse.applyRaceEffect(effect);

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
        for (RaceParticipant participant : participants) {
            if (!finishOrder.contains(participant)) {
                participant.move();
            }
        }
    }

    private void updateFinishOrder() {
        for (RaceParticipant participant : participants) {
            boolean hasFinished = participant.getCurrentDistance() >= lengthInMeters;
            boolean alreadyRecorded = finishOrder.contains(participant);

            if (hasFinished && !alreadyRecorded) {
                finishOrder.add(participant);
            }
        }
    }

    private void sortCurrentStandings() {
        participants.sort(
                Comparator.comparingInt(RaceParticipant::getCurrentDistance).reversed()
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
        int index = finishOrder.indexOf(horse);

        if (index < 0) {
            throw new IllegalArgumentException("Horse has not finished the race.");
        }

        return Placement.values()[index];
    }

    public int getLengthInMeters() {
        return lengthInMeters;
    }
}