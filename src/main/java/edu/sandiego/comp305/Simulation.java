package edu.sandiego.comp305;

import java.util.List;
import java.util.Scanner;

public class Simulation {
    private final Scanner scanner;
    private final RaceManager raceManager;
    private final HorseUpgradeSystem upgradeSystem;
    private final HorseFactory horseFactory;
    private Horse playerHorse;
    private int round;

    private static final int NUM_CHOICES = 3;
    private static final int FIRST_CHOICE = 1;
    private static final int SPEED_UPGRADE_CHOICE = 1;
    private static final int POWER_UPGRADE_CHOICE = 2;
    private static final int STAMINA_UPGRADE_CHOICE = 3;

    public Simulation(Scanner scanner, RaceManager raceManager, HorseUpgradeSystem upgradeSystem,
                     HorseFactory horseFactory) {
        this.scanner = scanner;
        this.raceManager = raceManager;
        this.upgradeSystem = upgradeSystem;
        this.horseFactory = horseFactory;
        this.round = 0;
    }

    private void printEvent(Race race){
        Event event = race.getEvent();
        Horse player = race.getPlayerHorse();
        List<EventChoice> choices = event.getEventChoices();

        System.out.println("══════════════════════════════════════════════════════");
        System.out.printf("%-52s %n", event.getDescription());
        System.out.println("══════════════════════════════════════════════════════");

        for (int i = 0; i < NUM_CHOICES; i++){
            EventChoice choice = choices.get(i);
            RaceEffect effect = choice.getEffect();
            String statChanges = String.format("SPD%+d PWR%+d", effect.getSpeedChange(),  effect.getPowerChange());
            System.out.printf("[%d] %-30s %s %n", i + 1, choice.getLabel(), statChanges);
            System.out.printf("%-48s %n", choice.getLabel());
        }
        System.out.println("══════════════════════════════════════════════════════");
        System.out.printf( "Your stamina: %-38d %n", player.getStats().getStamina());
        System.out.println("══════════════════════════════════════════════════════");
        System.out.print("Choose (1-3): ");
    }

    private void printRound(Race race){
        int trackDistance = race.getTrack().getLengthInMeters();
        Horse player = race.getPlayerHorse();
        Stats playerStats = player.getStats();
        List<RaceParticipant> standings = race.getCurrentStandings();

        System.out.println("══════════════════════════════════════════════════════");
        System.out.printf( "%s - %s Round:%s %n", race.getDifficulty(), trackDistance, round);
        System.out.println("══════════════════════════════════════════════════════");
        System.out.printf( "%-4s %-12s  %-8s %n",
                "POS", "HORSE", "DIST TO FINISH");
        System.out.println("══════════════════════════════════════════════════════");
        for (int i = 0; i < standings.size(); i++) {
            RaceParticipant horse = standings.get(i);
            boolean isPlayer = horse == player;
            boolean finished  = race.getFinishOrder().contains(horse);

            int dist = horse.getCurrentDistance();
            int distanceToFinish = Math.max(0, trackDistance - dist);
            String name = horse.getName() + (isPlayer ? "◄" : " ");
            String pos    = finished ? "FIN" : (i + 1) + ".";

            System.out.printf("%-4s %-12s %4dm left %n", pos, name, distanceToFinish);
        }
        System.out.println("══════════════════════════════════════════════════════");
        System.out.printf( "YOUR HORSE  SPD:%-3d  PWR:%-3d  STM:%-3d%-16s %n",
                player.getStats().getSpeed(), player.getStats().getPower(), player.getStats().getStamina(), "");
        System.out.println("══════════════════════════════════════════════════════");
    }

    private void printUpgradeSystem(){
        System.out.println("══════════════════════════════════════════════════════");
        System.out.println("It is time to upgrade your horse before the next race!");
        System.out.println("══════════════════════════════════════════════════════");
        System.out.println("Enter upgrades as three numbers:");
        System.out.println("<Speed> <Power> <Stamina>");
        System.out.println("══════════════════════════════════════════════════════");
        System.out.print("Enter upgrades: ");
    }

}
