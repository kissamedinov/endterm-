import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

interface FootballTeam {
    String getName();
}

class Player {
    private String name;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Match {
    private FootballTeam homeTeam;
    private FootballTeam awayTeam;

    public Match(FootballTeam homeTeam, FootballTeam awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public FootballTeam getHomeTeam() {
        return homeTeam;
    }

    public FootballTeam getAwayTeam() {
        return awayTeam;
    }
}

interface MatchStatisticsCalculator {
    void calculateAndDisplayStatistics(Match match);
}

class SimpleMatchStatisticsCalculator implements MatchStatisticsCalculator {
    public void calculateAndDisplayStatistics(Match match) {
        List<Player> homePlayers = ((Team) match.getHomeTeam()).getPlayers();
        List<Player> awayPlayers = ((Team) match.getAwayTeam()).getPlayers();

        int homeGoals = (int) (Math.random() * homePlayers.size()); 
        int awayGoals = (int) (Math.random() * awayPlayers.size());

        System.out.println("Match statistics:");
        System.out.println("Home Team: " + match.getHomeTeam().getName());
        System.out.println("Away Team: " + match.getAwayTeam().getName());
        System.out.println("Goals Scored: " + homeGoals + " - " + awayGoals);
        System.out.println("Scorers:");

        for (int i = 0; i < homeGoals; i++) {
            Player player = homePlayers.get((int) (Math.random() * homePlayers.size()));
            System.out.println(match.getHomeTeam().getName() + " - " + player.getName());
        }

        for (int i = 0; i < awayGoals; i++) {
            Player player = awayPlayers.get((int) (Math.random() * awayPlayers.size()));
            System.out.println(match.getAwayTeam().getName() + " - " + player.getName());
        }


        if (homeGoals > awayGoals) {
            System.out.println("Winner: " + match.getHomeTeam().getName());
        } else if (homeGoals < awayGoals) {
            System.out.println("Winner: " + match.getAwayTeam().getName());
        } else {
            System.out.println("It's a draw!");
        }
    }
}

class Team implements FootballTeam {
    private String name;
    private List<Player> players;

    public Team(String name) {
        this.name = name;
        players = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public List<Player> getPlayers() {
        return players;
    }
}

class FootballMatchPredictor {
    private Scanner scanner;
    private MatchStatisticsCalculator calculator;

    public FootballMatchPredictor() {
        scanner = new Scanner(System.in);
        calculator = new SimpleMatchStatisticsCalculator();
    }

    public void predictMatch() {
        System.out.print("Enter the name of Home Team: ");
        String homeTeamName = scanner.nextLine();

        System.out.print("Enter the name of Away Team: ");
        String awayTeamName = scanner.nextLine();

        Team homeTeam = createTeam(homeTeamName);
        Team awayTeam = createTeam(awayTeamName);

        Match match = new Match(homeTeam, awayTeam);

        calculator.calculateAndDisplayStatistics(match);
    }

    private Team createTeam(String teamName) {
        Team team = new Team(teamName);
        System.out.println("Enter player names for team " + teamName + ":");
        while (true) {
            System.out.print("Enter player name (leave empty to finish): ");
            String playerName = scanner.nextLine();
            if (playerName.isEmpty()) {
                break;
            }
            team.addPlayer(new Player(playerName));
        }
        return team;
    }

    public void closeScanner() {
        scanner.close();
    }
}

public class Main {
    public static void main(String[] args) {
        FootballMatchPredictor predictor = new FootballMatchPredictor();
        predictor.predictMatch();
        predictor.closeScanner();
    }
}
