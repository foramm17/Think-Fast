
package quiz.application;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Score extends JFrame implements ActionListener{
    
    Score(String name, int score){
        setBounds(350, 100, 1200, 750);
        getContentPane().setBackground(new Color(231, 242, 248));
        setLayout(null);
        
       
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/tick.jpg"));   //imaage object that will load object
        JLabel image = new JLabel(i1);           //has to make object of JLabel class as we can notmdirectly add image to frame
        image.setBounds(0, 0,700, 700);
        add(image);
        
        JLabel heading = new JLabel("Thank You " + name +" for attempting Think Fast");
        heading.setBounds(675, 200, 500, 30);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 24));
        add(heading);
        
        JLabel lblscore = new JLabel("Your Score is: " + score);
        lblscore.setBounds(800, 300, 500, 30);
        lblscore.setFont(new Font("Tahoma", Font.PLAIN, 24));
        add(lblscore);
        
        JButton submit = new JButton("Play Again");
        submit.setBounds(800, 400, 200, 40);
        submit.setFont(new Font("Tahoma", Font.BOLD, 22));
        submit.setBackground(new Color(57, 91, 112));
        submit.setForeground(Color.white);
        submit.addActionListener(this);
        add(submit);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        setVisible(false);
        new Login();
    }
    
    public static void main(String[] args){
        new Score("User", 0);
    }
}
