package edu.sandiego.comp305;

import java.util.List;

public class BadEvent extends Event {
    private final List<EventChoice> eventChoices;

    public BadEvent(final String description, final Horse horse){
        super(description, horse);
        this.eventChoices = List.of(
                new EventChoice("Shake it off and try to catch up",
                        NEUTRAL_OUTCOME, StaminaChange.COSTLY_LOST),
                new EventChoice("Do not worry what is ahead and stabilize",
                        getBadEffect(), StaminaChange.LOSS),
                new EventChoice("Slow down heavily and catch up later",
                        getVeryBadEffect(), StaminaChange.FREE)
        );
    }

    @Override
    public List<EventChoice> getEventChoices() {
        return eventChoices;
    }

    private RaceEffect getVeryBadEffect(){
        final Stats stats = getHorseStats();
        final int reductionSpeed = (int) (stats.getSpeed()
                * EventStatMultiplier.VERYBAD.getSpeedMultiplier());
        final int reductionPower = (int) (stats.getPower()
                * EventStatMultiplier.VERYBAD.getPowerMultiplier());
        return new RaceEffect(reductionSpeed, reductionPower);
    }

    private RaceEffect getBadEffect(){
        final Stats stats = getHorseStats();
        final int reductionSpeed = (int) (stats.getSpeed()
                * EventStatMultiplier.BAD.getSpeedMultiplier());
        final int reductionPower = (int) (stats.getPower()
                * EventStatMultiplier.BAD.getPowerMultiplier());
        return new RaceEffect(reductionSpeed, reductionPower);
    }
}
