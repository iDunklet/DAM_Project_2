import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String fileNameEasy = "src/src/Resources/questions&Answers(easy).txt";
        String fileNameMedium = "src/src/Resources/questions&Answers(medium).txt";
        String fileNameHard = "src/src/Resources/questions&Answers(hard).txt";

        boolean playAgain;

        do {
            int score = 0;
            String name = nameQuestion(sc);
            jump();
            int quantity = quantityQuestion(sc);
            jump();
            String difficulty = difficultyQuestion(sc);
            jump();

            String[] questions;
            switch (difficulty) {
                case "easy":
                    questions = loadQuestions(fileNameEasy);
                    break;
                case "medium":
                    questions = loadQuestions(fileNameMedium);
                    break;
                case "hard":
                    questions = loadQuestions(fileNameHard);
                    break;
                default:
                    questions = new String[0];
            }

            int[] randomNumbers = generateRandomNumbers(quantity);
            int correctAnswers = 0;
            int incorrectAnswers = 0;

            for (int i = 0; i < quantity; i++) {
                String question = questions[randomNumbers[i]];
                String[] parts = question.split(";", -1);
                System.out.println("Question " + (i + 1) + ": " + parts[0]);


                String[] options = {parts[1], parts[2], parts[3]};
                String correctAnswer = "C";
                int correctIndex = 2;


                int[] indices = {0, 1, 2};
                shuffleArray(indices);

                String[] shuffledOptions = new String[3];
                for (int j = 0; j < 3; j++) {
                    shuffledOptions[j] = options[indices[j]];
                    if (indices[j] == 2) {
                        correctIndex = j;
                        correctAnswer = String.valueOf((char) ('A' + j));
                    }
                }

                // Display shuffled answers
                for (int j = 0; j < shuffledOptions.length; j++) {
                    System.out.println((char) ('A' + j) + ") " + shuffledOptions[j]);
                }

                String userAnswer = getUserAnswer(sc);

                if (userAnswer.equalsIgnoreCase(correctAnswer)) {
                    System.out.println("Correct!");
                    score++;
                    correctAnswers++;
                } else {
                    System.out.println("Incorrect. The correct answer was " + correctAnswer);
                    incorrectAnswers++;
                }

                jump();
            }

            showFinalMessage(score, quantity);
            saveStatistics(name, difficulty, correctAnswers, incorrectAnswers);

            System.out.println("Do you want to play again? (yes/no)");
            playAgain = sc.nextLine().trim().equalsIgnoreCase("yes");

        } while (playAgain);

        System.out.println("Thanks for playing! Goodbye!");
    }

    private static String[] loadQuestions(String fileName) {
        String[] questions = new String[20];
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int index = 0;
            while ((line = br.readLine()) != null && index < 20) {
                questions[index++] = line;
            }
        } catch (IOException e) {
            System.out.println("File not found: " + fileName);
        }
        return questions;
    }

    private static int[] generateRandomNumbers(int quantity) {
        Random random = new Random();
        int[] randomNumbers = new int[quantity];
        for (int i = 0; i < quantity; i++) {
            randomNumbers[i] = random.nextInt(20);
        }
        return randomNumbers;
    }

    private static void shuffleArray(int[] array) {
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    private static String getUserAnswer(Scanner sc) {
        String answer;
        while (true) {
            System.out.println("Your answer (A/B/C): ");
            answer = sc.nextLine().trim().toUpperCase();
            if (answer.equals("A") || answer.equals("B") || answer.equals("C")) {
                break;
            } else {
                System.out.println("Invalid input. Please enter A, B, or C.");
            }
        }
        return answer;
    }

    private static void showFinalMessage(int score, int total) {
        double percentage = ((double) score / total) * 100;
        String message;

        if (percentage == 100) {
            message = "Amazing! You answered all questions correctly!";
        } else if (percentage >= 67) {
            message = "Great job! You answered " + String.format("%.2f", percentage) + "% of the questions correctly.";
        } else if (percentage >= 34) {
            message = "Not bad! You answered " + String.format("%.2f", percentage) + "% of the questions correctly. Keep learning!";
        } else {
            message = "Keep trying! You answered only " + String.format("%.2f", percentage) + "% of the questions correctly.";
        }

        System.out.println(message);
    }

    private static void saveStatistics(String name, String difficulty, int correct, int incorrect) {
        String fileName = "statistics_" + difficulty + ".txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))) {
            bw.write("Name: " + name + ", Date: " + new Date() + ", Correct: " + correct + ", Incorrect: " + incorrect);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error writing statistics file.");
        }
    }

    public static void jump() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public static String difficultyQuestion(Scanner sc) {
        String difficulty = "";
        while (true) {
            System.out.println("Choose the difficulty: Easy, Medium, or Hard");
            difficulty = sc.nextLine().trim().toLowerCase();

            if (difficulty.equals("easy") || difficulty.equals("medium") || difficulty.equals("hard")) {
                break;
            } else {
                System.out.println("Invalid choice. Please choose Easy, Medium, or Hard.");
            }
        }
        return difficulty;
    }

    private static int quantityQuestion(Scanner sc) {
        System.out.println("How many questions would you like to answer? (Minimum: 5)");
        int quantity;
        while (true) {
            try {
                quantity = Integer.parseInt(sc.nextLine().trim());
                if (quantity >= 5) {
                    break;
                } else {
                    System.out.println("Please enter a number 5 or greater.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        return quantity;
    }

    private static String nameQuestion(Scanner sc) {
        System.out.println("Welcome to the quiz!");
        System.out.println("I'm glad to see you here. What is your name?");
        String name = sc.nextLine().trim();
        System.out.println("Nice to meet you, " + name + "! Let's start our quiz.");
        System.out.println("Press Enter to continue...");
        sc.nextLine();
        return name;
    }
}
