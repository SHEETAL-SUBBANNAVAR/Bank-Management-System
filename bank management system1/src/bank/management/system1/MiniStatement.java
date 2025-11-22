package bank.management.system1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class MiniStatement extends JFrame implements ActionListener {

    JButton b1;
    JLabel mini;

    MiniStatement(String pinnumber) {

        setLayout(null);
        setTitle("Mini Statement");
        getContentPane().setBackground(Color.WHITE);

        setSize(400, 600);
        setLocation(20, 20);

        mini = new JLabel();
        mini.setBounds(20, 140, 350, 300);
        add(mini);

        JLabel bank = new JLabel("Indian Bank");
        bank.setFont(new Font("System", Font.BOLD, 16));
        bank.setBounds(140, 20, 200, 20);
        add(bank);

        JLabel card = new JLabel();
        card.setBounds(20, 80, 350, 20);
        add(card);

        JLabel balance = new JLabel();
        balance.setBounds(20, 460, 350, 20);
        add(balance);

        // ================= CARD NUMBER =====================
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM login WHERE pin = '"+pinnumber+"'");

            while (rs.next()) {
                String cardNum = rs.getString("cardnumber");

                if (cardNum != null && cardNum.length() == 16) {
                    card.setText("Card Number: " 
                        + cardNum.substring(0, 4) 
                        + "XXXX XXXX" 
                        + cardNum.substring(12));
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        // ================= MINI STATEMENT LIST =====================
        try {
            Conn conn = new Conn();
            int bal = 0;

            StringBuilder html = new StringBuilder("<html>");

            ResultSet rs = conn.s.executeQuery("SELECT * FROM bank WHERE pin = '"+pinnumber+"'");

            while (rs.next()) {

                html.append(rs.getString("date"))
                    .append("&nbsp;&nbsp;&nbsp;")
                    .append(rs.getString("type"))
                    .append("&nbsp;&nbsp;&nbsp;")
                    .append(rs.getString("amount"))
                    .append("<br><br>");

                if (rs.getString("type").equals("Deposit")) {
                    bal += Integer.parseInt(rs.getString("amount"));
                } else {
                    bal -= Integer.parseInt(rs.getString("amount"));
                }
            }

            html.append("</html>");
            mini.setText(html.toString());

            balance.setText("Your Current Balance: Rs " + bal);

        } catch (Exception e) {
            System.out.println(e);
        }

        // Exit Button
        b1 = new JButton("Exit");
        b1.setBounds(20, 520, 100, 25);
        b1.addActionListener(this);
        add(b1);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        this.setVisible(false);
    }

    public static void main(String[] args) {
        new MiniStatement("").setVisible(true);
    }
}
