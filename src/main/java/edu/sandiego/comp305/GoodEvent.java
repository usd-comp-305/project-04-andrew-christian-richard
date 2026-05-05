package edu.sandiego.comp305;
import java.util.List;

public class GoodEvent extends Event {
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
        int bonusSpeedStat = (int) (horse.getStats().getSpeed() *
                EventStatMultiplier.GREAT.getSpeedMultiplier());
        int bonusPowerStat = (int) (horse.getStats().getPower() *
                EventStatMultiplier.GREAT.getPowerMultiplier());
        return new RaceEffect(bonusSpeedStat, bonusPowerStat);
    }

    private RaceEffect getGoodEffect(){
        int bonusSpeedStat = (int) (horse.getStats().getSpeed() *
                EventStatMultiplier.GOOD.getSpeedMultiplier());
        int bonusPowerStat = (int) (horse.getStats().getPower() *
                EventStatMultiplier.GOOD.getPowerMultiplier());
        return new RaceEffect(bonusSpeedStat, bonusPowerStat);
    }
}