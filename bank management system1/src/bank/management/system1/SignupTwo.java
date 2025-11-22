package bank.management.system1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SignupTwo extends JFrame implements ActionListener {

    JTextField pan, aadhar;
    JButton next;
    JRadioButton syes, sno, eyes, eno;
    String formno;

    JComboBox religion, category, Occupation, education, income;

    SignupTwo(String formno) {

        this.formno = formno;
        setLayout(null);
        setTitle("NEW ACCOUNT APPLICATION FORM - PAGE 2");

        JLabel additionalDetails = new JLabel("Page 2: Additional Details");
        additionalDetails.setFont(new Font("Raleway", Font.BOLD, 30));
        additionalDetails.setBounds(250, 80, 500, 40);
        add(additionalDetails);

        // Religion
        JLabel name = new JLabel("Religion:");
        name.setFont(new Font("Raleway", Font.BOLD, 20));
        name.setBounds(100, 140, 200, 30);
        add(name);

        String valReligion[] = {"Hindu", "Muslim", "Jain", "Sikh", "Christian", "Other"};
        religion = new JComboBox(valReligion);
        religion.setBounds(300, 140, 400, 30);
        religion.setBackground(Color.WHITE);
        add(religion);

        // Category
        JLabel fname = new JLabel("Category:");
        fname.setFont(new Font("Raleway", Font.BOLD, 20));
        fname.setBounds(100, 190, 200, 30);
        add(fname);

        String valCategory[] = {"General", "OBC", "SC", "ST", "Other"};
        category = new JComboBox(valCategory);
        category.setBounds(300, 190, 400, 30);
        category.setBackground(Color.WHITE);
        add(category);

        // Income
        JLabel dob = new JLabel("Income:");
        dob.setFont(new Font("Raleway", Font.BOLD, 20));
        dob.setBounds(100, 240, 200, 30);
        add(dob);

        String IncomeCategory[] = {"null", "< 1,50,000", "< 2,50,000", "< 5,00,000", "Up to 10,00,000"};
        income = new JComboBox(IncomeCategory);
        income.setBounds(300, 240, 400, 30);
        income.setBackground(Color.WHITE);
        add(income);

        // Education Qualification
        JLabel eduLbl = new JLabel("Qualification:");
        eduLbl.setFont(new Font("Raleway", Font.BOLD, 20));
        eduLbl.setBounds(100, 315, 200, 30);
        add(eduLbl);

        String educationValues[] = {"Non-Graduation", "Graduation", "Post-Graduation", "Doctorate", "Others"};
        education = new JComboBox(educationValues);
        education.setBounds(300, 315, 400, 30);
        education.setBackground(Color.WHITE);
        add(education);

        // Occupation
        JLabel Marital = new JLabel("Occupation:");
        Marital.setFont(new Font("Raleway", Font.BOLD, 20));
        Marital.setBounds(100, 390, 200, 30);
        add(Marital);

        String occupationValues[] = {"Salaried", "Self-Employed", "Business", "Student", "Retired", "Others"};
        Occupation = new JComboBox(occupationValues);
        Occupation.setBounds(300, 390, 400, 30);
        Occupation.setBackground(Color.WHITE);
        add(Occupation);

        // PAN Number
        JLabel address = new JLabel("PAN Number:");
        address.setFont(new Font("Raleway", Font.BOLD, 20));
        address.setBounds(100, 440, 200, 30);
        add(address);

        pan = new JTextField();
        pan.setFont(new Font("Raleway", Font.BOLD, 14));
        pan.setBounds(300, 440, 400, 30);
        add(pan);

        // Aadhar
        JLabel city = new JLabel("Aadhar Number:");
        city.setFont(new Font("Raleway", Font.BOLD, 20));
        city.setBounds(100, 490, 200, 30);
        add(city);

        aadhar = new JTextField();
        aadhar.setFont(new Font("Raleway", Font.BOLD, 14));
        aadhar.setBounds(300, 490, 400, 30);
        add(aadhar);

        // Senior Citizen
        JLabel state = new JLabel("Senior Citizen:");
        state.setFont(new Font("Raleway", Font.BOLD, 20));
        state.setBounds(100, 540, 200, 30);
        add(state);

        syes = new JRadioButton("Yes");
        syes.setBounds(300, 540, 60, 30);
        syes.setBackground(Color.WHITE);
        add(syes);

        sno = new JRadioButton("No");
        sno.setBounds(450, 540, 60, 30);
        sno.setBackground(Color.WHITE);
        add(sno);

        // Group senior citizen
        ButtonGroup seniorGroup = new ButtonGroup();
        seniorGroup.add(syes);
        seniorGroup.add(sno);

        // Existing Account
        JLabel pincode = new JLabel("Existing Account:");
        pincode.setFont(new Font("Raleway", Font.BOLD, 20));
        pincode.setBounds(100, 590, 200, 30);
        add(pincode);

        eyes = new JRadioButton("Yes");
        eno = new JRadioButton("No");

        eyes.setBounds(300, 590, 60, 30);
        eyes.setBackground(Color.WHITE);
        add(eyes);

        eno.setBounds(450, 590, 120, 30);
        eno.setBackground(Color.WHITE);
        add(eno);

        // Group existing account
        ButtonGroup existingGroup = new ButtonGroup();
        existingGroup.add(eyes);
        existingGroup.add(eno);

        // Next button
        next = new JButton("Next");
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.setFont(new Font("Raleway", Font.BOLD, 14));
        next.setBounds(620, 660, 100, 30);
        next.addActionListener(this);
        add(next);

        getContentPane().setBackground(Color.WHITE);
        setSize(850, 800);
        setLocation(350, 10);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {

        String sreligion = (String) religion.getSelectedItem();
        String scategory = (String) category.getSelectedItem();
        String sincome = (String) income.getSelectedItem();
        String seducation = (String) education.getSelectedItem();
        String soccupation = (String) Occupation.getSelectedItem();

        String seniorcitizen = null;
        if (syes.isSelected()) seniorcitizen = "Yes";
        else if (sno.isSelected()) seniorcitizen = "No";

        String existingaccount = null;
        if (eyes.isSelected()) existingaccount = "Yes";
        else if (eno.isSelected()) existingaccount = "No";

        String span = pan.getText();
        String saadhar = aadhar.getText();

        // Validation
        if (span.equals("") || saadhar.equals("") || seniorcitizen == null || existingaccount == null) {
            JOptionPane.showMessageDialog(null, "Please fill all fields properly");
            return;
        }

        try {
            Conn c = new Conn();
            String query = "insert into signuptwo values('" + formno + "','" + sreligion + "','" + scategory + "','" + sincome + "','" + seducation + "','" + soccupation + "','" + span + "','" + saadhar + "','" + seniorcitizen + "','" + existingaccount + "')";
            c.s.executeUpdate(query);

            setVisible(false);
            new SignupThree(formno).setVisible(true);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String args[]) {
        new SignupTwo("");
    }
}
