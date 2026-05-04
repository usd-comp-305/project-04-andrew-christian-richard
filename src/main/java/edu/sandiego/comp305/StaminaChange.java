package edu.sandiego.comp305;

public enum StaminaChange {
    COSTLY_LOST(-3),
    LOSS(-1),
    GAIN(1),
    FREE(0);

    private final int change;

    StaminaChange(int change) {
        this.change = change;
    }

    public int getChange(){
        return change;
    }
}
