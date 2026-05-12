package edu.sandiego.comp305;

import java.util.List;
import java.util.Scanner;

public class Simulation {
    private final Scanner scanner;
    private final RaceManager raceManager;
    private final HorseUpgradeSystem upgradeSystem;
    private final HorseFactory horseFactory;
    private Horse playerHorse;

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

    }
}
