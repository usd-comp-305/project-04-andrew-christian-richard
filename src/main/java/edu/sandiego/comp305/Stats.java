package edu.sandiego.comp305;

import java.util.*;

public class Stats {
    private final Random random = new Random();

    private int speed;

    private int stamina;

    private int power;

    public Stats(
            final int speed,
            final int stamina,
            final int power) {
        this.speed = speed;
        this.stamina = stamina;
        this.power = power;
    }

    public Stats(final Stats stats) {
        this(
                stats.speed,
                stats.stamina,
                stats.power);
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
        return generateMovement(RaceEffect.NO_EFFECT);
    }

    public int generateMovement(final RaceEffect effect) {
        final int minMovementDistance =
                power + effect.getPowerChange();

        final int maxMovementDistance =
                speed + effect.getSpeedChange();

        if (stamina == 0
                || minMovementDistance <= 0
                || maxMovementDistance <= 0) {
            return 0;
        }

        final int movementRange = maxMovementDistance - minMovementDistance + 1;

        return minMovementDistance + random.nextInt(movementRange);
    }

    public void consumeStamina(final int amount) {
        if (stamina < amount) {
            stamina = 0;
        } else {
            stamina -= amount;
        }
    }

    public void increaseSpeed(final int amount) {
        speed += amount;
    }

    public void increaseStamina(final int amount) {
        stamina += amount;
    }

    public void increasePower(final int amount) {
        power += amount;
    }
}
