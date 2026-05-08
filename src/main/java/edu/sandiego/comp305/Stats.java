package edu.sandiego.comp305;

public class Stats {
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
        return 0;
    }

    public void consumeStamina(int amount) {

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
