import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String fileNameEasy = "src/Resources/questions&Answers(easy)";
        String fileNameMedium = "src/Resources/questions&Answers(medium)";
        String fileNameHard = "src/Resources/questions&Answers(hard)";

        int score = 0;
        String name = nameQuestion(sc);
        jump();
        int quantity = quantityQuestion(sc);
        jump();
        String dificulty = difficultyQuestion(sc);
        jump();
        int[] RandomNumbers = new int[quantity];

        RandomNumber(quantity, RandomNumbers);
        switch (dificulty){
            case "easy":
                String[] questionsE = questionEasy(fileNameEasy);
                break;
            case "medium":
                String[] questionsM = questionMedium(fileNameMedium);
                break;
            case "hard":
                String[] questionsH = questionHard(fileNameHard);
                break;
        }

    }

    private static String[] questionEasy( String filenameEasy) {
        String[] questions = new String[20];
            try {
                BufferedReader br = new BufferedReader(new FileReader(filenameEasy));
                String line;
                int index = 0;
                while ((line = br.readLine()) != null && index < 20) {
                    String[] question = line.split(";");
                    if (question.length == 4) {
                        questions[index] = "Pregunta: " + question[0] + "\nRespuestas: [" + question[1] + ", " + question[2] + ", " + question[3] + "]";
                        index++;
                    } else {
                        System.out.println("Error: línea no válida en el archivo: " + line);
                    }
                }
                br.close();
            } catch (IOException e) {
                System.out.println("File not found: " + filenameEasy);
            }
            return questions;
    }
    private static String[] questionMedium(String filenameMedium) {
        String[] questions = new String[20];
        try {
            BufferedReader br = new BufferedReader(new FileReader(filenameMedium));
            String line;
            int index = 0;
            while ((line = br.readLine()) != null && index < 20) {
                String[] question = line.split(";");
                if (question.length == 4) {
                    questions[index] = "Pregunta: " + question[0] + "\nRespuestas: [" + question[1] + ", " + question[2] + ", " + question[3] + "]";
                    index++;
                } else {
                    System.out.println("Error: línea no válida en el archivo: " + line);
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println("File not found: " + filenameMedium);
        }
        return questions;
    }
    private static String[] questionHard(String filenameHard) {
        String[] questions = new String[20];
        try {
            BufferedReader br = new BufferedReader(new FileReader(filenameHard));
            String line;
            int index = 0;
            while ((line = br.readLine()) != null && index < 20) {
                String[] question = line.split(";");
                if (question.length == 4) {
                    questions[index] = "Pregunta: " + question[0] + "\nRespuestas: [" + question[1] + ", " + question[2] + ", " + question[3] + "]";
                    index++;
                } else {
                    System.out.println("Error: línea no válida en el archivo: " + line);
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println("File not found: " + filenameHard);
        }
        return questions;
    }


    public static void RandomNumber(int quantity, int[] RandomNumbers){
        Random random = new Random();
        for (int i = 0; i < quantity; i++) {
            RandomNumbers[i] = random.nextInt(20);
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
