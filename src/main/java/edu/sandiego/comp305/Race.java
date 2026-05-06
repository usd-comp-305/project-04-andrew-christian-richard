package edu.sandiego.comp305;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Race {
    private static final int PLACEMENT_DISPLAY_CHECKPOINT = 20;

    private final String raceName;
    private final Difficulty difficulty;
    private final Track track;
    private final List<RaceParticipant> participants;
    private final List<QuickTimeEvent> events;
    private RaceState state;
    private boolean hasShownPlacementAt20m;

    public Race(String raceName, Difficulty difficulty, Track track, List<RaceParticipant> participants) {
        this.raceName = raceName;
        this.difficulty = difficulty;
        this.track = track;
        this.participants = new ArrayList<>(participants);
        this.events = new ArrayList<>();
        this.state = RaceState.NOT_STARTED;
        this.hasShownPlacementAt20m = false;
    }

    public void startRace() {
        if (state != RaceState.NOT_STARTED) {
            return;
        }

        for (RaceParticipant participant : participants) {
            if (participant instanceof Horse horse) {
                horse.resetForCurrentRace();
            }
        }

        state = RaceState.IN_PROGRESS;
    }

    public void advanceRound() {
        if (state != RaceState.IN_PROGRESS) {
            return;
        }

        for (RaceParticipant participant : participants) {
            if (!participant.hasFinished(track.getLengthInMeters())) {
                participant.move();
            }
        }

        triggerCheckpointEvents();

        if (allParticipantsFinished()) {
            state = RaceState.FINISHED;
        }
    }

    public void triggerCheckpointEvents() {
        if (!hasShownPlacementAt20m) {
            for (RaceParticipant participant : participants) {
                if (participant.getCurrentDistance() >= PLACEMENT_DISPLAY_CHECKPOINT) {
                    showPlacementAt20m();
                    hasShownPlacementAt20m = true;
                    break;
                }
            }
        }
    }

    public void showPlacementAt20m() {
        List<RaceParticipant> standings = getCurrentStandings();

        System.out.println("Standings at 20m:");package edu.sandiego.comp305;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

        public class Race {
            private static final int PLACEMENT_DISPLAY_CHECKPOINT = 20;

            private final String raceName;
            private final Difficulty difficulty;
            private final Track track;
            private final List<RaceParticipant> participants;
            private final List<QuickTimeEvent> events;
            private RaceState state;
            private boolean hasShownPlacementAt20m;

            public Race(String raceName, Difficulty difficulty, Track track, List<RaceParticipant> participants) {
                this.raceName = raceName;
                this.difficulty = difficulty;
                this.track = track;
                this.participants = new ArrayList<>(participants);
                this.events = new ArrayList<>();
                this.state = RaceState.NOT_STARTED;
                this.hasShownPlacementAt20m = false;
            }

            public void startRace() {
                if (state != RaceState.NOT_STARTED) {
                    return;
                }

                for (RaceParticipant participant : participants) {
                    if (participant instanceof Horse horse) {
                        horse.resetForCurrentRace();
                    }
                }

                state = RaceState.IN_PROGRESS;
            }

            public void advanceRound() {
                if (state != RaceState.IN_PROGRESS) {
                    return;
                }

                for (RaceParticipant participant : participants) {
                    if (!participant.hasFinished(track.getLengthInMeters())) {
                        participant.move();
                    }
                }

                triggerCheckpointEvents();

                if (allParticipantsFinished()) {
                    state = RaceState.FINISHED;
                }
            }

            public void triggerCheckpointEvents() {
                if (!hasShownPlacementAt20m) {
                    for (RaceParticipant participant : participants) {
                        if (participant.getCurrentDistance() >= PLACEMENT_DISPLAY_CHECKPOINT) {
                            showPlacementAt20m();
                            hasShownPlacementAt20m = true;
                            break;
                        }
                    }
                }
            }

            public void showPlacementAt20m() {
                List<RaceParticipant> standings = getCurrentStandings();

                System.out.println("Standings at 20m:");

                for (int i = 0; i < standings.size(); i++) {
                    RaceParticipant participant = standings.get(i);

                    System.out.println(
                            (i + 1) + ". " +
                                    participant.getName() +
                                    " - " +
                                    participant.getCurrentDistance() +
                                    "m"
                    );
                }
            }

            public List<RaceParticipant> getCurrentStandings() {
                List<RaceParticipant> standings = new ArrayList<>(participants);

                standings.sort(
                        Comparator.comparingInt(RaceParticipant::getCurrentDistance).reversed()
                );

                return standings;
            }

            public Placement getPlacement(Horse horse) {
                List<RaceParticipant> standings = getCurrentStandings();

                for (int i = 0; i < standings.size(); i++) {
                    RaceParticipant participant = standings.get(i);

                    if (participant == horse) {
                        return Placement.values()[i];
                    }
                }

                return Placement.SIXTH;
            }

            private boolean allParticipantsFinished() {
                for (RaceParticipant participant : participants) {
                    if (!participant.hasFinished(track.getLengthInMeters())) {
                        return false;
                    }
                }

                return true;
            }
        }

        for (int i = 0; i < standings.size(); i++) {
            RaceParticipant participant = standings.get(i);

            System.out.println(
                    (i + 1) + ". " +
                            participant.getName() +
                            " - " +
                            participant.getCurrentDistance() +
                            "m"
            );
        }
    }

    public List<RaceParticipant> getCurrentStandings() {
        List<RaceParticipant> standings = new ArrayList<>(participants);

        standings.sort(
                Comparator.comparingInt(RaceParticipant::getCurrentDistance).reversed()
        );

        return standings;
    }

    public Placement getPlacement(Horse horse) {
        List<RaceParticipant> standings = getCurrentStandings();

        for (int i = 0; i < standings.size(); i++) {
            RaceParticipant participant = standings.get(i);

            if (participant == horse) {
                return Placement.values()[i];
            }
        }

        return Placement.SIXTH;
    }

    private boolean allParticipantsFinished() {
        for (RaceParticipant participant : participants) {
            if (!participant.hasFinished(track.getLengthInMeters())) {
                return false;
            }
        }

        return true;
    }
}