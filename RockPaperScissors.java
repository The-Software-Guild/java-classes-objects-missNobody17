import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int computerWins = 0;
        int userWins = 0;
        int nextRound = 0;

        do {
            System.out.println("How many rounds of Rock-Paper-Scissors would you like to play?");
            int rounds = Integer.parseInt(scanner.nextLine());
            if(rounds > 0 && rounds < 11) {
                for (int i = 0; i < rounds; i++) {
                    String value = playRockPaperScissors(scanner);
                    if(value.equals("user")){
                        userWins += 1;
                    } else if(value.equals("computer")) {
                        computerWins += 1;
                    }
                }
                int tie = rounds - computerWins - userWins;
                if(computerWins > userWins) {
                    System.out.println("The computer wins! Computer score is " + computerWins + ", user score is " + userWins + ", number of ties is " + tie);
                } else if(userWins > computerWins) {
                    System.out.println("The user wins! Computer score is " + computerWins + ", user score is " + userWins + ", number of ties is " + tie);
                } else {
                    System.out.println("There was a tie! Computer score is " + computerWins + ", user score is " + userWins + ", number of ties is " + tie);
                }
                System.out.println("Do you want to play again?");
                String nextGameAnswer = scanner.nextLine();
                if(nextGameAnswer.toLowerCase().equals("yes")){
                    nextRound = 1;
                    computerWins = 0;
                    userWins = 0;
                }else {
                    nextRound = 0;
                }
            } else {
                System.out.println("The number of rounds needs to be between 1 and 10!!");
            }

        }while(nextRound == 1);
    }

    static String playRockPaperScissors(Scanner scanner) {
        // Getting input from the user
        System.out.println("Make a move! (rock/paper/scissors)");
        String playerMove = scanner.nextLine();

        // Getting input from the computer
        Random random = new Random();
        int randomNumber = random.nextInt(3);

        String computerMove;
        if (randomNumber == 0) {
            computerMove = "rock";
        } else if (randomNumber == 1) {
            computerMove = "paper";
        } else {
            computerMove = "scissors";
        }
        System.out.println("Computer chose " + computerMove + "!");

        // Print results
        if (playerMove.equals(computerMove)) {
            System.out.println("It's a draw!");
        } else if (playerWins(playerMove, computerMove)) {
            System.out.println("Player wins!");
            return "user";
        } else {
            System.out.println("Computer wins!");
            return "computer";
        }

        return "";
    }

    static boolean playerWins(String playerMove, String computerMove) {
        if (playerMove.equals("rock")) {
            return computerMove.equals("scissors");
        } else if (playerMove.equals("paper")) {
            return computerMove.equals("rock");
        } else {
            return computerMove.equals("paper");
        }
    }
}