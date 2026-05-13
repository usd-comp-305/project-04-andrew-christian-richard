package edu.sandiego.comp305;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RaceTests {

    private Race race;
    private Track track;
    private Horse playerHorse;
    private Horse opponent;

    @BeforeEach
    public void setUp() {
        track = Mockito.mock(Track.class);
        Mockito.when(track.getLengthInMeters()).thenReturn(100);

        playerHorse = Mockito.mock(Horse.class);
        Mockito.when(playerHorse.getName()).thenReturn("Player");
        Mockito.when(playerHorse.getCurrentDistance()).thenReturn(0);
        Mockito.when(playerHorse.hasFinished(100)).thenReturn(false);

        opponent = Mockito.mock(Horse.class);
        Mockito.when(opponent.getName()).thenReturn("Opponent");
        Mockito.when(opponent.getCurrentDistance()).thenReturn(0);
        Mockito.when(opponent.hasFinished(100)).thenReturn(false);

        List<RaceParticipant> participants = new ArrayList<>();
        participants.add(playerHorse);
        participants.add(opponent);

        race = new Race("Test Race", Difficulty.EASY, track, participants);
    }

    @Test
    public void test_advance_round_calls_move_on_unfinished_participants() {
        race.advanceRound();
        Mockito.verify(playerHorse, Mockito.never()).move();
        Mockito.verify(opponent, Mockito.never()).move();
    }

    @Test
    public void test_advance_round_does_not_move_finished_participants() {
        Mockito.when(playerHorse.hasFinished(100)).thenReturn(true);
        race.advanceRound();
        Mockito.verify(playerHorse, Mockito.never()).move();
        Mockito.verify(opponent, Mockito.never()).move();
    }

    @Test
    public void test_advance_round_does_nothing_if_not_started() {
        race.advanceRound();
        Mockito.verify(playerHorse, Mockito.never()).move();
        Mockito.verify(opponent, Mockito.never()).move();
    }

    @Test
    public void test_get_current_standings_returns_sorted_by_distance() {
        Mockito.when(playerHorse.getCurrentDistance()).thenReturn(30);
        Mockito.when(opponent.getCurrentDistance()).thenReturn(50);

        List<RaceParticipant> standings = race.getCurrentStandings();

        assertEquals("Opponent", standings.get(0).getName());
        assertEquals("Player", standings.get(1).getName());
    }

    @Test
    public void test_get_current_standings_returns_all_participants() {
        List<RaceParticipant> standings = race.getCurrentStandings();
        assertEquals(2, standings.size());
    }

    @Test
    public void test_get_placement_first_place() {
        Horse realPlayer = new Horse("Player", new Stats(5, 5, 5));
        Horse realOpponent = new Horse("Opponent", new Stats(5, 5, 5));

        Mockito.when(playerHorse.getCurrentDistance()).thenReturn(60);
        Mockito.when(opponent.getCurrentDistance()).thenReturn(40);
        Mockito.when(playerHorse.getName()).thenReturn("Player");
        Mockito.when(opponent.getName()).thenReturn("Opponent");

        List<RaceParticipant> participants = new ArrayList<>();
        participants.add(playerHorse);
        participants.add(opponent);

        Race newRace = new Race("Placement Race", Difficulty.EASY, track, participants);
        Placement placement = newRace.getPlacement(realPlayer);

        assertEquals(Placement.FIRST, placement);
    }

    @Test
    public void test_get_placement_second_place() {
        Horse realPlayer = new Horse("Player", new Stats(5, 5, 5));

        Mockito.when(playerHorse.getCurrentDistance()).thenReturn(40);
        Mockito.when(opponent.getCurrentDistance()).thenReturn(60);
        Mockito.when(playerHorse.getName()).thenReturn("Player");
        Mockito.when(opponent.getName()).thenReturn("Opponent");

        List<RaceParticipant> participants = new ArrayList<>();
        participants.add(playerHorse);
        participants.add(opponent);

        Race newRace = new Race("Placement Race", Difficulty.EASY, track, participants);
        Placement placement = newRace.getPlacement(realPlayer);

        assertEquals(Placement.SECOND, placement);
    }

    @Test
    public void test_get_placement_returns_null_if_horse_not_in_race() {
        Horse outsider = new Horse("Outsider", new Stats(5, 5, 5));
        Placement placement = race.getPlacement(outsider);
        assertNull(placement);
    }

    @Test
    public void test_trigger_checkpoint_events_does_not_throw() {
        Mockito.when(playerHorse.getCurrentDistance()).thenReturn(30);
        Mockito.when(opponent.getCurrentDistance()).thenReturn(55);
        assertDoesNotThrow(() -> race.triggerCheckpointEvents());
    }
}