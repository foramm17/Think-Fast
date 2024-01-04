package quiz.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//import java.util.ArrayList;

public class Rules extends JFrame implements ActionListener {

    String name;
    JButton start, back;

    Rules(String name) {
        this.name = name;

        getContentPane().setBackground(new Color(231, 242, 248));
        setLayout(null);

        JLabel heading = new JLabel("Welcome " + name + " to Think Fast");
        heading.setBounds(200, 30, 750, 70);
        heading.setFont(new Font("Garamond", Font.BOLD, 45));
        heading.setForeground(new Color(69, 106, 125));
        add(heading);

        JLabel rules = new JLabel("");
        rules.setBounds(80, 110, 850, 500);
        rules.setFont(new Font("Tahoma", Font.PLAIN, 20));
        rules.setForeground(Color.BLACK);
        rules.setText(
                "<html>" +
                        "1. Read Instructions: Carefully read any instructions or rules provided at the beginning of the quiz. Understand the scoring system, time limits (if any), and any special rules."
                        + "<br><br>" +
                        "2. Manage Time: Keep track of the time available for each question. If there's a time limit for the entire quiz, divide it by the number of questions to allocate time per question."
                        + "<br><br>" +
                        "3. Stay Focused: Avoid distractions and maintain focus throughout the quiz. Minimize interruptions and create a conducive environment"
                        + "<br><br>" +
                        "4. Review Answers: If time allows, review your answers before submitting. Check for errors, typos, and ensure you've answered all questions."
                        + "<br><br>" +
                        "5. Stay Positive: Maintain a positive mindset, even if you encounter difficult questions. Stay calm and composed."
                        + "<br><br>" +
                        "6. Submit Early: If you've reviewed and are confident in your answers, submit your quiz before the time expires"
                        + "<br><br>" +

                        "<html>");
        add(rules);

        start = new JButton("Start");
        start.setBounds(200, 600, 140, 55);
        start.setBackground(new Color(150, 187, 118));
        start.setForeground(Color.WHITE);
        start.addActionListener(this);
        add(start);

        back = new JButton("Back");
        back.setBounds(650, 600, 140, 55);
        back.setBackground(new Color(170, 170, 170));
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        setSize(1000, 800);
        setLocation(450, 150);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == start) {
            setVisible(false);
            new Quiz(name);
        } else {
            setVisible(false);
            new Login();
        }
    }

    public static void main(String[] args) {
        new Rules("User");
    }

}
