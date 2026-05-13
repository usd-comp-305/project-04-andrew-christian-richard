package edu.sandiego.comp305;

import java.util.ArrayList;
import java.util.List;

public class RaceManager {
    private static final int ONE_HUNDRED_METERS = 100;

    private static final int TWO_HUNDRED_METERS = 200;

    private static final int FOUR_HUNDRED_METERS = 400;

    private final List<Race> races;

    private int currentRaceIndex;

    public RaceManager() {
        this.races = new ArrayList<>();
        this.currentRaceIndex = 0;
        initializeRaces();
    }

    public RaceManager(final RaceManager raceManager) {
        this.races = new ArrayList<>();
        this.currentRaceIndex = raceManager.currentRaceIndex;

        for (final Race race : raceManager.races) {
            races.add(new Race(race.getDifficulty(), race.getLengthInMeters()));
        }
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
                races.add(new Race(difficulty, length));
            }
        }
    }

    public boolean hasMoreRaces() {
        return currentRaceIndex < races.size();
    }

    public Race getNextRace() {
        if (!hasMoreRaces()) {
            return null;
        }

        final Race race = races.get(currentRaceIndex);
        currentRaceIndex++;
        return race;
    }

    public List<Race> generateRaces(final Horse playerHorse) {
        return null;
    }
  
    public void addRace(final Race race) {
        races.add(race);
    }
}
