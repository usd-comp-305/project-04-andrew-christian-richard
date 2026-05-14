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

    public int generateMovement(final RaceEffect effect) {
        final int minMovementDistance = getMinMovementDistance(effect);
        final int maxMovementDistance = getMaxMovementDistance(effect);

        if (stamina == 0) {
            return 0;
        }

        if (maxMovementDistance <= minMovementDistance) {
            return maxMovementDistance;
        }

        return random.nextInt(
                minMovementDistance,
                maxMovementDistance + 1
        );
    }

    public int getMinMovementDistance(final RaceEffect effect) {
        final int minMovementDistance = power + effect.getPowerChange();
        return Math.max(0, minMovementDistance);
    }

    public int getMaxMovementDistance(final RaceEffect effect) {
        final int maxMovementDistance = speed + effect.getSpeedChange();
        return Math.max(0, maxMovementDistance);
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
