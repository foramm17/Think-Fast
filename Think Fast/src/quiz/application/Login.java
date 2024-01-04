package quiz.application; // as login class is inside this package

import javax.swing.*;
import java.awt.*;  //for background color
import java.awt.event.*;

public class Login extends JFrame implements ActionListener {  //used jFrame to get the frame

    JButton rules, manage, back;   //globally declared as to function as buttons
    JTextField tfname;            //globally declared as it has to pass value to next page

    Login() {  //constuctor as frame is to be visible right when we run the program

        getContentPane().setBackground(new Color(231, 242, 248));
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/login.jpg"));   //imaage object that will load object
        JLabel image = new JLabel(i1);           //has to make object of JLabel class as we can not directly add image to frame
        image.setBounds(0, 0, 700, 820);
        add(image);                              //for adding image to frame

        JLabel heading = new JLabel("Think Fast");
        heading.setBounds(850, 180, 450, 80);
        heading.setFont(new Font("Viner Hand ITC", Font.BOLD, 65));
        heading.setForeground(new Color(69, 106, 125));
        add(heading);

        JLabel name = new JLabel("Enter Your Name");
        name.setBounds(910, 300, 300, 45);
        name.setFont(new Font("Mongolian Baiti", Font.BOLD, 30));
        name.setForeground(new Color(255, 220, 92));
        add(name);

        tfname = new JTextField();
        tfname.setBounds(885, 375, 300, 25);
        tfname.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        add(tfname);

        rules = new JButton("Attempt Quiz");
        rules.setBounds(795, 460, 140, 55);
        rules.setBackground(new Color(150, 187, 118));
        rules.setForeground(Color.WHITE);
        rules.addActionListener(this);
        add(rules);

        manage = new JButton("Manage Quiz");
        manage.setBounds(970, 460, 140, 55);
        manage.setBackground(new Color(242, 177, 175));
        manage.setForeground(Color.WHITE);
        manage.addActionListener(this);
        add(manage);

        back = new JButton("Exit Quiz");
        back.setBounds(1145, 460, 140, 55);
        back.setBackground(new Color(170, 170, 170));
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        setSize(1400, 900);
        setLocation(300, 100);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == rules) {
            String name = tfname.getText();
            setVisible(false);
            new Rules(name);
        } else if (ae.getSource() == manage) {
            // Inside the ActionListener of the "Manage" button
            String password = JOptionPane.showInputDialog(null, "Enter password:");
            if (password != null && password.equals("123456")) {
                // Password correct, open the manage window
                String name = tfname.getText();
                new ManageQuestionsPage(name);
                // Code to open the manage window
            } else {
                // Incorrect password, show an error message
                JOptionPane.showMessageDialog(null, "Incorrect password. Access denied.");
            }
            //new ManageQuestionsPage(); 
            setVisible(false);
        } else if (ae.getSource() == back) {
            setVisible(false);
        }

    }

    public static void main(String[] args) {
        new Login();
    }

}
