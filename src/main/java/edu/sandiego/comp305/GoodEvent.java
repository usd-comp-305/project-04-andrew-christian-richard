package edu.sandiego.comp305;
import java.util.List;

public class GoodEvent extends Event {
    private static final RaceEffect GREAT_OUTCOME = new RaceEffect(2, 1);
    private static final RaceEffect GOOD_OUTCOME = new RaceEffect(1, 1);
    private static final RaceEffect NEUTRAL_OUTCOME = new RaceEffect(1, 0);
    private final List<EventChoice> choices;

    public GoodEvent(String description){
        super(description);
        this.choices = List.of(
                new EventChoice("Use the momentum, and try to get a bigger lead!",
                        GREAT_OUTCOME, StaminaChange.COSTLY_LOST),
                new EventChoice("Speed up a little",
                        GOOD_OUTCOME, StaminaChange.LOSS),
                new EventChoice("Hold pace and remain steady",
                        NEUTRAL_OUTCOME, StaminaChange.FREE)
        );
    }

    @Override
    public List<EventChoice> getChoices(){
        return choices;
    }
}