package edu.sandiego.comp305;

import java.util.List;

public class NeutralEvent extends Event {
    private final List<EventChoice> eventChoices;

    public NeutralEvent(final String description, final Horse horse) {
        super(description, horse);
        this.eventChoices = List.of(
                new EventChoice("Try to gain a lead",
                        getFairEffect(), StaminaChange.LOSS),
                new EventChoice("Do nothing",
                        NEUTRAL_OUTCOME, StaminaChange.FREE),
                new EventChoice("Pace slowly and conserve some energy",
                        getOkayEffect(), StaminaChange.GAIN)
        );
    }

    @Override
    public List<EventChoice> getEventChoices() {
        return eventChoices;
    }

    private RaceEffect getFairEffect() {
        final Stats stats = getHorseStats();
        final int bonusSpeed = (int) (stats.getSpeed()
                * EventStatMultiplier.FAIR.getSpeedMultiplier());
        final int bonusPower = (int) (stats.getPower()
                * EventStatMultiplier.FAIR.getPowerMultiplier());
        return new RaceEffect(bonusSpeed, bonusPower);
    }

    private RaceEffect getOkayEffect() {
        final Stats stats = getHorseStats();
        final int reductionSpeed = (int) (stats.getSpeed()
                * EventStatMultiplier.OKAY.getSpeedMultiplier());
        final int reductionPower = (int) (stats.getPower()
                * EventStatMultiplier.OKAY.getPowerMultiplier());
        return new RaceEffect(reductionSpeed, reductionPower);
    }
}
