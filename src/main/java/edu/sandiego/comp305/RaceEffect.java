package edu.sandiego.comp305;

public class RaceEffect {
    private final int speedChange;
    private final int staminaChange;
    private final int powerChange;
    private final String description;


    public RaceEffect(int speedChange, int staminaChange, int powerChange, String description) {
        this.speedChange = speedChange;
        this.staminaChange = staminaChange;
        this.powerChange = powerChange;
        this.description = description;
    }

    public int getPowerChange() {
        return powerChange;
    }

    public int getSpeedChange() {
        return speedChange;
    }

    public int getStaminaChange() {
        return staminaChange;
    }

    public String getDescription() {
        return description;
    }
}
