package bank.management.system1;

import javax.swing.*;   //JFrame JLabel JButton JTextFiel JTable
import java.awt.*;       //setting background color, fonts, layouts.
import  java.awt.event.*; //Used for event handling like button click, mouse events, keyboard events.
import java.sql.*;       // onnection Statement ResultSet


public class Login extends JFrame implements ActionListener {
    
    JButton clear,signup,login;
    JTextField cardTextField ;
    JPasswordField pinTextField;

    Login() {
        setTitle("AUTOMATED TELLER MACHINE ");
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));   //⭐ Loads the image (logo.jpg) from the icons folder
        Image i2 = i1.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT);         //⭐ Converts the ImageIcon to Image and resizes it to 100×100 pixels
        ImageIcon i3 =new ImageIcon(i2);                                                 //⭐ Converts the resized Image back to ImageIcon so it can be displayed 
        JLabel label = new JLabel(i3);                                                   //⭐ Creates a label and puts the image icon inside it
        setLayout(null);                                                                 //⭐ Because by default layout is in center 
        label.setBounds(70,10,100,100);                                                  //⭐ setBounds(x, y, width, height);   setBounds = manual positioning + manual sizefor bank image 
        add(label);                                                                      //⭐ Adds the image label to the frame so it becomes visible
        
        JLabel text = new JLabel("welcome to ATM");                                      //⭐ Creates a label that will display the text "Welcome to ATM"
        text.setFont (new Font("Osward", Font.BOLD ,38));                                //⭐ Changes the text style:  Font name → Osward  Font weight → BOLD  Font size → 38
        text.setBounds(200,40,400,40);                                                   //⭐ Positions and sizes the label: X = 200 → move 200 pixels from the left Y = 40 → move 40 pixels from the top Width = 400 → allow text width Height = 40 → label height
        add(text);                                                                       //⭐ Adds the label to the JFrame so it appears on the screen
        
        
        //⭐ same like that create card no 
        JLabel cardno = new JLabel("Card No :");                                          
        cardno.setFont (new Font("Raleway", Font.BOLD ,28));                               
        cardno.setBounds(120,150, 150,30);                                               
        add(cardno); 
        
        cardTextField = new JTextField();
        cardTextField.setBounds(300 ,150,250,30);
        cardTextField.setFont(new Font("Arical ", Font.BOLD ,14));
        add(cardTextField);
        
        //⭐ create pin 
        
        JLabel pin = new JLabel("PIN :");                                                
        pin.setFont (new Font("Raleway", Font.BOLD ,28));                                 
        pin.setBounds(120,220,150,30);                                                
        add(pin); 
        
        pinTextField = new JPasswordField();
        pinTextField.setBounds(300 ,220,250,30);
        pinTextField.setFont(new Font("Arical ", Font.BOLD ,14));
        add(pinTextField);
        
        //⭐  buttons 
        login = new JButton("SIGN IN");
        login.setBounds(300,300,100,30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);                                                //⭐  to text color white use setForeground
        login.addActionListener(this);
        add(login);
        
        clear = new JButton("CLEAR");
        clear.setBounds(430,300,100,30);
        clear.setBackground(Color.BLACK);
        clear.setForeground(Color.WHITE);                                                //⭐  to text color white use setForeground
        clear.addActionListener(this);
        add(clear);
        
        signup = new JButton("SIGN UP");
        signup.setBounds(300,350,230,30);
        signup.setBackground(Color.BLACK);
        signup.setForeground(Color.WHITE);                                                //⭐  to text color white use setForeground
        signup.addActionListener(this);
        add(signup);
        
        
        getContentPane().setBackground(Color.WHITE);                                     //⭐ Set the background color as white
        
        setSize(800, 480); 
        setLocation(350,200);
        setVisible(true);

    }
    
    public void actionPerformed(ActionEvent ae ){
        if(ae.getSource() == clear){
            cardTextField.setText("");
            pinTextField.setText("");

        }else if(ae.getSource() == login){
            Conn conn = new Conn();
            String cardnumber = cardTextField.getText();
            String pinnumber = pinTextField.getText();

            String query ="select * from login where cardnumber ='"+cardnumber+"' and pin ='"+pinnumber+"'";
            try{
                ResultSet rs =conn.s.executeQuery(query);
                
                if(rs.next()){
                    setVisible(false);
                    new Transactions(pinnumber).setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(null , "Incorrect card number or pin");
                }
            }catch(Exception e){
                System.out.println(e);
            }
        }else if(ae.getSource() == signup){
            setVisible(false);    
            new SignupOne().setVisible(true);
        }
            
    }

    public static void main(String[] args) {
        new Login();
    }
}


