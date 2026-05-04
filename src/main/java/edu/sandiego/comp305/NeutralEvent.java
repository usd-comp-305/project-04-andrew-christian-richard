package edu.sandiego.comp305;

import java.util.List;

public class NeutralEvent extends Event {
    private static final double FAIR_SPEED_MULTIPLIER = .1;
    private static final double FAIR_POWER_MULTIPLIER = .05;
    private static final double OKAY_SPEED_MULTIPLIER = -.1;
    private static final double OKAY_POWER_MULTIPLIER = -.05;
    private static final RaceEffect NEUTRAL_OUTCOME = new RaceEffect(0, 0);
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
        int reductionSpeedStat = (int) (horse.getStats().getSpeed() * FAIR_SPEED_MULTIPLIER);
        int reductionPowerStat = (int) (horse.getStats().getPower() * FAIR_POWER_MULTIPLIER);
        return new RaceEffect(reductionSpeedStat, reductionPowerStat);
    }

    private RaceEffect getOkayEffect() {
        int reductionSpeedStat = (int) (horse.getStats().getSpeed() * OKAY_SPEED_MULTIPLIER);
        int reductionPowerStat = (int) (horse.getStats().getPower() * OKAY_POWER_MULTIPLIER);
        return new RaceEffect(reductionSpeedStat, reductionPowerStat);
    }
}