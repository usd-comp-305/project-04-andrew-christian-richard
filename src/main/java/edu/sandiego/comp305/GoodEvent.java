package edu.sandiego.comp305;
import java.util.List;

public class GoodEvent extends Event {
    private final List<EventChoice> eventChoices;

    public GoodEvent(final String description, final Horse horse){
        super(description, horse);
        this.eventChoices = List.of(
                new EventChoice("Use the momentum, and CHARGE!",
                        getGreatEffect(), StaminaChange.LOSS),
                new EventChoice("Speed up!",
                        getGoodEffect(), StaminaChange.FREE),
                new EventChoice("Hold pace and remain steady...",
                        NEUTRAL_OUTCOME, StaminaChange.GAIN)
        );
    }

    @Override
    public List<EventChoice> getEventChoices() {
        return eventChoices;
    }

    private RaceEffect getGreatEffect() {
        final Stats stats = getHorseStats();
        final int bonusSpeedStat = (int) (stats.getSpeed()
                * EventStatMultiplier.GREAT.getSpeedMultiplier());
        final int bonusPowerStat = (int) (stats.getPower()
                * EventStatMultiplier.GREAT.getPowerMultiplier());
        return new RaceEffect(bonusSpeedStat, bonusPowerStat);
    }

    private RaceEffect getGoodEffect() {
        final Stats stats = getHorseStats();
        final int bonusSpeedStat = (int) (stats.getSpeed()
                * EventStatMultiplier.GOOD.getSpeedMultiplier());
        final int bonusPowerStat = (int) (stats.getPower()
                * EventStatMultiplier.GOOD.getPowerMultiplier());
        return new RaceEffect(bonusSpeedStat, bonusPowerStat);
    }
}
