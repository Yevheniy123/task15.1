package service;

import dto.DependencyTable;
import dto.Player;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Controller extends DependencyTable {
    private static int countGames;
    private Player player;
    private Computer computer;

    public void runGame() {
        computer = new Computer();
        boolean nextGame = true;
        Scanner scanner = new Scanner(System.in);

        System.out.print("Hello! What's your name? \n-> ");
        String name = scanner.nextLine();

        player = new Player(name);

        System.out.println("Nice to meet you, " + player.getName() + " :)");
        System.out.print("How many games do you want to play? \n-> ");
        int plays = Integer.parseInt(scanner.nextLine());

        wrapper();
        do {
            System.out.print("""
                        Tools:\s
                         [R] - Rock\s
                         [P] - Paper\s
                         [S] - Scissors\s
                        """);
            wrapper();
            System.out.print("Input: ");

            String toolChoice = scanner.nextLine();
            int computerChoice = computer.getComputerDecision();

            System.out.println("Result: ");

            try {
                String resultChoice = defineWin(defineTool(toolChoice), computerChoice);
                System.out.println(resultChoice);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.err.println("This tool is not existing!");
            }
            wrapper();
            ++countGames;
            if (countGames == plays) nextGame = false;

            System.out.print("""
                        Do you want to play again? [N] - to exit.\s
                        Press any [button] to continue.\s
                        Input:\040""");

            String buffer = scanner.nextLine();
            if (buffer.equalsIgnoreCase("N")) nextGame = false;
        } while (nextGame);
        printStats();
    }

    private void printStats() {
        double winRate;
        int wins = player.getWin();
        int loses = player.getLose();
        int ties = countGames - wins - loses;

        wrapper();
        System.out.println("Wins: " + wins);
        System.out.println("Loses: " + loses);
        System.out.println("Ties: " + ties);

        try {
            winRate = ((double) wins / (wins + loses)) * 100;
            System.out.println("Win rate: " + String.format("%.2f", winRate) + "%");
        } catch (ArithmeticException e) {
            System.err.println("Statistics cannot be displayed.");
        } finally {
            System.out.println("Number of games: " + countGames);
            wrapper();
        }
    }

    private void wrapper() {
        for (int i = 0; i < 70; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    private int defineTool(String tool) {
        switch (tool) {
            case "R" -> {
                return 0;
            }
            case "S" -> {
                return 1;
            }
            case "P" -> {
                return 2;
            }
        }
        return -1;
    }

    private String defineWin(int playerChoice, int computerChoice) {
        if (playerChoice < 0 && computerChoice < 0) throw new NoSuchElementException();

        if (playerChoice == 0 && computerChoice == 1 ||
                playerChoice == 2 && computerChoice == 0 || playerChoice == 1 && computerChoice == 2) {
            player.setWin(player.getWin() + 1);
            return player.getName() + " is a winner! Combination: " +
                        dependencyTable[playerChoice][computerChoice];
        }
        else if (playerChoice == 2 && computerChoice == 1 ||
                playerChoice == 0 && computerChoice == 2 || playerChoice == 1 && computerChoice == 0) {
            player.setLose(player.getLose() + 1);
            return "Computer is a winner! Combination: " +
                        dependencyTable[playerChoice][computerChoice];
        }
        else {
            return "Tie! Combination: " +
                        dependencyTable[playerChoice][computerChoice];
        }
    }
}
