package bank.management.system1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PinChange extends JFrame implements ActionListener {

    JPasswordField pin, repin;
    JButton change, back;
    JLabel text, pintext, repintext, image;
    String pinnumber;

    PinChange(String pinnumber) {
        this.pinnumber = pinnumber;

        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);

        image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        // Heading
        text = new JLabel("CHANGE YOUR PIN");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        text.setBounds(250, 250, 500, 35);
        image.add(text);

        // New PIN Label
        pintext = new JLabel("New PIN:");
        pintext.setForeground(Color.WHITE);
        pintext.setFont(new Font("System", Font.BOLD, 16));
        pintext.setBounds(250, 300, 500, 35);
        image.add(pintext);

        // New PIN Input
        pin = new JPasswordField();
        pin.setFont(new Font("Raleway", Font.BOLD, 25));
        pin.setBounds(330, 300, 180, 30);
        image.add(pin);

        // Re-enter PIN Label
        repintext = new JLabel("Re-Enter New PIN:");
        repintext.setForeground(Color.WHITE);
        repintext.setFont(new Font("System", Font.BOLD, 16));
        repintext.setBounds(250, 350, 500, 35);
        image.add(repintext);

        // Re-enter PIN Input
        repin = new JPasswordField();
        repin.setFont(new Font("Raleway", Font.BOLD, 25));
        repin.setBounds(330, 350, 180, 30);
        image.add(repin);

        // Change Button
        change = new JButton("CHANGE");
        change.setFont(new Font("Raleway", Font.BOLD, 22));
        change.setBounds(330, 420, 180, 30);
        change.addActionListener(this);
        image.add(change);

        // Back Button
        back = new JButton("BACK");
        back.setFont(new Font("Raleway", Font.BOLD, 22));
        back.setBounds(330, 470, 180, 30);
        back.addActionListener(this);
        image.add(back);

        setSize(900, 900);
        setUndecorated(true);
        setLocation(300, 0);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == change) {
            try {
                String npin = pin.getText();
                String rpin = repin.getText();

                if (!npin.equals(rpin)) {
                    JOptionPane.showMessageDialog(null, "Entered PIN does not match");
                    return;
                }
                if (npin.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter new PIN");
                    return;
                }
                if (rpin.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please re-enter new PIN");
                    return;
                }

                Conn conn = new Conn();
                String query1 = "update bank set pin = '" + rpin + "' where pin = '" + pinnumber + "'";
                String query2 = "update login set pin = '" + rpin + "' where pin = '" + pinnumber + "'";
                String query3 = "update signupthree set pin = '" + rpin + "' where pin = '" + pinnumber + "'";

                conn.s.executeUpdate(query1);
                conn.s.executeUpdate(query2);
                conn.s.executeUpdate(query3);

                JOptionPane.showMessageDialog(null, "PIN changed successfully");

                setVisible(false);
                new Transactions(rpin).setVisible(true);

            } catch (Exception e) {
                System.out.println(e);
            }

        } else {
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new PinChange("").setVisible(true);
    }
}
