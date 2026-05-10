package edu.sandiego.comp305;

import java.util.*;

public class Stats {
    private static final int STAMINA_DEPLETION_RATE_PER_ROUND = 1;
    private final Random random = new Random();

    private int speed;
    private int stamina;
    private int power;

    public Stats(int speed, int stamina, int power) {
        this.speed = speed;
        this.stamina = stamina;
        this.power = power;
    }

    public int getSpeed() {
        return speed;
    }

    public int getStamina() {
        return stamina;
    }

    public int getPower() {
        return power;
    }

    public int generateMovement() {
        final int minMovementDistance;

        if (stamina == 0 || speed == 0) {
            return 0;
        }

        minMovementDistance = Math.min(power, speed);

        final int movementRange = speed - minMovementDistance + 1;
        final int movementDistance = minMovementDistance + random.nextInt(movementRange);

        consumeStamina(STAMINA_DEPLETION_RATE_PER_ROUND);

        return movementDistance;
    }

    public void consumeStamina(int amount) {
        if (stamina < amount) {
            stamina = 0;
        }
        else {
            stamina -= amount;
        }
    }

    public void increaseSpeed(int amount) {
        speed += amount;
    }

    public void increaseStamina(int amount) {
        stamina += amount;
    }

    public void increasePower(int amount) {
        power += amount;
    }
}
