package quiz.application;

import java.util.ArrayList;

public class ManageQuestions {
    private ArrayList<Question> questionsList;

    public ManageQuestions() {
        questionsList = new ArrayList<>();
    }

   public void addQuestion(String question, String option1, String option2, String option3, String option4, String correctAnswer) {
    Question newQuestion = new Question(question, option1, option2, option3, option4, correctAnswer);
    questionsList.add(newQuestion);
}


    public void deleteQuestion(int questionIndex) {
        if (questionIndex >= 0 && questionIndex < questionsList.size()) {
            questionsList.remove(questionIndex);
        }
    }

    public ArrayList<Question> getQuestions() {
        return questionsList;
    }

   
}