public class Question {
    private String question, A, B, C;

    public Question(String question,String A ,String B ,String C ) {
        this.question = question;
        this.A = A;
        this.B = B;
        this.C = C;
    }

    @Override
    public String toString() {
        return "Question: " + question + "\nA: " + A + "\nB: " + B + "\nC: " + C;
    }


}
