package edu.sandiego.comp305;

import java.util.List;

public class NeutralEvent extends Event {
    private final List<EventChoice> eventChoices;

    public NeutralEvent(String description, Horse horse) {
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
        int bonusSpeed = (int) (horse.getStats().getSpeed() *
                EventStatMultiplier.FAIR.getSpeedMultiplier());
        int bonusPower = (int) (horse.getStats().getPower() *
                EventStatMultiplier.FAIR.getPowerMultiplier());
        return new RaceEffect(bonusSpeed, bonusPower);
    }

    private RaceEffect getOkayEffect() {
        int reductionSpeed = (int) (horse.getStats().getSpeed() *
                EventStatMultiplier.OKAY.getSpeedMultiplier());
        int reductionPower = (int) (horse.getStats().getPower() *
                EventStatMultiplier.OKAY.getPowerMultiplier());
        return new RaceEffect(reductionSpeed, reductionPower);
    }
}