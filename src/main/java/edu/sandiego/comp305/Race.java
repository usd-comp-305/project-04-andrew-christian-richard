package edu.sandiego.comp305;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Race {
    private static final int EVENT_ROUND_INTERVAL = 3;

    private static final int STAMINA_DEPLETION_INTERVAL = 5;

    private static final int MAX_NUM_ROUNDS = 60;

    private final Difficulty difficulty;

    private final int lengthInMeters;

    private final List<RaceParticipant> participants;

    private final List<RaceParticipant> finishOrder;

    private AbstractEventFactory eventFactory;

    private final EventDescriptionProvider descriptor;

    private RaceState state;

    private int round;

    private Event event;

    private Horse playerHorse;

    private int currentPlayerStamina;

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
        this.playerHorse = null;
        this.currentPlayerStamina = 0;
    }

    public void setPlayerHorse(final Horse playerHorse) {
        if (playerHorse == null) {
            throw new IllegalArgumentException("Player horse cannot be null.");
        }

        this.playerHorse = playerHorse;
        this.currentPlayerStamina = playerHorse.getStats().getStamina();

        if (!participants.contains(playerHorse)) {
            participants.add(playerHorse);
        }
    }

    public void addParticipant(final RaceParticipant participant) {
        if (participant == null) {
            throw new IllegalArgumentException("Participant cannot be null.");
        }

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
        if (round > MAX_NUM_ROUNDS) {
            state = RaceState.FINISHED;
            return;
        }

        event = null;

        if (playerHorse == null) {
            throw new IllegalStateException("Race does not have a player horse.");
        }

        eventFactory = new AbstractEventFactory(playerHorse, descriptor);

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

        if (playerHorse == null) {
            throw new IllegalStateException("Race does not have a player horse.");
        }

        final RaceEffect effect = selectedChoice.getEffect();
        playerHorse.applyRaceEffect(effect);

        applyStaminaChange(selectedChoice.getChange());

        event = null;
    }

    public void executeRound() {
        if (state != RaceState.IN_PROGRESS) {
            return;
        }

        moveParticipants();
        updateFinishOrder();
        sortCurrentStandings();
        decreaseStamina();

        if (finishOrder.contains(playerHorse) || round >= MAX_NUM_ROUNDS) {
            state = RaceState.FINISHED;
        } else {
            round++;
        }
    }

    private void applyStaminaChange(final StaminaChange staminaChange) {
        currentPlayerStamina += staminaChange.getChange();

        if (currentPlayerStamina < 0) {
            currentPlayerStamina = 0;
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
        return playerHorse;
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
            throw new IllegalArgumentException("Horse has not finished the race.");
        }

        return Placement.values()[index];
    }

    public int getLengthInMeters() {
        return lengthInMeters;
    }

    public int getCurrentPlayerStamina() {
        return currentPlayerStamina;
    }

    private void decreaseStamina() {
        if (round % STAMINA_DEPLETION_INTERVAL == 0) {
            currentPlayerStamina--;
        }
    }

}
