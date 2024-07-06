import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();

    public static void main(String[] args) {
        System.out.println("Welcome to the Number Guessing Game!");

        boolean playAgain;
        do {
            int difficulty = selectDifficulty();
            int maxNumber = getMaxNumberForDifficulty(difficulty);
            int numberToGuess = random.nextInt(maxNumber) + 1;

            System.out.println("I have selected a number between 1 and " + maxNumber + ".");
            System.out.println("Can you guess what it is?");

            int numberOfTries = playGame(numberToGuess, maxNumber);
            int score = calculateScore(difficulty, numberOfTries);

            System.out.println("Congratulations! You've guessed the number!");
            System.out.println("It took you " + numberOfTries + " tries.");
            System.out.println("Your score is: " + score);

            playAgain = askToPlayAgain();
        } while (playAgain);

        System.out.println("Thank you for playing the Number Guessing Game! Goodbye!");
        scanner.close();
    }

    private static int selectDifficulty() {
        System.out.println("Select difficulty level:");
        System.out.println("1. Easy (1-10)");
        System.out.println("2. Medium (1-100)");
        System.out.println("3. Hard (1-1000)");
        int difficulty;
        while (true) {
            System.out.print("Enter your choice (1/2/3): ");
            difficulty = scanner.nextInt();
            if (difficulty >= 1 && difficulty <= 3) {
                break;
            } else {
                System.out.println("Invalid choice. Please select 1, 2, or 3.");
            }
        }
        return difficulty;
    }

    private static int getMaxNumberForDifficulty(int difficulty) {
        switch (difficulty) {
            case 1:
                return 10;
            case 2:
                return 100;
            case 3:
                return 1000;
            default:
                return 100;  // Default to medium difficulty
        }
    }

    private static int playGame(int numberToGuess, int maxNumber) {
        int numberOfTries = 0;
        boolean hasGuessedCorrectly = false;
        int guess;

        while (!hasGuessedCorrectly) {
            System.out.print("Enter your guess: ");
            guess = scanner.nextInt();
            numberOfTries++;

            if (guess < 1 || guess > maxNumber) {
                System.out.println("Please guess a number between 1 and " + maxNumber + ".");
            } else if (guess < numberToGuess) {
                System.out.println("Your guess is too low.");
            } else if (guess > numberToGuess) {
                System.out.println("Your guess is too high.");
            } else {
                hasGuessedCorrectly = true;
            }
        }

        return numberOfTries;
    }

    private static int calculateScore(int difficulty, int numberOfTries) {
        int baseScore;
        switch (difficulty) {
            case 1:
                baseScore = 100;
                break;
            case 2:
                baseScore = 1000;
                break;
            case 3:
                baseScore = 10000;
                break;
            default:
                baseScore = 1000;  // Default to medium difficulty score
        }
        return baseScore / numberOfTries;
    }

    private static boolean askToPlayAgain() {
        System.out.print("Do you want to play again? (yes/no): ");
        String response = scanner.next();
        return response.equalsIgnoreCase("yes");
    }
}