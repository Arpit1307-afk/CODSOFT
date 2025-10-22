import java.util.*;

public class EduSmartGradeCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 🌈 Colors using ANSI Escape Codes
        String RESET = "\u001B[0m";
        String CYAN = "\u001B[36m";
        String GREEN = "\u001B[32m";
        String YELLOW = "\u001B[33m";
        String RED = "\u001B[31m";
        String BLUE = "\u001B[34m";
        String PURPLE = "\u001B[35m";

        // 💫 Welcome Message
        System.out.println(BLUE + "---------------------------------------------" + RESET);
        System.out.println(PURPLE + "🎓  WELCOME TO EduSmart GRADE CALCULATOR  🎓" + RESET);
        System.out.println(BLUE + "---------------------------------------------" + RESET);

        System.out.print(CYAN + "\nPlease enter your name: " + RESET);
        String name = sc.nextLine();

        System.out.print(CYAN + "Hey " + name + "! 👋 Enter the number of subjects: " + RESET);
        int totalSubject = sc.nextInt();
        sc.nextLine(); // consume newline

        int[] marks = new int[totalSubject];
        String[] subjects = new String[totalSubject];
        int sum = 0;

        System.out.println(YELLOW + "\nLet's start entering your subject details 📘" + RESET);

        for (int i = 0; i < totalSubject; i++) {
            // Subject numbering suffix
            String suffix;
            if (i == 0) suffix = "st";
            else if (i == 1) suffix = "nd";
            else if (i == 2) suffix = "rd";
            else suffix = "th";

            System.out.print("\nEnter the name of your " + (i + 1) + suffix + " subject: ");
            subjects[i] = sc.nextLine();

            int mark;
            while (true) {
                System.out.print("Enter marks for " + subjects[i] + " (out of 100): ");
                mark = sc.nextInt();
                if (mark >= 0 && mark <= 100) {
                    break;
                } else {
                    System.out.println(RED + "⚠️ Invalid marks! Please enter between 0 and 100." + RESET);
                }
            }
            sc.nextLine(); // consume newline
            marks[i] = mark;
            sum += mark;
        }

        double averagePercentage = (double) sum / totalSubject;
        char grade;
        String gradeColor;

        if (averagePercentage >= 90) {
            grade = 'A';
            gradeColor = GREEN;
        } else if (averagePercentage >= 80) {
            grade = 'B';
            gradeColor = CYAN;
        } else if (averagePercentage >= 70) {
            grade = 'C';
            gradeColor = YELLOW;
        } else if (averagePercentage >= 60) {
            grade = 'D';
            gradeColor = RED;
        } else {
            grade = 'F';
            gradeColor = RED;
        }

        // 🧾 Displaying the final report
        System.out.println(BLUE + "\n---------------------------------------------" + RESET);
        System.out.println(PURPLE + "📊  RESULT SUMMARY FOR " + name.toUpperCase() + "  📊" + RESET);
        System.out.println(BLUE + "---------------------------------------------" + RESET);

        for (int i = 0; i < totalSubject; i++) {
            System.out.println(GREEN + "✅ " + subjects[i] + ": " + marks[i] + "/100" + RESET);
        }

        System.out.println(BLUE + "---------------------------------------------" + RESET);
        System.out.println(GREEN + "✨ Total Marks Obtained: " + sum + "/" + (totalSubject * 100) + RESET);
        System.out.printf(CYAN + "📈 Average Percentage: %.2f%%\n" + RESET, averagePercentage);
        System.out.println(gradeColor + "🏅 Grade Achieved: " + grade + RESET);
        System.out.println(BLUE + "---------------------------------------------" + RESET);

        System.out.println(PURPLE + "🎉 Great work, " + name + "! Keep learning and improving! 🚀" + RESET);
        System.out.println(BLUE + "---------------------------------------------" + RESET);

        sc.close();
    }
}
