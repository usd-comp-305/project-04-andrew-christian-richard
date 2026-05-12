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
        if (currentRaceIndex >= races.size()) {
            return null;
        }

        Race next = races.get(currentRaceIndex);
        currentRaceIndex++;
        return next;
    }

    public List<Race> generateRaces(Horse playerHorse) {
        races.clear();
        currentRaceIndex = 0;

        Track easyTrack = new Track(TrackType.ONE_HUNDRED_METER, TrackType.ONE_HUNDRED_METER.getLength());
        Track mediumTrack = new Track(TrackType.TWO_HUNDRED_METER, TrackType.TWO_HUNDRED_METER.getLength());
        Track hardTrack = new Track(TrackType.FOUR_HUNDRED_METER, TrackType.FOUR_HUNDRED_METER.getLength());

        List<RaceParticipant> easyParticipants = new ArrayList<>();
        easyParticipants.add(playerHorse);
        easyParticipants.add(new Horse("Dusty", new Stats(3, 5, 3)));
        easyParticipants.add(new Horse("Blaze", new Stats(4, 4, 3)));

        List<RaceParticipant> mediumParticipants = new ArrayList<>();
        mediumParticipants.add(playerHorse);
        mediumParticipants.add(new Horse("Thunder", new Stats(5, 5, 4)));
        mediumParticipants.add(new Horse("Storm", new Stats(6, 4, 4)));

        List<RaceParticipant> hardParticipants = new ArrayList<>();
        hardParticipants.add(playerHorse);
        hardParticipants.add(new Horse("Shadow", new Stats(7, 6, 6)));
        hardParticipants.add(new Horse("Eclipse", new Stats(8, 5, 7)));

        races.add(new Race("Easy Race", Difficulty.EASY, easyTrack, easyParticipants));
        races.add(new Race("Medium Race", Difficulty.MEDIUM, mediumTrack, mediumParticipants));
        races.add(new Race("Hard Race", Difficulty.HARD, hardTrack, hardParticipants));

        return races;
    }
}