package edu.sandiego.comp305;

import java.util.*;

public class Race {
    private final String raceName;
    private final Difficulty difficulty;
    private final Track track;
    private final List<RaceParticipant> participants;
    private final List<Event> events;
    private RaceState state;

    public Race(String raceName, Difficulty difficulty, Track track, List<RaceParticipant> participants) {
        this.raceName = raceName;
        this.difficulty = difficulty;
        this.track = track;
        this.participants = new ArrayList<>(participants);
        this.events = new ArrayList<>();
        this.state = RaceState.NOT_STARTED;
    }

    public void advanceRound() {
        if (state != RaceState.IN_PROGRESS) {
            return;
        }

        for (RaceParticipant participant : participants) {
            if (!participant.hasFinished(track.getLengthInMeters())) {
                int distanceMoved = participant.move();
            }
        }

        triggerCheckpointEvents();
        checkAllFinished();
    }

    private void checkAllFinished() {
        boolean allFinished = true;
        for (RaceParticipant participant : participants) {
            if (!participant.hasFinished(track.getLengthInMeters())) {
                allFinished = false;
                break;
            }
        }

        if (allFinished) {
            state = RaceState.FINISHED;
        }
    }

    public void triggerCheckpointEvents() {
        List<Integer> eventCheckpoints = track.getEventCheckpoints();
        if (eventCheckpoints == null) {
            return;
        }

        for (RaceParticipant participant : participants) {
            for (int checkpoint : eventCheckpoints) {
                if (participant.getCurrentDistance() >= checkpoint) {
                }
            }
        }
    }

    public List<RaceParticipant> getCurrentStandings() {
        List<RaceParticipant> sorted = new ArrayList<>(participants);

        for (int i = 0; i < sorted.size() - 1; i++) {
            for (int j = 0; j < sorted.size() - 1 - i; j++) {
                if (sorted.get(j).getCurrentDistance() < sorted.get(j + 1).getCurrentDistance()) {
                    RaceParticipant temp = sorted.get(j);
                    sorted.set(j, sorted.get(j + 1));
                    sorted.set(j + 1, temp);
                }
            }
        }

        return sorted;
    }

    public Placement getPlacement(Horse horse) {
        List<RaceParticipant> standings = getCurrentStandings();
        Placement[] placements = Placement.values();

        for (int i = 0; i < standings.size(); i++) {
            if (standings.get(i).getName().equals(horse.getName())) {
                if (i < placements.length) {
                    return placements[i];
                }
            }
        }

        return null;
    }
}