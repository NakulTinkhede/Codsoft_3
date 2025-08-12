import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int score = 0;
        int round = 1;

        System.out.println(" Welcome to the Number Guessing Game!");

        boolean playAgain = true;
        while (playAgain) {
            int randomNumber = random.nextInt(100) + 1; // 1 to 100
            int attempts = 0;
            int maxAttempts = 7;

            System.out.println("\n Round " + round + " - Guess a number between 1 and 100");

            boolean guessedCorrectly = false;

            while (attempts < maxAttempts) {
                System.out.print(" Enter your guess: ");
                int guess = scanner.nextInt();
                attempts++;

                if (guess == randomNumber) {
                    System.out.println(" Correct! You guessed the number in " + attempts + " attempt(s).");
                    guessedCorrectly = true;
                    score += (10 - attempts); // Better score for fewer attempts
                    break;
                } else if (guess < randomNumber) {
                    System.out.println(" Too Low!");
                } else {
                    System.out.println(" Too High!");
                }
            }

            if (!guessedCorrectly) {
                System.out.println(" You’ve used all attempts. The correct number was: " + randomNumber);
            }

            System.out.print(" Do you want to play another round? (yes/no): ");
            String response = scanner.next().toLowerCase();
            if (!response.equals("yes")) {
                playAgain = false;
            }
            round++;
        }

        System.out.println("\n Game Over! Your total score is: " + score);
        System.out.println(" Thanks for playing!");

        scanner.close();
    }
}
