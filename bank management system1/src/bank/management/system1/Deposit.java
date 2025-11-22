package bank.management.system1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Deposit extends JFrame implements ActionListener {

    JTextField amount;
    JButton deposit, back;
    String pinnumber;

    Deposit(String pinnumber){
        this.pinnumber = pinnumber;

        // Load ATM background
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);

        setContentPane(image);    // ⭐ MOST IMPORTANT FIX
        image.setLayout(null);

        // Heading
        JLabel text = new JLabel("ENTER AMOUNT YOU WANT TO DEPOSIT");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        text.setBounds(170, 300, 400, 20);
        image.add(text);

        // Amount field
        amount = new JTextField();
        amount.setFont(new Font("Raleway", Font.BOLD, 22));
        amount.setBounds(170, 350, 320, 40);
        image.add(amount);

        // Deposit button
        deposit = new JButton("DEPOSIT");
        deposit.setBounds(170, 420, 150, 35);
        image.add(deposit);

        // Back button
        back = new JButton("BACK");
        back.setBounds(340, 420, 150, 35);
        image.add(back);

        deposit.addActionListener(this);
        back.addActionListener(this);

        setSize(900, 900);
        setUndecorated(true);
        setLocation(300, 0);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){

        if(ae.getSource() == deposit){
            String number = amount.getText();
            Date date = new Date();

            if(number.equals("")){
                JOptionPane.showMessageDialog(null, "Please enter the Amount");
                return;
            }

            try {
                Conn c1 = new Conn();
                String query = "insert into bank values('"+pinnumber+"', '"+date+"', 'Deposit', '"+number+"')";
                c1.s.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Rs. "+number+" Deposited Successfully");

                setVisible(false);
                new Transactions(pinnumber).setVisible(true);

            } catch(Exception e){
                System.out.println(e);
            }
        }
        else if(ae.getSource() == back){
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }
    }

    public static void main(String[] args){
        new Deposit("").setVisible(true);
    }
}
