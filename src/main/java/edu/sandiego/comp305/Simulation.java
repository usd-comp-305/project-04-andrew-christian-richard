package edu.sandiego.comp305;

import java.util.List;
import java.util.Scanner;

public class Simulation {
    private final Scanner scanner;
    private final RaceManager raceManager;
    private final HorseUpgradeSystem upgradeSystem;
    private final HorseFactory horseFactory;
    private Horse playerHorse;
    private final static int NUM_CHOICES = 3;

    public Simulation(Scanner scanner, RaceManager raceManager, HorseUpgradeSystem upgradeSystem,
                     HorseFactory horseFactory) {
        this.scanner       = scanner;
        this.raceManager   = raceManager;
        this.upgradeSystem = upgradeSystem;
        this.horseFactory  = horseFactory;
    }

    private void printEvent(Race race){
        Event event = race.getEvent();
        Horse player = race.getPlayerHorse();
        List<EventChoice> choices = event.getEventChoices();

        System.out.println("══════════════════════════════════════════════════════");
        System.out.printf("%-52s \n", event.getDescription());
        System.out.println("══════════════════════════════════════════════════════");

        for (int i = 0; i < NUM_CHOICES; i++){
            EventChoice choice = choices.get(i);
            RaceEffect effect = choice.getEffect();
            String statChanges = String.format("SPD%+d PWR%+d", effect.getSpeedChange(),  effect.getPowerChange());
            System.out.printf("[%d] %-30s %s \n", i + 1, choice.getLabel(), statChanges);
            System.out.printf("%-48s \n", choice.getLabel());
        }
        System.out.println("══════════════════════════════════════════════════════");
        System.out.printf( "Your stamina: %-38d \n", player.getStats().getStamina());
        System.out.println("══════════════════════════════════════════════════════");
        System.out.print("Choose (1-3): ");
    }


}
