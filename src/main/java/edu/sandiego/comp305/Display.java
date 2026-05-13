package edu.sandiego.comp305;

import java.util.List;
public class Display {
    private static final int NUM_CHOICES = 3;

    public Display(){
    }

    public void printEvent(Race race){
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

    public void printRound(Race race){
        int trackDistance = race.getLengthInMeters();
        Horse player = race.getPlayerHorse();
        Stats playerStats = player.getStats();
        List<RaceParticipant> standings = race.getCurrentStandings();

        System.out.println("══════════════════════════════════════════════════════");
        System.out.printf( "%s - %s Round:%s %n", race.getDifficulty(), trackDistance, race.getRound());
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

    public void printUpgradeSystem(final int upgradePoints){
        System.out.println("══════════════════════════════════════════════════════");
        System.out.printf("It is time to upgrade your horse before the next race! You have %d%n", upgradePoints);
        System.out.println("══════════════════════════════════════════════════════");
        System.out.println("Enter upgrades as three numbers:");
        System.out.println("<Speed> <Power> <Stamina>");
        System.out.println("══════════════════════════════════════════════════════");
        System.out.print("Enter upgrades: ");
    }

    public void printRaceResult(Race race, int trophiesEarned, int totalTrophies){
        System.out.println("══════════════════════════════════════════════════════");
        System.out.println("The race has ended! Here are the results are in!");
        System.out.println("══════════════════════════════════════════════════════");
        List<RaceParticipant> finishOrder = race.getFinishOrder();
        for (int i = 0; i < finishOrder.size(); i++) {
            RaceParticipant participant = finishOrder.get(i);
            System.out.printf("%d. %s%n", i + 1, participant.getName());
        }
        System.out.println("══════════════════════════════════════════════════════");
        System.out.printf("You earned %d%n", trophiesEarned);
        System.out.printf("Total trophies: %d%n", totalTrophies);
        System.out.println("══════════════════════════════════════════════════════");
    }

    public void printWelcome() {
        System.out.println("══════════════════════════════════════════════════════");
        System.out.println("Welcome to the Horse Racing! Let's start your career!");
        System.out.println("══════════════════════════════════════════════════════");
    }

    public void printHorseCreation(){
        System.out.println("Name your horse: ");
    }

    public void printCompletion(Horse horse, int totalTrophies){
        Stats stats = horse.getStats();
        System.out.println("══════════════════════════════════════════════════════");
        System.out.println("Good job! Your career is complete. Here are your stats");
        System.out.println("══════════════════════════════════════════════════════");
        System.out.printf("HORSE: %s%n", horse.getName());
        System.out.printf("SPD:%-3d  PWR:%-3d  STM:%-3d%n",
                stats.getSpeed(),
                stats.getPower(),
                stats.getStamina());
        System.out.printf("Total trophies awarded: %d%n", totalTrophies);
        System.out.println("══════════════════════════════════════════════════════");
    }
}
