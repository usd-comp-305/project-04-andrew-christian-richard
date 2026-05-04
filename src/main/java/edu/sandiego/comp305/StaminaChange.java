package edu.sandiego.comp305;

public enum StaminaChange {
    COSTLY_LOST(-4),
    LOSS(-2),
    GAIN(2),
    FREE(0);

    private final int change;

    StaminaChange(int change) {
        this.change = change;
    }

    public int getChange(){
        return change;
    }
}
