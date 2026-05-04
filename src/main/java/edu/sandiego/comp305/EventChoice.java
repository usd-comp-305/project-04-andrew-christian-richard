package edu.sandiego.comp305;

public class EventChoice {
    private final String label;
    private final RaceEffect effect;
    private final StaminaChange change;

    public EventChoice(String label, RaceEffect effect, StaminaChange change) {
        this.label = label;
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

    @Override
    public String toString() {
        return label + " | Effect: " + effect + " | Stamina: " + change;
    }
}
