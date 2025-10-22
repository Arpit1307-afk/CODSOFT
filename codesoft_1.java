import java.util.*;

public class NumberGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random r = new Random();

        // 🎨 ANSI color codes for terminal
        String RESET = "\u001B[0m";
        String RED = "\u001B[31m";
        String GREEN = "\u001B[32m";
        String YELLOW = "\u001B[33m";
        String BLUE = "\u001B[34m";
        String CYAN = "\u001B[36m";
        String PURPLE = "\u001B[35m";

        int playAgain = 1;
        int totalRounds = 0, roundsWon = 0;

        System.out.println(BLUE + "🎮 WELCOME TO THE NUMBER GUESSING GAME 🎯" + RESET);
        System.out.println(YELLOW + "---------------------------------------------------" + RESET);

        while (playAgain == 1) {
            totalRounds++;
            int random = r.nextInt(100) + 1;  // Range: 1 to 100
            int attempts = 0;
            int maxAttempts = 10;
            boolean guessedCorrect = false;

            System.out.println(PURPLE + "\n⭐ ROUND " + totalRounds + " ⭐" + RESET);
            if (totalRounds > 1)
                System.out.println(CYAN + "💪 WELCOME BACK, CHAMP!" + RESET);
            System.out.println(YELLOW + "Guess the number between 1 and 100. You have " + maxAttempts + " attempts! 🔢" + RESET);

            while (attempts < maxAttempts) {
                System.out.print(CYAN + "\n⏩ Attempt " + (attempts + 1) + ": Enter your guess → " + RESET);
                int guess = sc.nextInt();
                attempts++;

                if (guess == random) {
                    System.out.println(GREEN + "🎉 CONGRATULATIONS! You guessed the number in " + attempts + " attempt(s)! 🏆" + RESET);
                    guessedCorrect = true;
                    roundsWon++;
                    break;
                } 
                else if (guess < random - 5) { 
                    System.out.println(RED + "⬇️ Too Low! Try a higher number." + RESET);
                } 
                else if (guess > random + 5) { 
                    System.out.println(RED + "⬆️ Too High! Try a lower number." + RESET);
                } 
                else {
                    System.out.println(YELLOW + "✨ You’re very close! Try again!" + RESET);
                }

                // show remaining attempts
                int remaining = maxAttempts - attempts;
                System.out.println(BLUE + "💥 Remaining Attempts: " + remaining + RESET);
            }

            if (!guessedCorrect) {
                System.out.println(RED + "❌ You've used all attempts. The correct number was: " + random + RESET);
            }

            double accuracy = guessedCorrect ? ((double)(maxAttempts - attempts + 1) / maxAttempts) * 100 : 0;
            System.out.printf(CYAN + "🎯 Your accuracy this round: %.2f%%" + RESET + "\n", accuracy);

            System.out.print(PURPLE + "\n🔁 Do you want to play again? (1 = Yes / 0 = No): " + RESET);
            playAgain = sc.nextInt();
        }

        double totalAccuracy = ((double) roundsWon / totalRounds) * 100;
        System.out.println(BLUE + "\n---------------------------------------------------" + RESET);
        System.out.printf(GREEN + "🏁 GAME OVER!%nRounds Played: %d%nRounds Won: %d%nOverall Accuracy: %.2f%%%n" + RESET, 
                          totalRounds, roundsWon, totalAccuracy);
        System.out.println(YELLOW + "\n💖 THANK YOU FOR PLAYING! 💖" + RESET);
        System.out.println(BLUE + "---------------------------------------------------" + RESET);

        sc.close();
    }
}
