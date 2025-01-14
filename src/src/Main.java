import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        int score = 0;
        int incorrectAnswers = 0;

        String fileNameEasy = "src/src/Resources/questions&Answers(easy).txt";
        String fileNameMedium = "src/src/Resources/questions&Answers(medium).txt";
        String fileNameHard = "src/src/Resources/questions&Answers(hard).txt";

        boolean playAgain = false;

        do {
            String name = askNameUserQuestion();
            wait2SecondsThanJump50Lines();
            int quantity = askUserQuantityQuestion();
            wait2SecondsThanJump50Lines();
            String difficulty = askUSerDifficultyQuestion();
            wait2SecondsThanJump50Lines();

            String [] questions = chooseDificultyAndloadQuestions(difficulty,fileNameEasy, fileNameMedium, fileNameHard);

            int[] randomNumbers = generateRandomNumbersForRandomQuestions(quantity);

            for (int i = 0; i < quantity; i++) {

                String[] parts = separateAndBuidlQuestion(questions,randomNumbers, i);
                String[] options = {parts[1], parts[2], parts[3]};

                int[] indices = {0, 1, 2};

                CreateShuffleArrayNumbers(indices);

                String[] result = shuffleOptionsAndDetermineCorrectAnswer(options, indices);
                String correctAnswer = result[0];
                String[] shuffledOptions = {result[1], result[2], result[3]};

                printShuffledOptions(shuffledOptions);

                String userAnswer = askUserForAnswerThanReturnAnswer();

                int[] scoreIncorrectAnswer = checkUserAnswer(score, incorrectAnswers, userAnswer, correctAnswer);
                score = scoreIncorrectAnswer[0];
                incorrectAnswers = scoreIncorrectAnswer[1];

                wait2SecondsThanJump50Lines();
            }

            showFinalMessage(score, quantity);
            saveStatistics(name, difficulty, score, incorrectAnswers);

            playAgain = askUserPlayAgain();

        } while (playAgain = true);

        System.out.println("Thanks for playing! Goodbye!");
    }

    private static boolean askUserPlayAgain() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Do you want to play again? (yes/no)");
        if (sc.equals(sc.nextLine().trim().equalsIgnoreCase("yes"))){
            sc.close();
            return false;}
        else {
            sc.close();
            return true;
        }

    }

    private static int[] checkUserAnswer(int score, int incorrectAnswers, String userAnswer, String correctAnswer) {
        if (userAnswer.equalsIgnoreCase(correctAnswer)) {
            System.out.println("Correct!");
            score++;
        } else {
            System.out.println("Incorrect. The correct answer was " + correctAnswer);
            incorrectAnswers++;
        }
        return new int[]{score, incorrectAnswers};
    }


    public static void printShuffledOptions(String[] shuffledOptions) {
        for (int j = 0; j < shuffledOptions.length; j++) {
            System.out.println((char) ('A' + j) + ") " + shuffledOptions[j]);
        }
    }

    private static String[] shuffleOptionsAndDetermineCorrectAnswer(String[] options, int[] indices) {
        String[] shuffledOptions = new String[3];
        String correctAnswer = "C";

        for (int j = 0; j < 3; j++) {
            shuffledOptions[j] = options[indices[j]];
            if (indices[j] == 2) {
                correctAnswer = String.valueOf((char) ('A' + j));
            }
        }
        return new String[] {correctAnswer, shuffledOptions[0], shuffledOptions[1], shuffledOptions[2]};
    }
    private static String[] separateAndBuidlQuestion(String[] questions,int[] randomNumbers, int i) {
        String question = questions[randomNumbers[i]];
        String[] parts = question.split(";", -1);
        System.out.println("Question " + (i + 1) + ": " + parts[0]);
        return parts;
    }

    private static String[] chooseDificultyAndloadQuestions(String difficulty, String fileNameEasy, String fileNameMedium, String fileNameHard) {
        return switch (difficulty) {
            case "easy" -> loadStringQuestions(fileNameEasy);
            case "medium" -> loadStringQuestions(fileNameMedium);
            case "hard" -> loadStringQuestions(fileNameHard);
            default -> new String[0];
        };
    }

    public static String[] loadStringQuestions(String fileName) {
        String[] questions = new String[20];
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int index = 0;
            while ((line = br.readLine()) != null) {
                questions[index++] = line;
            }
        } catch (IOException e) {
            System.out.println("File not found: " + fileName);
        }
        return questions;
    }

    public static int[] generateRandomNumbersForRandomQuestions(int quantity) {
        Random random = new Random();
        Set<Integer> uniqueNumbers = new HashSet<>();

        while (uniqueNumbers.size() < quantity) {
            int randomNumber = random.nextInt(20);
            uniqueNumbers.add(randomNumber);

        }

        int[] randomNumbers = new int[quantity];
        int index = 0;
        for (int num : uniqueNumbers) {
            randomNumbers[index++] = num;
        }

        return randomNumbers;
    }

    public static void CreateShuffleArrayNumbers(int[] array) {
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    public static String askUserForAnswerThanReturnAnswer() {
        String answer;
        boolean exit = false;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Your answer (A/B/C): ");
            answer = sc.nextLine().trim().toUpperCase();
            if (answer.equals("A") || answer.equals("B") || answer.equals("C")) {
                exit = true;
            } else {
                System.out.println("Invalid input. Please enter A, B, or C.");
            }
        }while(!exit);
        sc.close();
        return answer;
    }

    public static void showFinalMessage(int score, int total) {
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

    public static void saveStatistics(String name, String difficulty, int correct, int incorrect) {
        String fileName = "statistics_" + difficulty + ".txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))) {
            bw.write("Name: " + name + ", Date: " + new Date() + ", Correct: " + correct + ", Incorrect: " + incorrect);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error writing statistics file.");
        }
    }

    public static void wait2SecondsThanJump50Lines() {
        try{Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public static String askUSerDifficultyQuestion() {
        Scanner sc = new Scanner(System.in);
        String difficulty;
        boolean exit = false;
        sc.nextLine();
        do {
            System.out.println("Choose the difficulty: Easy, Medium, or Hard");
            difficulty = sc.nextLine().trim().toLowerCase();

            if (difficulty.equals("easy") || difficulty.equals("medium") || difficulty.equals("hard")) {
                exit = true;
            } else {
                System.out.println("Invalid choice. Please choose Easy, Medium, or Hard.");
            }
        } while(!exit);
        sc.close();
        return difficulty;
    }

    public static int askUserQuantityQuestion() {
        Scanner sc = new Scanner(System.in);
        System.out.println("How many questions would you like to answer? (Minimum: 5)");
        int quantity = 0;
        boolean exit = false;
        do {
            try {
                quantity = sc.nextInt();
                if (quantity >= 5 && quantity <= 20) {
                    exit = true;
                } else {
                    System.out.println("Please enter a number 5 or greater.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        } while(!exit);
        sc.close();
        return quantity;
    }

    public static String askNameUserQuestion() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the quiz!");
        System.out.println("I'm glad to see you here. What is your name?");
        String name = sc.nextLine().trim();
        System.out.println("Nice to meet you, " + name + "! Let's start our quiz.");
        System.out.println("Press Enter to continue...");
        sc.nextLine();
        sc.close();
        return name;
    }
}
