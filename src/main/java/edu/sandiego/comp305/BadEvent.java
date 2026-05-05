package edu.sandiego.comp305;

import java.util.List;

public class BadEvent extends Event {
    private final List<EventChoice> choices;

    public BadEvent(String description, Horse horse){
        super(description, horse);
        this.choices = List.of(
                new EventChoice("Shake it off and try to catch up",
                        NEUTRAL_OUTCOME, StaminaChange.COSTLY_LOST),
                new EventChoice("Do not worry what is ahead and stabilize",
                        getBadEffect(), StaminaChange.LOSS),
                new EventChoice("Slow down heavily and catch up later",
                        getPoorEffect(), StaminaChange.FREE)
        );
    }

    @Override
    public List<EventChoice> getChoices(){
        return choices;
    }

    private RaceEffect getPoorEffect(){
        int reductionSpeedStat = (int) (horse.getStats().getSpeed() *
                EventStatMultiplier.POOR.getSpeedMultiplier());
        int reductionPowerStat = (int) (horse.getStats().getPower() *
                EventStatMultiplier.POOR.getPowerMultiplier());
        return new RaceEffect(reductionSpeedStat, reductionPowerStat);
    }

    private RaceEffect getBadEffect(){
        int reductionSpeedStat = (int) (horse.getStats().getSpeed() *
                EventStatMultiplier.BAD.getSpeedMultiplier());
        int reductionPowerStat = (int) (horse.getStats().getPower() *
                EventStatMultiplier.BAD.getPowerMultiplier());
        return new RaceEffect(reductionSpeedStat, reductionPowerStat);
    }
}
