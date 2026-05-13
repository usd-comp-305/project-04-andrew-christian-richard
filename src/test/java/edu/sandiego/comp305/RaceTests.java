package edu.sandiego.comp305;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RaceTests {

    private Race race;
    private Horse mockHorse;
    private RaceParticipant mockNPC;
    private EventChoice mockChoice;
    private RaceEffect mockEffect;

    @BeforeEach
    public void setUp() {
        race = new Race(Difficulty.MEDIUM, 1000);

        mockHorse  = mock(Horse.class);
        mockNPC    = mock(RaceParticipant.class);
        mockChoice = mock(EventChoice.class);
        mockEffect = mock(RaceEffect.class);
    }

    @Test
    public void testInitialStateIsNotStarted() {
        assertEquals(RaceState.NOT_STARTED, race.getState());
    }

    @Test
    public void testInitialRoundIsZero() {
        assertEquals(0, race.getRound());
    }

    @Test
    public void testInitialParticipantListIsEmpty() {
        assertTrue(race.getCurrentStandings().isEmpty());
    }

    @Test
    public void testInitialFinishOrderIsEmpty() {
        assertTrue(race.getFinishOrder().isEmpty());
    }

    @Test
    public void testLengthInMetersStoredCorrectly() {
        Race shortRace = new Race(Difficulty.EASY, 500);
        assertEquals(500, shortRace.getLengthInMeters());
    }

    @Test
    public void testDifficultyStoredCorrectly() {
        assertEquals(Difficulty.MEDIUM, race.getDifficulty());
    }

    @Test
    public void testAddParticipantIncreasesStandingsSize() {
        race.addParticipant(mockHorse);
        assertEquals(1, race.getCurrentStandings().size());
    }

    @Test
    public void testAddMultipleParticipantsAllAppearInStandings() {
        race.addParticipant(mockHorse);
        race.addParticipant(mockNPC);

        List<RaceParticipant> standings = race.getCurrentStandings();

        assertTrue(standings.contains(mockHorse));
        assertTrue(standings.contains(mockNPC));
    }

    @Test
    public void testStartSetsStateToInProgress() {
        race.start();
        assertEquals(RaceState.IN_PROGRESS, race.getState());
    }

    @Test
    public void testStartSetsRoundToOne() {
        race.start();
        assertEquals(1, race.getRound());
    }

    @Test
    public void testIsFinishedReturnsFalseBeforeStart() {
        assertFalse(race.isFinished());
    }

    @Test
    public void testIsFinishedReturnsFalseAfterStart() {
        race.start();
        assertFalse(race.isFinished());
    }

    @Test
    public void testHasEventReturnsFalseInitially() {
        assertFalse(race.hasEvent());
    }

    @Test
    public void testGetEventReturnsNullInitially() {
        assertNull(race.getEvent());
    }

    @Test
    public void testPrepareRoundClearsExistingEvent() {
        race.prepareRound();
        race.prepareRound();
        assertFalse(race.hasEvent());
    }

    @Test
    public void testResolveEventThrowsWhenChoiceIsNull() {
        race.addParticipant(mockHorse);

        assertThrows(IllegalArgumentException.class, () -> {
            race.resolveEvent(null);
        });
    }

    @Test
    public void testResolveEventThrowsWhenNoPlayerHorse() {
        // no Horse in the race, only an NPC
        race.addParticipant(mockNPC);

        assertThrows(IllegalStateException.class, () -> {
            race.resolveEvent(mockChoice);
        });
    }

    @Test
    public void testResolveEventAppliesEffect() {
        race.addParticipant(mockHorse);
        when(mockChoice.getEffect()).thenReturn(mockEffect);

        race.resolveEvent(mockChoice);

        verify(mockHorse).applyRaceEffect(mockEffect);
    }

    @Test
    public void testResolveEventClearsTheEvent() {
        race.addParticipant(mockHorse);
        when(mockChoice.getEffect()).thenReturn(mockEffect);

        race.resolveEvent(mockChoice);

        assertFalse(race.hasEvent());
    }

    @Test
    public void testExecuteRoundDoesNothingIfNotStarted() {
        race.addParticipant(mockHorse);
        race.executeRound();

        verify(mockHorse, never()).move();
    }

    @Test
    public void testExecuteRoundCallsMoveOnParticipants() {
        when(mockHorse.getCurrentDistance()).thenReturn(0);

        race.addParticipant(mockHorse);
        race.start();
        race.executeRound();

        verify(mockHorse, times(1)).move();
    }

    @Test
    public void testExecuteRoundIncrementsRound() {
        when(mockHorse.getCurrentDistance()).thenReturn(0);

        race.addParticipant(mockHorse);
        race.start();
        race.executeRound();

        // started at 1, should now be 2
        assertEquals(2, race.getRound());
    }

    @Test
    public void testExecuteRoundSetsFinishedWhenAllParticipantsCrossLine() {
        when(mockHorse.getCurrentDistance()).thenReturn(1000);

        race.addParticipant(mockHorse);
        race.start();
        race.executeRound();

        assertTrue(race.isFinished());
    }

    @Test
    public void testExecuteRoundDoesNotFinishIfSomeParticipantsStillRunning() {
        when(mockHorse.getCurrentDistance()).thenReturn(1000);
        when(mockNPC.getCurrentDistance()).thenReturn(500);

        race.addParticipant(mockHorse);
        race.addParticipant(mockNPC);
        race.start();
        race.executeRound();

        assertFalse(race.isFinished());
    }

    @Test
    public void testExecuteRoundDoesNotMoveParticipantsThatHaveAlreadyFinished() {
        when(mockHorse.getCurrentDistance()).thenReturn(1000);
        when(mockNPC.getCurrentDistance()).thenReturn(500);

        race.addParticipant(mockHorse);
        race.addParticipant(mockNPC);
        race.start();
        race.executeRound(); // horse finishes here

        clearInvocations(mockHorse);

        when(mockNPC.getCurrentDistance()).thenReturn(1000);
        race.executeRound();

        verify(mockHorse, never()).move();
    }

    @Test
    public void testGetPlayerHorseReturnsNullWhenNoHorseAdded() {
        race.addParticipant(mockNPC);
        assertNull(race.getPlayerHorse());
    }

    @Test
    public void testGetPlayerHorseReturnsHorseWhenPresent() {
        race.addParticipant(mockHorse);
        assertEquals(mockHorse, race.getPlayerHorse());
    }

    @Test
    public void testCurrentStandingsIsUnmodifiable() {
        race.addParticipant(mockHorse);
        List<RaceParticipant> standings = race.getCurrentStandings();

        assertThrows(UnsupportedOperationException.class, () -> {
            standings.add(mockNPC);
        });
    }

    @Test
    public void testFinishOrderIsUnmodifiable() {
        List<RaceParticipant> finishOrder = race.getFinishOrder();

        assertThrows(UnsupportedOperationException.class, () -> {
            finishOrder.add(mockHorse);
        });
    }

    @Test
    public void testGetPlacementThrowsForHorseThatHasNotFinished() {
        race.addParticipant(mockHorse);
        when(mockHorse.getCurrentDistance()).thenReturn(0);
        race.start();

        assertThrows(IllegalArgumentException.class, () -> {
            race.getPlacement(mockHorse);
        });
    }

    @Test
    public void testGetPlacementReturnsFirstForWinner() {
        when(mockHorse.getCurrentDistance()).thenReturn(1000);
        race.addParticipant(mockHorse);
        race.start();
        race.executeRound();

        assertEquals(Placement.values()[0], race.getPlacement(mockHorse));
    }

    @Test
    public void testGetPlacementReturnsSecondForRunnerUp() {
        when(mockNPC.getCurrentDistance()).thenReturn(1000);
        when(mockHorse.getCurrentDistance()).thenReturn(1000);

        // NPC added first so it finishes first
        race.addParticipant(mockNPC);
        race.addParticipant(mockHorse);
        race.start();
        race.executeRound();

        assertEquals(Placement.values()[1], race.getPlacement(mockHorse));
    }
}