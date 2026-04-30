package edu.sandiego.comp305;

public class EventChoice {
    private final String label;
    private final String outcomeText;
    private final RaceEffect effect;
    private final StaminaChange change;

    public EventChoice(String label, String outcomeText, RaceEffect effect, StaminaChange change) {
        this.label = label;
        this.outcomeText = outcomeText;
        this.effect = effect;
        this.change = change;
    }

    public RaceEffect getEffect() {
        return effect;
    }

    public StaminaChange getChange() {
        return change;
    }

    public String getLabel() {
        return label;
    }

    public String getOutcomeText() {
        return outcomeText;
    }
}
