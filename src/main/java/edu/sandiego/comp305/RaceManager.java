package edu.sandiego.comp305;

import java.util.*;

public class RaceManager {
    private final List<Race> races;
    private int currentRaceIndex;

    public RaceManager() {
        this.races = new ArrayList<>();
        this.currentRaceIndex = 0;
    }

    public Race getNextRace() {
        return null;
    }

    public boolean hasNoMoreRaces(){
        return false;
    }

    public List<Race> generateRaces(Horse playerHorse) {
        return null;
    }
}
