import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.util.*;

class MainTest {

    @Test
    void jump() {
        assertDoesNotThrow(() -> Main.wait2SecondsThanJump50Lines());
    }

    @Test
    void difficultyQuestion() {
        String input = "medium\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner sc = new Scanner(System.in);
        String result = Main.askUSerDifficultyQuestion(sc);
        assertEquals("medium", result);
    }

    @Test
    void loadQuestions() throws IOException {
        String[] questions = Main.loadStringQuestions("src/src/Resources/questions&Answers(easy).txt");
        assertNotNull(questions);
        assertTrue(questions.length > 0);
    }
}
