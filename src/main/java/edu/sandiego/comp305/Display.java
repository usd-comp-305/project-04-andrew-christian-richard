package edu.sandiego.comp305;

import java.util.List;
public class Display {
    private static final int NUM_CHOICES = 3;

    public void printEvent(final Race race){
        final Event event = race.getEvent();
        final Horse player = race.getPlayerHorse();
        final List<EventChoice> choices = event.getEventChoices();

        System.out.println(
                "══════════════════════════════════════════════════════");
        System.out.printf("%-52s %n", event.getDescription());
        System.out.println(
                "══════════════════════════════════════════════════════");

        for (int i = 0; i < NUM_CHOICES; i++){
            final EventChoice choice = choices.get(i);
            final RaceEffect effect = choice.getEffect();
            final String statChanges = String.format(
                    "SPD%+d PWR%+d",
                    effect.getSpeedChange(),
                    effect.getPowerChange());
            System.out.printf(
                    "[%d] %-30s %s %n",
                    i + 1,
                    choice.getLabel(),
                    statChanges);
            System.out.printf("%-48s %n", choice.getLabel());
        }

        System.out.println(
                "══════════════════════════════════════════════════════");
        System.out.printf(
                "Your stamina: %-38d %n",
                player.getStats().getStamina());
        System.out.println(
                "══════════════════════════════════════════════════════");
        System.out.print("Choose (1-3): ");
    }

    public void printRound(Race race){
        final Horse player = race.getPlayerHorse();
        final Stats playerStats = player.getStats();
        final List<RaceParticipant> standings = race.getCurrentStandings();
        final int trackDistance = race.getLengthInMeters();

        System.out.println(
                "══════════════════════════════════════════════════════");
        System.out.printf(
                "%s - %s Round:%s %n",
                race.getDifficulty(),
                trackDistance,
                race.getRound());
        System.out.println(
                "══════════════════════════════════════════════════════");
        System.out.printf(
                "%-4s %-12s  %-8s %n",
                "POS",
                "HORSE",
                "DIST TO FINISH");
        System.out.println(
                "══════════════════════════════════════════════════════");
        for (int i = 0; i < standings.size(); i++) {
            final RaceParticipant horse = standings.get(i);
            final boolean isPlayer = horse == player;
            final boolean finished  = race.getFinishOrder().contains(horse);

            final int dist = horse.getCurrentDistance();
            final int distanceToFinish = Math.max(0, trackDistance - dist);
            final String name = horse.getName() + (isPlayer ? "◄" : " ");
            final String pos    = finished ? "FIN" : (i + 1) + ".";

            System.out.printf(
                    "%-4s %-12s %4dm left %n",
                    pos,
                    name,
                    distanceToFinish);
        }
        System.out.println(
                "══════════════════════════════════════════════════════");
        System.out.printf(
                "YOUR HORSE  SPD:%-3d  PWR:%-3d  STM:%-3d%-16s %n",
                player.getStats().getSpeed(),
                player.getStats().getPower(),
                player.getStats().getStamina(),
                "");
        System.out.println(
                "══════════════════════════════════════════════════════");
    }

    public void printUpgradeSystem(final int upgradePoints){
        System.out.println(
                "══════════════════════════════════════════════════════");
        System.out.printf("It is time to upgrade your horse before the next race! You have %d%n", upgradePoints);
        System.out.println(
                "══════════════════════════════════════════════════════");
        System.out.println("Enter upgrades as three numbers:");
        System.out.println("<Speed> <Power> <Stamina>");
        System.out.println(
                "══════════════════════════════════════════════════════");
        System.out.print("Enter upgrades: ");
    }

    public void printRaceResult(
            final Race race,
            final int trophiesEarned,
            final int totalTrophies) {
        System.out.println(
                "══════════════════════════════════════════════════════");
        System.out.println("The race has ended! Here are the results are in!");
        System.out.println(
                "══════════════════════════════════════════════════════");
        final List<RaceParticipant> finishOrder = race.getFinishOrder();
        for (int i = 0; i < finishOrder.size(); i++) {
            final RaceParticipant participant = finishOrder.get(i);
            System.out.printf("%d. %s%n", i + 1, participant.getName());
        }
        System.out.println(
                "══════════════════════════════════════════════════════");
        System.out.printf("You earned %d%n", trophiesEarned);
        System.out.printf("Total trophies: %d%n", totalTrophies);
        System.out.println(
                "══════════════════════════════════════════════════════");
    }

    public void printWelcome() {
        System.out.println(
                "══════════════════════════════════════════════════════");
        System.out.println(
                "Welcome to the Horse Racing! Let's start your career!");
        System.out.println(
                "══════════════════════════════════════════════════════");
    }

    public void printHorseCreation() {
        System.out.println("Name your horse: ");
    }

    public void printCompletion(Horse horse, int totalTrophies){
        final Stats stats = horse.getStats();
        System.out.println(
                "══════════════════════════════════════════════════════");
        System.out.println(
                "Good job! Your career is complete. Here are your stats");
        System.out.println(
                "══════════════════════════════════════════════════════");
        System.out.printf("HORSE: %s%n", horse.getName());
        System.out.printf(
                "SPD:%-3d  PWR:%-3d  STM:%-3d%n",
                stats.getSpeed(),
                stats.getPower(),
                stats.getStamina());
        System.out.printf("Total trophies awarded: %d%n", totalTrophies);
        System.out.println(
                "══════════════════════════════════════════════════════");
    }
}
