/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentswing;

import entity.Student;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.StudentModel;

/**
 *
 * @author ueda
 */
public class StudentSwing extends JFrame {
    
    private JLabel id, name, email, phone, className, rollNumber, lblIdMessage, lblNameMessage, lblEmailMessage, lblPhoneMessage, lblClassNameMessage, lblRollNumberMessage;
    private JTextField txtId, txtName, txtClassName, txtRollNumber, txtEmail, txtPhone;
    private JPanel panel;
    private JButton accept, reset;
    
    public StudentSwing() {
        super("Student Information");
        this.setSize(1000, 800);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        
        panel = new JPanel();
        panel.setBounds(100, 0, 800, 800);
        panel.setBackground(Color.lightGray);
        panel.setLayout(null);
        
        id = new JLabel("ID");
        name = new JLabel("Name");
        email = new JLabel("Email");
        phone = new JLabel("Phone");
        className = new JLabel("Class name");
        rollNumber = new JLabel("Roll number");
        
        txtId = new JTextField();
        txtName = new JTextField();
        txtClassName = new JTextField();
        txtRollNumber = new JTextField();
        txtEmail = new JTextField();
        txtPhone = new JTextField();
        
        accept = new JButton("Accept");
        reset = new JButton("Reset");
        
        id.setBounds(150, 100, 100, 40);
        txtId.setBounds(255, 100, 300, 40);
        
        lblIdMessage = new JLabel();
        lblIdMessage.setBounds(560, 100, 200, 40);
        
        name.setBounds(150, 150, 100, 40);
        txtName.setBounds(255, 150, 300, 40);
        
        lblNameMessage = new JLabel();
        lblNameMessage.setBounds(560, 150, 200, 40);
        
        email.setBounds(150, 200, 100, 40);
        txtEmail.setBounds(255, 200, 300, 40);
        
        lblEmailMessage = new JLabel();
        lblEmailMessage.setBounds(560, 200, 200, 40);
        
        phone.setBounds(150, 250, 100, 40);
        txtPhone.setBounds(255, 250, 300, 40);
        
        lblPhoneMessage = new JLabel();
        lblPhoneMessage.setBounds(560, 250, 200, 40);
        
        className.setBounds(150, 300, 100, 40);
        txtClassName.setBounds(255, 300, 300, 40);
        
        lblClassNameMessage = new JLabel();
        lblClassNameMessage.setBounds(560, 300, 200, 40);
        
        rollNumber.setBounds(150, 350, 100, 40);
        txtRollNumber.setBounds(255, 350, 300, 40);
        
        lblRollNumberMessage = new JLabel();
        lblRollNumberMessage.setBounds(560, 350, 200, 40);
        
        accept.setBounds(255, 400, 140, 40);
        reset.setBounds(415, 400, 140, 40);
        
        reset.addActionListener(new ResetHandle());
        accept.addActionListener(new ButtonHandle());
        
        panel.add(id);
        panel.add(txtId);
        panel.add(name);
        panel.add(txtName);
        panel.add(email);
        panel.add(txtEmail);
        panel.add(phone);
        panel.add(txtPhone);
        panel.add(className);
        panel.add(txtClassName);
        panel.add(rollNumber);
        panel.add(txtRollNumber);
        panel.add(accept);
        panel.add(reset);
        panel.add(lblIdMessage);
        panel.add(lblNameMessage);
        panel.add(lblEmailMessage);
        panel.add(lblPhoneMessage);
        panel.add(lblClassNameMessage);
        panel.add(lblRollNumberMessage);
        this.add(panel);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
    public static void main(String[] args) {
        StudentSwing stu = new StudentSwing();
    }
    
    class ButtonHandle implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            StudentModel stuModel = new StudentModel();
            Student stu = new Student();
            try {                
                stu.setId(Integer.parseInt(txtId.getText()));
            } catch (NumberFormatException ex) {
                System.err.println(ex);
            }
            
            
            stu.setName(txtName.getText());
            stu.setEmail(txtEmail.getText());
            stu.setPhone(txtPhone.getText());
            stu.setClassName(txtClassName.getText());
            stu.setRollNumber(txtRollNumber.getText());
            
            HashMap<String, String> errors = new FormHandle().validateLogin(stu);
            if (errors.size()!=0) {
                showError(errors);
            } else {
                stuModel.insert(stu);
                JOptionPane.showConfirmDialog(null, "Are you sure?\nID: " + stu.getId() + "\nName: " + stu.getName()
                        + "\nEmail: " + stu.getEmail() + "\nPhone: " + stu.getPhone() + "\nClass name: " + stu.getClassName()
                        + "\nRoll number: " + stu.getRollNumber());
                JOptionPane.showMessageDialog(null, "Login success!\n- Welcome " + stu.getName() + " to my world -");
            }
        }
        
    }
    
    class ResetHandle implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            txtId.setText("");
            txtName.setText("");
            txtEmail.setText("");
            txtPhone.setText("");
            txtClassName.setText("");
            txtRollNumber.setText("");
        }
        
    }
    
    private void showError(HashMap<String, String> errors) {
        if (errors.containsKey("txtId")) {
            lblIdMessage.setForeground(Color.red);
            lblIdMessage.setText(errors.get("txtId"));
        } else {
            lblIdMessage.setForeground(Color.green);
            lblIdMessage.setText("OK");
        }
        
        if (errors.containsKey("txtName")) {
            lblNameMessage.setForeground(Color.red);
            lblNameMessage.setText(errors.get("txtName"));
        } else {
            lblNameMessage.setForeground(Color.green);
            lblNameMessage.setText("OK");
        }
        
        if (errors.containsKey("txtEmail")) {
            lblEmailMessage.setForeground(Color.red);
            lblEmailMessage.setText(errors.get("txtEmail"));
        } else {
            lblEmailMessage.setForeground(Color.green);
            lblEmailMessage.setText("OK");
        }
        
        if (errors.containsKey("txtPhone")) {
            lblPhoneMessage.setForeground(Color.red);
            lblPhoneMessage.setText(errors.get("txtPhone"));
        } else {
            lblPhoneMessage.setForeground(Color.green);
            lblPhoneMessage.setText("OK");
        }
        
        if (errors.containsKey("txtClassName")) {
            lblClassNameMessage.setForeground(Color.red);
            lblClassNameMessage.setText(errors.get("txtClassName"));
        } else {
            lblClassNameMessage.setForeground(Color.green);
            lblClassNameMessage.setText("OK");
        }
        
        if (errors.containsKey("txtRollNumber")) {
            lblRollNumberMessage.setForeground(Color.red);
            lblRollNumberMessage.setText(errors.get("txtRollNumber"));
        } else {
            lblRollNumberMessage.setForeground(Color.green);
            lblRollNumberMessage.setText("OK");
        }
        
    }
    
}
