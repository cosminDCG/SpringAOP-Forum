/*
 * Created by JFormDesigner on Wed Mar 10 23:13:58 EET 2021
 */

package com.aop.forumspring.forms.dashboard;

import com.aop.forumspring.dto.PostDTO;
import com.aop.forumspring.dto.UserDTO;
import com.aop.forumspring.forms.login.Login;
import com.aop.forumspring.forms.post.Post;
import com.aop.forumspring.forms.view.ViewPost;
import com.aop.forumspring.service.PostService;
import net.miginfocom.swing.MigLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * @author unknown
 */
@Component
public class Dashboard extends JFrame {

    @Autowired
    private PostService postService;

    @Autowired
    private Login loginFrame;

    @Autowired
    private Post postFrame;

    @Autowired
    private ViewPost viewPostFrame;

    private UserDTO user;
    private List<PostDTO> postDTOList;

    public Dashboard () {
    }

    public Dashboard(UserDTO userDTO) {

        this.user = userDTO;
        this.postDTOList = postService.getAllPosts();
        addPosts();
        nameLabel.setText(userDTO.getFirstName() + " " + userDTO.getLastName());
    }

    public void setUser(UserDTO userDTO) {
        getContentPane().removeAll();
        initComponents();
        this.user = userDTO;
        this.postDTOList = postService.getAllPosts();
        addPosts();
        nameLabel.setText(user.getFirstName() + " " + user.getLastName());
    }


    private void addPosts() {
        int postIndex = 2;
        for (PostDTO postDTO : postDTOList) {
            JTextArea jta = new JTextArea(postDTO.getPostText());
            jta.setEditable(false);
            jta.setRows(3);
            jta.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    viewPostMouseClicked(e, postDTO);
                }
            });
            JScrollPane jScrollPane = new JScrollPane();
            jScrollPane.setViewportView(jta);
            getContentPane().add(jScrollPane, "cell 1 " + postIndex + " 25 4");
            postIndex += 5;
        }
    }

    private void viewPostMouseClicked(MouseEvent e, PostDTO postDTO) {

        viewPostFrame.setUserAndPost(user, postDTO);
        viewPostFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        viewPostFrame.setSize(600, 400);
        viewPostFrame.setVisible(true);
        this.dispose();
    }

    private void newPostLabelMouseClicked(MouseEvent e) {
        postFrame.setUser(user);
        postFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        postFrame.setSize(600, 400);
        postFrame.setVisible(true);
        this.dispose();
    }

    private void logoutLabelMouseClicked(MouseEvent e) {
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setVisible(true);
        this.dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        nameLabel = new JLabel();
        newPostLabel = new JLabel();
        logoutLabel = new JLabel();

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
            "[]"));
        contentPane.add(nameLabel, "cell 0 0 3 1");

        //---- newPostLabel ----
        newPostLabel.setText("Add new post");
        newPostLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                newPostLabelMouseClicked(e);
            }
        });
        contentPane.add(newPostLabel, "cell 17 0 3 1");

        //---- logoutLabel ----
        logoutLabel.setText("Logout");
        logoutLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                logoutLabelMouseClicked(e);
            }
        });
        contentPane.add(logoutLabel, "cell 20 0");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JLabel nameLabel;
    private JLabel newPostLabel;
    private JLabel logoutLabel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
