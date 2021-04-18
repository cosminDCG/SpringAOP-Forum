/*
 * Created by JFormDesigner on Tue Mar 09 23:00:10 EET 2021
 */

package com.aop.forumspring.forms.register;

import com.aop.forumspring.dto.UserDTO;
import com.aop.forumspring.forms.login.Login;
import com.aop.forumspring.service.UserService;
import net.miginfocom.swing.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author unknown
 */
@Component
public class Register extends JFrame {

    @Autowired
    private UserService userService;

    @Autowired
    private Login loginFrame;

    public Register() {
        initComponents();
    }

    private void registerButtonActionPerformed(ActionEvent e) {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(firstNameField.getText());
        userDTO.setLastName(lastNameField.getText());
        userDTO.setEmail(emailField.getText());
        userDTO.setPassword(new String(passwordField.getPassword()));

        String result = null;
        try {
            result = userService.registerUser(userDTO);
        } catch (RuntimeException exception) {

        }

        if (result != null && result.equals("Success")) {
            loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            loginFrame.setVisible(true);
            this.dispose();
        }
    }

    private void toLoginMouseClicked(MouseEvent e) {
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setVisible(true);
        this.dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        registerTitle = new JLabel();
        firstNameLabel = new JLabel();
        firstNameField = new JTextField();
        lastNameLabel = new JLabel();
        lastNameField = new JTextField();
        emailLabel = new JLabel();
        emailField = new JTextField();
        passwordLabel = new JLabel();
        passwordField = new JPasswordField();
        registerButton = new JButton();
        toLogin = new JLabel();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]",
            // rows
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]"));

        //---- registerTitle ----
        registerTitle.setText("Register");
        registerTitle.setFont(registerTitle.getFont().deriveFont(registerTitle.getFont().getSize() + 9f));
        contentPane.add(registerTitle, "cell 4 0 2 1");

        //---- firstNameLabel ----
        firstNameLabel.setText("First Name");
        contentPane.add(firstNameLabel, "cell 3 2");
        contentPane.add(firstNameField, "cell 5 2 6 1");

        //---- lastNameLabel ----
        lastNameLabel.setText("Last Name");
        contentPane.add(lastNameLabel, "cell 3 3");
        contentPane.add(lastNameField, "cell 5 3 6 1");

        //---- emailLabel ----
        emailLabel.setText("Email");
        contentPane.add(emailLabel, "cell 3 4");
        contentPane.add(emailField, "cell 5 4 6 1");

        //---- passwordLabel ----
        passwordLabel.setText("Password");
        contentPane.add(passwordLabel, "cell 3 5");
        contentPane.add(passwordField, "cell 5 5 6 1");

        //---- registerButton ----
        registerButton.setText("Register");
        registerButton.addActionListener(e -> registerButtonActionPerformed(e));
        contentPane.add(registerButton, "cell 4 7 4 1");

        //---- toLogin ----
        toLogin.setText("Already have an account");
        toLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                toLoginMouseClicked(e);
            }
        });
        contentPane.add(toLogin, "cell 4 8 4 1");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JLabel registerTitle;
    private JLabel firstNameLabel;
    private JTextField firstNameField;
    private JLabel lastNameLabel;
    private JTextField lastNameField;
    private JLabel emailLabel;
    private JTextField emailField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton registerButton;
    private JLabel toLogin;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
