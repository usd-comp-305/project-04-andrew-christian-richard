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

    @BeforeEach
    public void setUp() {
        race = new Race(Difficulty.MEDIUM, 1000);

        mockHorse = mock(Horse.class);
        mockNPC = mock(RaceParticipant.class);
        mockChoice = mock(EventChoice.class);
    }

    @Test
    public void initialStateIsNotStarted() {
        assertEquals(RaceState.NOT_STARTED, race.getState());
    }

    @Test
    public void initialRoundIsZero() {
        assertEquals(0, race.getRound());
    }

    @Test
    public void initialParticipantListIsEmpty() {
        assertTrue(race.getCurrentStandings().isEmpty());
    }

    @Test
    public void initialFinishOrderIsEmpty() {
        assertTrue(race.getFinishOrder().isEmpty());
    }

    @Test
    public void lengthInMeters_isStoredCorrectly() {
        final Race shortRace = new Race(Difficulty.EASY, 500);
        assertEquals(500, shortRace.getLengthInMeters());
    }

    @Test
    public void difficulty_StoredCorrectly() {
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

        final List<RaceParticipant> standings = race.getCurrentStandings();

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
    public void resolveEvent_ThrowsWhenChoiceIsNull() {
        final Horse playerHorse = new Horse(
                "Player",
                new Stats(5, 5, 5)
        );

        race.setPlayerHorse(playerHorse);

        assertThrows(IllegalArgumentException.class, () -> {
            race.resolveEvent(null);
        });
    }

    @Test
    public void resolveEvent_AppliesEffect() {
        final Horse playerHorse = new Horse(
                "Player",
                new Stats(5, 5, 5)
        );

        final RaceEffect effect = new RaceEffect(2, 3);

        race.setPlayerHorse(playerHorse);
        when(mockChoice.getEffect()).thenReturn(effect);
        when(mockChoice.getChange()).thenReturn(StaminaChange.FREE);

        race.resolveEvent(mockChoice);

        final Horse result = race.getPlayerHorse();

        assertEquals(7, result.getStats().getMaxMovementDistance(effect));
        assertEquals(8, result.getStats().getMinMovementDistance(effect));
    }

    @Test
    public void resolveEvent_ClearsTheEvent() {
        final Horse playerHorse = new Horse(
                "Player",
                new Stats(5, 5, 5)
        );

        race.setPlayerHorse(playerHorse);
        when(mockChoice.getEffect()).thenReturn(new RaceEffect(0, 0));
        when(mockChoice.getChange()).thenReturn(StaminaChange.FREE);

        race.resolveEvent(mockChoice);

        assertFalse(race.hasEvent());
    }

    @Test
    public void getPlayerHorse_ReturnsHorseCopyWhenPresent() {
        final Horse playerHorse = new Horse(
                "Player",
                new Stats(5, 6, 7)
        );

        race.setPlayerHorse(playerHorse);

        final Horse result = race.getPlayerHorse();

        assertNotNull(result);
        assertNotSame(playerHorse, result);
        assertEquals(playerHorse.getName(), result.getName());
        assertEquals(
                playerHorse.getStats().getSpeed(),
                result.getStats().getSpeed()
        );
        assertEquals(
                playerHorse.getStats().getStamina(),
                result.getStats().getStamina()
        );
        assertEquals(
                playerHorse.getStats().getPower(),
                result.getStats().getPower()
        );
    }

    @Test
    public void executeRound_DoesNothingIfNotStarted() {
        race.addParticipant(mockHorse);
        race.executeRound();

        verify(mockHorse, never()).move();
    }

    @Test
    public void executeRound_CallsMoveOnParticipants() {
        when(mockHorse.getCurrentDistance()).thenReturn(0);

        race.addParticipant(mockHorse);
        race.start();
        race.executeRound();

        verify(mockHorse, times(1)).move();
    }

    @Test
    public void executeRound_IncrementsRound() {
        when(mockHorse.getCurrentDistance()).thenReturn(0);

        race.addParticipant(mockHorse);
        race.start();
        race.executeRound();

        assertEquals(2, race.getRound());
    }

    @Test
    public void executeRound_SetsFinishedWhenAllParticipantsCrossLine() {
        when(mockHorse.getCurrentDistance()).thenReturn(1000);

        race.addParticipant(mockHorse);
        race.start();
        race.executeRound();

        assertTrue(race.isFinished());
    }

    @Test
    public void executeRound_DoesNotFinishIfSomeParticipantsStillRunning() {
        when(mockHorse.getCurrentDistance()).thenReturn(1000);
        when(mockNPC.getCurrentDistance()).thenReturn(500);

        race.addParticipant(mockHorse);
        race.addParticipant(mockNPC);
        race.start();
        race.executeRound();

        assertFalse(race.isFinished());
    }

    @Test
    public void executeRound_DoesNotMoveParticipantsThatHaveAlreadyFinished() {
        when(mockHorse.getCurrentDistance()).thenReturn(1000);
        when(mockNPC.getCurrentDistance()).thenReturn(500);

        race.addParticipant(mockHorse);
        race.addParticipant(mockNPC);
        race.start();
        race.executeRound();

        clearInvocations(mockHorse);

        when(mockNPC.getCurrentDistance()).thenReturn(1000);
        race.executeRound();

        verify(mockHorse, never()).move();
    }

    @Test
    public void getPlayerHorse_ReturnsNullWhenNoHorseAdded() {
        race.addParticipant(mockNPC);
        assertNull(race.getPlayerHorse());
    }

    @Test
    public void currentStandingsIsUnmodifiable() {
        race.addParticipant(mockHorse);
        final List<RaceParticipant> standings = race.getCurrentStandings();

        assertThrows(UnsupportedOperationException.class, () -> {
            standings.add(mockNPC);
        });
    }

    @Test
    public void finishOrder_IsUnmodifiable() {
        final List<RaceParticipant> finishOrder = race.getFinishOrder();

        assertThrows(UnsupportedOperationException.class, () -> {
            finishOrder.add(mockHorse);
        });
    }

    @Test
    public void getPlacement_ThrowsForHorseThatHasNotFinished() {
        final Horse playerHorse = new Horse(
                "Player",
                new Stats(5, 5, 5)
        );

        race.setPlayerHorse(playerHorse);
        race.start();

        assertThrows(IllegalArgumentException.class, () -> {
            race.getPlayerPlacement();
        });
    }

    @Test
    public void getPlacement_ReturnsFirstForWinner() {
        final Horse playerHorse = new Horse(
                "Player",
                new Stats(1001, 5, 1000)
        );

        race.setPlayerHorse(playerHorse);
        race.start();
        race.executeRound();

        assertEquals(Placement.values()[0], race.getPlayerPlacement());
    }

    @Test
    public void getPlacement_ReturnsSecondForRunnerUp() {
        final Horse playerHorse = new Horse(
                "Player",
                new Stats(1001, 5, 1000)
        );

        when(mockNPC.getCurrentDistance()).thenReturn(1000);

        race.addParticipant(mockNPC);
        race.setPlayerHorse(playerHorse);
        race.start();
        race.executeRound();

        assertEquals(Placement.values()[1], race.getPlayerPlacement());
    }
}