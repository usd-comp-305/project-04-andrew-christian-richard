package edu.sandiego.comp305;

import java.util.List;

public class NeutralEvent extends Event {
    private final List<EventChoice> choices;

    public NeutralEvent(String description, Horse horse) {
        super(description, horse);
        this.choices = List.of(
                new EventChoice("Try to gain a lead",
                        getFairEffect(), StaminaChange.LOSS),
                new EventChoice("Do nothing",
                        NEUTRAL_OUTCOME, StaminaChange.FREE),
                new EventChoice("Pace slowly and conserve some energy",
                        getOkayEffect(), StaminaChange.GAIN)
        );
    }

    @Override
    public List<EventChoice> getChoices() {
        return choices;
    }

    private RaceEffect getFairEffect() {
        int reductionSpeedStat = (int) (horse.getStats().getSpeed() *
                EventStatMultiplier.FAIR.getSpeedMultiplier());
        int reductionPowerStat = (int) (horse.getStats().getPower() *
                EventStatMultiplier.FAIR.getPowerMultiplier());
        return new RaceEffect(reductionSpeedStat, reductionPowerStat);
    }

    private RaceEffect getOkayEffect() {
        int reductionSpeedStat = (int) (horse.getStats().getSpeed() *
                EventStatMultiplier.OKAY.getSpeedMultiplier());
        int reductionPowerStat = (int) (horse.getStats().getPower() *
                EventStatMultiplier.OKAY.getPowerMultiplier());
        return new RaceEffect(reductionSpeedStat, reductionPowerStat);
    }
}