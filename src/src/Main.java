import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String name = nameQuestion(sc);
        int quantity = quantityQuestion(sc);
        String dificulty = difficultyQuestion(sc);
    }

    public static String difficultyQuestion(Scanner sc) {
        System.out.println("Choose the difficulty: Easy, Medium, or Hard");
        sc.nextLine();
        String difficulty = sc.nextLine();
        Boolean loop = false;
        while (!loop) {
            if (difficulty == null || difficulty.isBlank()) {
                System.out.println("Difficulty cannot be empty. Please provide a valid input.");
            } else {
                switch (difficulty.toLowerCase()) {
                    case "easy":
                        System.out.println("You chose Easy difficulty.");
                        loop = true;
                        break;
                    case "medium":
                        System.out.println("You chose Medium difficulty.");
                        break;
                        loop = true;
                    case "hard":
                        System.out.println("You chose Hard difficulty.");
                        break;
                        loop = true;
                    default:
                        System.out.println("Invalid choice. Please choose Easy, Medium, or Hard.");
                        loop = false;
                        break;
                }
            }
        }
        return difficulty;
    }

    private static int quantityQuestion(Scanner sc) {
        System.out.println("How may questions would you like to answer?");
        System.out.println("5 minimum");
        int quantity = sc.nextInt();
        while (quantity < 5) {
            System.out.println("Please enter a number 5 or greater.");
            quantity = sc.nextInt();
        }
        return quantity;
    }

    private static String nameQuestion(Scanner sc) {
        System.out.println("Welcome to my quizz");
        System.out.println("I'm glad to see you here ");
        System.out.println("What is your name?");
        String name = sc.nextLine();
        System.out.println("Nice to meet you, " + name + "! Let's start our quiz.");
        System.out.println("press any button to continue...");
        sc.nextLine();
        return name;
    }


}