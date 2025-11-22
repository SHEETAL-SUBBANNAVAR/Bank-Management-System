package bank.management.system1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener{

    JLabel text;
    JButton deposit,withdrawl,fastcash,ministatement,pinchange,balanceenquiry,exit;
    String pinnumber;
    FastCash(String pinnumber){
        setLayout(null);

        this.pinnumber = pinnumber;
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);
        
        text = new JLabel("SELECT WITHDRAWL AMOUNT");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        text.setBounds(210,300,700,35);
        image.add(text);
       
        deposit = new JButton("Rs 100");
        withdrawl = new JButton("Rs 500");
        fastcash = new JButton("Rs 1000");
        ministatement = new JButton("Rs 2000");
        pinchange = new JButton("Rs 5000");
        balanceenquiry = new JButton("Rs 10000");
        exit = new JButton("BACK");
        
        setLayout(null);
        
        
        
        deposit.setBounds(170,415,150,30);
        image.add(deposit);
        
        withdrawl.setBounds(355,415,150,30);
        image.add(withdrawl);
        
        fastcash.setBounds(170,450,150,30);
        image.add(fastcash);
        
        ministatement.setBounds(355,450,150,30);
        image.add(ministatement);
        
        pinchange.setBounds(170,485,150,30);
        image.add(pinchange);
        
        balanceenquiry.setBounds(355,485,150,30);
        image.add(balanceenquiry);
        
        exit.setBounds(355,520,150,30);
        image.add(exit);
        
        
        deposit.addActionListener(this);
        withdrawl.addActionListener(this);
        fastcash.addActionListener(this);
        ministatement.addActionListener(this);
        pinchange.addActionListener(this);
        balanceenquiry.addActionListener(this);
        exit.addActionListener(this);
        
        
        setSize(900,900);
        setLocation(300,0);
        setUndecorated(true);
        setVisible(true);
    }
       public void actionPerformed(ActionEvent ae){
             if(ae.getSource()== exit){ 
                setVisible(false);
                new Transactions(pinnumber).setVisible(true);
            }
             else{
                 String amount = ((JButton) ae.getSource()).getText().substring(3);
                 Conn c =new Conn();
                 try{
                     ResultSet rs =c.s.executeQuery("select * from bank where pin = '"+pinnumber+"'");
                     int balance = 0;
                     while(rs.next()){
                         if(rs.getString("type").equals("Deposit")){
                             balance += Integer.parseInt(rs.getString("amount"));
                         }else{
                             balance -= Integer.parseInt(rs.getString("amount"));
                         }
                     }
                     if(ae.getSource() != exit && balance < Integer.parseInt(amount)){
                        JOptionPane.showMessageDialog(null, "Insuddicient Balance");
                        return;
                     }
                     Date date = new Date();
                     String query = "insert into bank values ('"+pinnumber+"','"+date+"','Withdraw','"+amount+"')";
                   
                     c.s.executeUpdate(query);
                     JOptionPane.showMessageDialog(null, "Rs "+ amount +" ebited Sucessfully");
                     
                     setVisible(false);
                     new Transactions(pinnumber).setVisible(true);
                     
                 }catch(Exception e){
                     System.out.println(e);
                 }
             }
        }
    
    
    public static void main(String[] args){
        new FastCash("");
    }
}