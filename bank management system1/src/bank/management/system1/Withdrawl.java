package bank.management.system1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.Date;  //Used for date and time.

public class Withdrawl extends JFrame implements ActionListener {

    JTextField amount;
    JButton withdraw, back;
    String pinnumber;
    JLabel image;

    Withdrawl(String pinnumber) {

        this.pinnumber = pinnumber;
        setLayout(null);

        // ATM Background
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);

        image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        // Text
        JLabel text = new JLabel("ENTER AMOUNT YOU WANT TO WITHDRAW");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        text.setBounds(170, 300, 400, 20);
        image.add(text);

        // Input
        amount = new JTextField();
        amount.setFont(new Font("Raleway", Font.BOLD, 22));
        amount.setBounds(170, 350, 320, 25);
        image.add(amount);

        // Withdraw Button
        withdraw = new JButton("WITHDRAW");
        withdraw.setBounds(170, 485, 150, 30);
        image.add(withdraw);

        // Back Button
        back = new JButton("BACK");
        back.setBounds(355, 485, 150, 30);
        image.add(back);

        withdraw.addActionListener(this);
        back.addActionListener(this);

        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == back) {
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
            return;
        }

        String number = amount.getText();

        if (number.equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter the Amount");
            return;
        }

        try {
            Conn c = new Conn();

            // --------------------- CHECK BALANCE -----------------------
            ResultSet rs = c.s.executeQuery("select * from bank where pin='" + pinnumber + "'");

            int balance = 0;

            while (rs.next()) {
                String type = rs.getString("type");
                int amt = Integer.parseInt(rs.getString("amount"));

                if (type.equals("Deposit")) {
                    balance += amt;
                } else {
                    balance -= amt;
                }
            }

            int withdrawAmt = Integer.parseInt(number);

            if (balance < withdrawAmt) {
                JOptionPane.showMessageDialog(null, "Insufficient Balance");
                return;
            }

            // ---------------- INSERT WITHDRAW RECORD -------------------
            Date date = new Date();
            String query = "insert into bank values('" + pinnumber + "', '" + date + "', 'Withdrawal', '" + number + "')";
            c.s.executeUpdate(query);

            JOptionPane.showMessageDialog(null, "Rs " + number + " Withdrawn Successfully");

            setVisible(false);
            new Transactions(pinnumber).setVisible(true);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String args[]) {
        new Withdrawl("");
    }
}

