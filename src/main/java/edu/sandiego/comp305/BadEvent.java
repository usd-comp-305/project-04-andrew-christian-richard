package edu.sandiego.comp305;

import java.util.List;

public class BadEvent extends Event {
    private static final double POOR_SPEED_MULTIPLIER = -.5;
    private static final double POOR_POWER_MULTIPLIER = -.2;
    private static final double BAD_SPEED_MULTIPLIER = -.3;
    private static final double BAD_POWER_MULTIPLIER = -.1;
    private static final RaceEffect NEUTRAL_OUTCOME = new RaceEffect(0, 0);
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
        int reductionSpeedStat = (int) (horse.getStats().getSpeed() * POOR_SPEED_MULTIPLIER);
        int reductionPowerStat = (int) (horse.getStats().getPower() * POOR_POWER_MULTIPLIER);
        return new RaceEffect(reductionSpeedStat, reductionPowerStat);
    }

    private RaceEffect getBadEffect(){
        int reductionSpeedStat = (int) (horse.getStats().getSpeed() * BAD_SPEED_MULTIPLIER);
        int reductionPowerStat = (int) (horse.getStats().getPower() * BAD_POWER_MULTIPLIER);
        return new RaceEffect(reductionSpeedStat, reductionPowerStat);
    }
}
