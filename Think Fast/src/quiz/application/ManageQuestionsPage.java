package quiz.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageQuestionsPage extends JFrame implements ActionListener {
    private JTextArea questionTextArea;
    private JButton addQuestionButton;
    private JButton deleteQuestionButton;
    private JButton editButton;
    private JButton gotoButton;

    String name;

    public ManageQuestionsPage(String name) {
        if (!Quiz.isInitialized) {
            Quiz.isInitialized = true;
            Quiz.initializeQuestionsAndAnswers();
        }
        this.name = name;
        setBounds(350, 200, 1200, 750);
        getContentPane().setBackground(new Color(231, 242, 248));
        setLayout(null);

        addQuestionButton = new JButton("Add Question");
        addQuestionButton.setBounds(50, 50, 200, 40);
        addQuestionButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
        addQuestionButton.setBackground(new Color(57, 91, 112));
        addQuestionButton.setForeground(Color.white);
        addQuestionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAddQuestionDialog();
            }
        });
        add(addQuestionButton);

        deleteQuestionButton = new JButton("Delete Question");
        deleteQuestionButton.setBounds(300, 50, 200, 40);
        deleteQuestionButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
        deleteQuestionButton.setBackground(new Color(57, 91, 112));
        deleteQuestionButton.setForeground(Color.white);
        deleteQuestionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showDeleteQuestionDialog();
            }
        });
        add(deleteQuestionButton);

        editButton = new JButton("Edit Question");
        editButton.setBounds(550, 50, 200, 40);
        editButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
        editButton.setBackground(new Color(57, 91, 112));
        editButton.setForeground(Color.white);
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showEditQuestionDialog();
                updateQuestionList();
            }
        });
        add(editButton);

        gotoButton = new JButton("Save");
        gotoButton.setBounds(800, 50, 200, 40);
        gotoButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
        gotoButton.setBackground(new Color(57, 91, 112));
        gotoButton.setForeground(Color.white);
        gotoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Login(); // Pass questions and answers to the constructor
            }
        });
        add(gotoButton);

        // Initialize JTextArea to display questions
        questionTextArea = new JTextArea("");
        questionTextArea.setEditable(false);

        // Create a JScrollPane and add the JTextArea to it
        JScrollPane scrollPane = new JScrollPane(questionTextArea);
        scrollPane.setBounds(50, 120, 1000, 500);
        add(scrollPane);

        updateQuestionList();

        setVisible(true);
    }

    private void showAddQuestionDialog() {
        // Implement UI for adding a question
        // Get user inputs for question, options, and correct answer
        String question = JOptionPane.showInputDialog("Enter the question:");
        String option1 = JOptionPane.showInputDialog("Enter option 1:");
        String option2 = JOptionPane.showInputDialog("Enter option 2:");
        String option3 = JOptionPane.showInputDialog("Enter option 3:");
        String option4 = JOptionPane.showInputDialog("Enter option 4:");
        String correctAnswer = JOptionPane.showInputDialog("Enter correct answer (1-4):");

        // Add the question and answer directly to the Quiz class
        Quiz.addQuestion(question, new String[] { option1, option2, option3, option4 }, correctAnswer);

        updateQuestionList();
    }

    private void showDeleteQuestionDialog() {
        String questionNumber = JOptionPane.showInputDialog("Enter the question number you want to delete:");
        int questionIndex = Integer.parseInt(questionNumber) - 1;

        if (questionIndex >= 0 && questionIndex < Quiz.questions.size()) {
            // Delete the question from the Quiz class
            Quiz.deleteQuestion(questionIndex);
            updateQuestionList();
        } else {
            JOptionPane.showMessageDialog(null, "Invalid question number.");
        }
    }

    private void showEditQuestionDialog() {
        String questionNumber = JOptionPane.showInputDialog("Enter the question number you want to edit:");
        int questionIndex = Integer.parseInt(questionNumber) - 1;

        if (questionIndex >= 0 && questionIndex < Quiz.questions.size()) {
            String newQuestion = JOptionPane.showInputDialog("Edit the question:");
            String newOption1 = JOptionPane.showInputDialog("Edit option 1:");
            String newOption2 = JOptionPane.showInputDialog("Edit option 2:");
            String newOption3 = JOptionPane.showInputDialog("Edit option 3:");
            String newOption4 = JOptionPane.showInputDialog("Edit option 4:");
            String newCorrectAnswer = JOptionPane.showInputDialog("Edit correct answer (1-4):");

            if (newCorrectAnswer.equals("1") || newCorrectAnswer.equals("2") || newCorrectAnswer.equals("3") || newCorrectAnswer.equals("4")) {
                // Update the selected question directly in the Quiz class
                Quiz.editQuestion(questionIndex, newQuestion,
                    new String[] { newOption1, newOption2, newOption3, newOption4 },
                    newCorrectAnswer);

                updateQuestionList(); // Update the displayed questions
            } else {
                JOptionPane.showMessageDialog(null, "Invalid correct answer. Please enter 1, 2, 3, or 4.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Invalid question number.");
        }
    }
    

    private void updateQuestionList() {
        questionTextArea.setText(""); // Clear previous content
        for (int i = 0; i < Quiz.questions.size(); i++) {
            String[] question = Quiz.questions.get(i);
            String correctAnswer = Quiz.answers.get(i);

            String questionText = String.format("Q%d: %s\nOptions:\n1. %s\n2. %s\n3. %s\n4. %s\nCorrect Answer: %s\n\n",
                    i + 1, question[0], question[1], question[2], question[3], question[4], correctAnswer);
            questionTextArea.append(questionText);
        }
    }

    public void actionPerformed(ActionEvent ae) {
        // Handle button clicks and user interactions
    }

    public static void main(String[] args) {
        new ManageQuestionsPage("User");
    }
}
