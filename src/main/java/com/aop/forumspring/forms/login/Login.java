/*
 * Created by JFormDesigner on Wed Mar 10 20:16:40 EET 2021
 */

package com.aop.forumspring.forms.login;

import com.aop.forumspring.dto.UserDTO;
import com.aop.forumspring.forms.dashboard.Dashboard;
import com.aop.forumspring.forms.register.Register;
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
public class Login extends JFrame {

    @Autowired
    private UserService userService;

    @Autowired
    private Dashboard dashboardFrame;

    @Autowired
    private Register registerFrame;

    public Login() {
        initComponents();
    }

    private void loginButtonActionPerformed(ActionEvent e) {
        UserDTO userDTO = null;
        try {
            userDTO  = userService.logIn(emailField.getText(), new String(passwordField.getPassword()));
        } catch (RuntimeException ex) {

        }


        if (userDTO != null) {
            dashboardFrame.setUser(userDTO);
            dashboardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            dashboardFrame.setSize(400, 400);
            dashboardFrame.setVisible(true);
            this.dispose();
        }
    }

    private void toRegisterMouseClicked(MouseEvent e) {
        registerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        registerFrame.setVisible(true);
        this.dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        loginTitle = new JLabel();
        emailLabel = new JLabel();
        emailField = new JTextField();
        passwordLabel = new JLabel();
        passwordField = new JPasswordField();
        loginButton = new JButton();
        toRegister = new JLabel();

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
            "[fill]",
            // rows
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]"));

        //---- loginTitle ----
        loginTitle.setText("Login");
        loginTitle.setFont(loginTitle.getFont().deriveFont(loginTitle.getFont().getSize() + 9f));
        contentPane.add(loginTitle, "cell 5 0");

        //---- emailLabel ----
        emailLabel.setText("Email");
        contentPane.add(emailLabel, "cell 4 2");
        contentPane.add(emailField, "cell 5 2 3 1");

        //---- passwordLabel ----
        passwordLabel.setText("Password");
        contentPane.add(passwordLabel, "cell 4 3");
        contentPane.add(passwordField, "cell 5 3 3 1");

        //---- loginButton ----
        loginButton.setText("Login");
        loginButton.addActionListener(e -> loginButtonActionPerformed(e));
        contentPane.add(loginButton, "cell 5 5");

        //---- toRegister ----
        toRegister.setText("I don't have an account");
        toRegister.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                toRegisterMouseClicked(e);
            }
        });
        contentPane.add(toRegister, "cell 5 6");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JLabel loginTitle;
    private JLabel emailLabel;
    private JTextField emailField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel toRegister;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
