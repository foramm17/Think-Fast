package quiz.application;

public class Question {
    private String question;
    private String[] options;
    private String correctAnswer;

    /*public Question(String question) {
        this.question = question;
        String option1 = null;
        String option2 = null;
        String option3 = null;
        String option4 = null;
        this.options = new String[]{option1, option2, option3, option4};
        this.correctAnswer = correctAnswer;
    }*/
    public Question(String question, String option1, String option2, String option3, String option4, String correctAnswer) {
    this.question = question;
    this.options = new String[]{option1, option2, option3, option4};
    this.correctAnswer = correctAnswer;
}


    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}