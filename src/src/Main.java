import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int score = 0;
        String name = nameQuestion(sc);
        jump();
        int quantity = quantityQuestion(sc);
        jump();
        String dificulty = difficultyQuestion(sc);
        jump();
        String quizz = quizzQuestion(sc, quantity, dificulty, name, score);
    }
    public static loadQuestions(String file, String questionArray){


    }
    private static String quizzQuestion(Scanner sc, int quantity, String dificulty, String name, int score) {
        switch(dificulty){
            case "easy":
                File file = new File("questions&Answers(easy)");


            case "medium":


            case "hard":


        }


    }

    public static void jump() {
        for (int i = 0; i < 50; i++){
            System.out.println(" ");
        }
    }

    public static String difficultyQuestion(Scanner sc) {
        String difficulty = "";
        boolean loop = false;

        while (!loop) {
            System.out.println("Choose the difficulty: Easy, Medium, or Hard");
            difficulty = sc.nextLine().trim().toLowerCase();

            if (difficulty.isEmpty()) {
                System.out.println("Difficulty cannot be empty. Please provide a valid input.");
            } else {
                switch (difficulty) {
                    case "easy":
                        System.out.println("You chose Easy difficulty.");
                        loop = true;
                        break;
                    case "medium":
                        System.out.println("You chose Medium difficulty.");
                        loop = true;
                        break;
                    case "hard":
                        System.out.println("You chose Hard difficulty.");
                        loop = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please choose Easy, Medium, or Hard.");
                        break;
                }
            }
        }
        return difficulty;
    }

    private static int quantityQuestion (Scanner sc){
            System.out.println("How may questions would you like to answer?");
            System.out.println("5 minimum");
            int quantity = sc.nextInt();
            while (quantity < 5) {
                System.out.println("Please enter a number 5 or greater.");
                quantity = sc.nextInt();
            }
            return quantity;
        }

        private static String nameQuestion (Scanner sc){
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
