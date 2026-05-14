package edu.sandiego.comp305;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class RaceManager {
    private static final int ONE_HUNDRED_METERS = 100;

    private static final int TWO_HUNDRED_METERS = 200;

    private static final int FOUR_HUNDRED_METERS = 400;

    private static final int NUM_OPPONENTS = 5;

    private final List<Race> races;

    private final Random random;

    private int currentRaceIndex;

    public RaceManager() {
        this.races = new ArrayList<>();
        this.random = new Random();
        this.currentRaceIndex = 0;
        initializeRaces();
    }

    public RaceManager(final RaceManager other) {
        this.races = new ArrayList<>();
        this.random = new Random();
        this.currentRaceIndex = 0;
        initializeRaces();
    }

    private void initializeRaces() {
        final int[] raceLengths = {
            ONE_HUNDRED_METERS,
            TWO_HUNDRED_METERS,
            FOUR_HUNDRED_METERS
        };

        final Difficulty[] difficulties = {
            Difficulty.EASY,
            Difficulty.MEDIUM,
            Difficulty.HARD
        };

        for (final int length : raceLengths) {
            for (final Difficulty difficulty : difficulties) {
                addRace(new Race(difficulty, length));
            }
        }
    }

    public boolean hasMoreRaces() {
        return currentRaceIndex < races.size();
    }

    public Race getNextRace(final Horse playerHorse) {
        if (!hasMoreRaces()) {
            return null;
        }

        final Race race = races.get(currentRaceIndex);
        currentRaceIndex++;

        playerHorse.resetForCurrentRace();

        race.setPlayerHorse(playerHorse);
        addOpponentsToRace(race);

        return race;
    }

    private void addOpponentsToRace(final Race race) {
        final TrackType trackType = getTrackType(race.getLengthInMeters());

        final AbstractOpponentHorseFactory opponentFactory =
                new AbstractOpponentHorseFactory(
                        race.getDifficulty(),
                        trackType,
                        random::nextInt
                );

        final List<Horse> opponents =
                opponentFactory.createOpponentHorses(NUM_OPPONENTS);

        for (final Horse opponent : opponents) {
            race.addParticipant(opponent);
        }
    }

    private TrackType getTrackType(final int lengthInMeters) {
        if (lengthInMeters == ONE_HUNDRED_METERS) {
            return TrackType.ONE_HUNDRED_METER;
        } else if (lengthInMeters == TWO_HUNDRED_METERS) {
            return TrackType.TWO_HUNDRED_METER;
        } else if (lengthInMeters == FOUR_HUNDRED_METERS) {
            return TrackType.FOUR_HUNDRED_METER;
        }

        throw new IllegalArgumentException("Invalid race length.");
    }

    public void addRace(final Race race) {
        if (race == null) {
            throw new IllegalArgumentException("Race cannot be null.");
        }

        races.add(race);
    }
}
