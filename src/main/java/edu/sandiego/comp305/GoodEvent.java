package edu.sandiego.comp305;
import java.util.List;

public class GoodEvent extends Event {
    private static final double SPEED_GREAT_MULTIPLIER = .25;
    private static final double SPEED_GOOD_MULTIPLIER = .1;
    private static final double POWER_GREAT_MULTIPLIER = .2;
    private static final double POWER_GOOD_MULTIPLIER = .5;
    private static final RaceEffect NEUTRAL_OUTCOME = new RaceEffect(0, 0);
    private final List<EventChoice> choices;

    public GoodEvent(String description, Horse horse){
        super(description, horse);
        this.choices = List.of(
                new EventChoice("Use the momentum, and CHARGE!",
                        getGreatEffect(), StaminaChange.LOSS),
                new EventChoice("Speed up!",
                        getGoodEffect(), StaminaChange.FREE),
                new EventChoice("Hold pace and remain steady...",
                        NEUTRAL_OUTCOME, StaminaChange.GAIN)
        );
    }

    @Override
    public List<EventChoice> getChoices(){
        return choices;
    }

    private RaceEffect getGreatEffect(){
        int bonusSpeedStat = (int) (horse.getStats().getSpeed() * SPEED_GREAT_MULTIPLIER);
        int bonusPowerStat = (int) (horse.getStats().getPower() * POWER_GREAT_MULTIPLIER);
        return new RaceEffect(bonusSpeedStat, bonusPowerStat);
    }

    private RaceEffect getGoodEffect(){
        int bonusSpeedStat = (int) (horse.getStats().getSpeed() * SPEED_GOOD_MULTIPLIER);
        int bonusPowerStat = (int) (horse.getStats().getPower() * POWER_GOOD_MULTIPLIER);
        return new RaceEffect(bonusSpeedStat, bonusPowerStat);
    }
}