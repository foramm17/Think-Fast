package quiz.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Quiz extends JFrame implements ActionListener {

    static boolean isInitialized = false;

    static ArrayList<String[]> questions = new ArrayList<>();
    static ArrayList<String> answers = new ArrayList<>(); // Use a single String for correct answers
    static ArrayList<String> userAnswers = new ArrayList<>();
    JLabel qno, question;
    JRadioButton opt1, opt2, opt3, opt4;
    ButtonGroup groupoptions;
    JButton next, submit, lifeline;

    public static int timer = 15;
    public static int ansGiven = 0;
    public static int count = 0;
    public static int score = 0;

    String name;

    Quiz(String name) {
        if (!Quiz.isInitialized) {
            Quiz.isInitialized = true;
            Quiz.initializeQuestionsAndAnswers();
        }
        this.name = name;
        setBounds(300, 60, 1400, 970);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/thinkfast.jpg")); // imaage object that will
                                                                                            // load object
        JLabel image = new JLabel(i1); // has to make object of JLabel class as we can notmdirectly add image to frame
        image.setBounds(0, 0, 1400, 430);
        add(image);

        qno = new JLabel();
        qno.setBounds(100, 490, 50, 30);
        qno.setFont(new Font("Tahoma", Font.PLAIN, 24));
        add(qno);

        question = new JLabel();
        question.setBounds(150, 490, 900, 30);
        question.setFont(new Font("Tahoma", Font.PLAIN, 24));
        add(question);

        // initializeQuestionsAndAnswers();

        opt1 = new JRadioButton();
        opt1.setBounds(170, 540, 900, 30);
        opt1.setBackground(Color.WHITE);
        opt1.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(opt1);

        opt2 = new JRadioButton();
        opt2.setBounds(170, 580, 900, 30);
        opt2.setBackground(Color.WHITE);
        opt2.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(opt2);

        opt3 = new JRadioButton();
        opt3.setBounds(170, 620, 900, 30);
        opt3.setBackground(Color.WHITE);
        opt3.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(opt3);

        opt4 = new JRadioButton();
        opt4.setBounds(170, 660, 900, 30);
        opt4.setBackground(Color.WHITE);
        opt4.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(opt4);

        groupoptions = new ButtonGroup();
        groupoptions.add(opt1);
        groupoptions.add(opt2);
        groupoptions.add(opt3);
        groupoptions.add(opt4);

        next = new JButton("Next");
        next.setBounds(300, 800, 200, 40);
        next.setFont(new Font("Tahoma", Font.PLAIN, 22));
        next.setBackground(new Color(57, 91, 112));
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        add(next);

        /*
         * pre = new JButton("Previous");
         * pre.setBounds(200, 800, 200, 40);
         * pre.setFont(new Font("Tahoma", Font.PLAIN, 22));
         * pre.setBackground(new Color(57, 91, 112));
         * pre.setForeground(Color.white);
         * pre.addActionListener(this);
         * add(pre);
         */

        lifeline = new JButton("50-50 Lifeline");
        lifeline.setBounds(600, 800, 200, 40);
        lifeline.setFont(new Font("Tahoma", Font.PLAIN, 22));
        lifeline.setBackground(new Color(57, 91, 112));
        lifeline.setForeground(Color.WHITE);
        lifeline.addActionListener(this);
        add(lifeline);

        submit = new JButton("Submit");
        submit.setBounds(900, 800, 200, 40);
        submit.setFont(new Font("Tahoma", Font.PLAIN, 22));
        submit.setBackground(new Color(57, 91, 112));
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        submit.setEnabled(false);
        add(submit);

        start(count);

        setVisible(true);
    }

    // Initialize questions and answers
    public static void initializeQuestionsAndAnswers() {
        // Add questions and answers to ArrayLists for three questions
        for (int i = 0; i < 3; i++) {
            questions.add(new String[5]);
            answers.add(""); // Initialize with an empty string
            userAnswers.add("");
        }

        // Question 1
        questions.get(0)[0] = "Which is used to find and fix bugs in the Java programs.?";
        questions.get(0)[1] = "JDB";
        questions.get(0)[2] = "JVM";
        questions.get(0)[3] = "JDK";
        questions.get(0)[4] = "JRE";
        answers.set(0, "JDB"); // Set the correct answer as a single String
        // Question 2
        questions.get(1)[0] = "What is the return type of the hashCode() method in the Object class?";
        questions.get(1)[1] = "Object";
        questions.get(1)[2] = "int";
        questions.get(1)[3] = "long";
        questions.get(1)[4] = "void";
        answers.set(1, "int"); // Set the correct answer as a single String
        // Question 3
        questions.get(2)[0] = "Which package contains the Random class?";
        questions.get(2)[1] = "java.util package";
        questions.get(2)[2] = "java.lang package";
        questions.get(2)[3] = "java.awt package";
        questions.get(2)[4] = "java.io package";
        answers.set(2, "java.util package"); // Set the correct answer as a single String
    }

    public static void addQuestion(String question, String[] options, String correctAnswer) {
        // Create new arrays and add the question and answers
        questions.add(new String[5]);
        for (int i = 0; i < options.length; i++) {
            questions.get(questions.size() - 1)[i + 1] = options[i];
        }
        questions.get(questions.size() - 1)[0] = question;
        answers.add(correctAnswer);

        // Add a corresponding entry in userAnswers
        userAnswers.add("");
    }

    public static void deleteQuestion(int index) {
        if (index >= 0 && index < questions.size()) {
            questions.remove(index);
            answers.remove(index);
            userAnswers.remove(index);
        }
    }

    public static void editQuestion(int index, String question, String[] options, String correctAnswer) {
        // Check if index is out of range
        if (index < 0 || index >= questions.size()) {
            // Handle this case by not editing, as it's an invalid index
            return;
        }

        // Now, edit the question at the given index
        questions.get(index)[0] = question;
        for (int i = 0; i < options.length; i++) {
            questions.get(index)[i + 1] = options[i];
        }
        answers.set(index, correctAnswer);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == next) {
            repaint();
            opt1.setEnabled(true);
            opt2.setEnabled(true);
            opt3.setEnabled(true);
            opt4.setEnabled(true);

            ansGiven = 1;
            if (groupoptions.getSelection() == null) {
                userAnswers.set(count, "");
            } else {
                userAnswers.set(count, groupoptions.getSelection().getActionCommand());
            }

            if (count == questions.size() - 2) {
                System.out.println(questions.size());
                next.setEnabled(false);
                submit.setEnabled(true);
            }

            count++;
            start(count);
        }  else if (ae.getSource() == lifeline) {
            if (count % 2 == 0) { // For even counts
                opt2.setEnabled(false);
                opt3.setEnabled(false);
            } else { // For odd counts
                opt1.setEnabled(false);
                opt4.setEnabled(false);
            }
            lifeline.setEnabled(false);
        } else if (ae.getSource() == submit) {
            ansGiven = 1;
            if (groupoptions.getSelection() == null) {
                userAnswers.set(count, "");
            } else {
                userAnswers.set(count, groupoptions.getSelection().getActionCommand());
            }

            for (int i = 0; i < userAnswers.size(); i++) {
                if (userAnswers.get(i).equals(answers.get(i))) {
                    score += 10;
                } else {
                    score += 0;
                }
            }
            setVisible(false);
            count = 0;
            new Score(name, score);
            score = 0;
        }
    }

    public void paint(Graphics g) {
        super.paint(g);

        String time = "Time left - " + timer + " seconds"; // 15
        g.setColor(Color.RED);
        g.setFont(new Font("Tahoma", Font.BOLD, 20));

        if (timer > 0) {
            g.drawString(time, 1000, 510);
        } else {
            g.drawString("Times up!!", 1000, 510);
        }

        timer--; // 14

        try {
            Thread.sleep(1000);
            repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (ansGiven == 1) {
            ansGiven = 0;
            timer = 15;
        } else if (timer < 0) {
            timer = 15;
            opt1.setEnabled(true);
            opt2.setEnabled(true);
            opt3.setEnabled(true);
            opt4.setEnabled(true);

            if (count == questions.size() - 2) {
                System.out.println(questions.size());
                next.setEnabled(false);
                submit.setEnabled(true);
            }
            if (count == questions.size() - 1) { // submit button
                if (groupoptions.getSelection() == null) {
                    userAnswers.set(count, "");
                } else {
                    userAnswers.set(count, groupoptions.getSelection().getActionCommand());
                }

                for (int i = 0; i < userAnswers.size(); i++) {
                    if (userAnswers.get(i).equals(answers.get(i))) {
                        score += 10;
                    } else {
                        score += 0;
                    }
                }
                setVisible(false);
                new Score(name, score);
            } else { // next button
                if (groupoptions.getSelection() == null) {
                    userAnswers.set(count, "");
                } else {
                    userAnswers.set(count, groupoptions.getSelection().getActionCommand());
                }
                count++; // 0 // 1
                start(count);
            }
        }

    }

    public void start(int count) {
        qno.setText("" + (count + 1) + ". ");
        question.setText(questions.get(count)[0]);
        opt1.setText(questions.get(count)[1]);
        opt1.setActionCommand(questions.get(count)[1]);

        opt2.setText(questions.get(count)[2]);
        opt2.setActionCommand(questions.get(count)[2]);

        opt3.setText(questions.get(count)[3]);
        opt3.setActionCommand(questions.get(count)[3]);

        opt4.setText(questions.get(count)[4]);
        opt4.setActionCommand(questions.get(count)[4]);

        groupoptions.clearSelection();

    }

    public static void main(String[] args) {
        new Quiz("User");

    }
}
