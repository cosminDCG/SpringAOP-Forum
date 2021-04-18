package com.aop.forumspring;

import com.aop.forumspring.forms.register.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;
import java.awt.*;

@SpringBootApplication
public class ForumSpringApplication implements CommandLineRunner {

	@Autowired
	Register registerFrame;

	public static void main(String[] args) {
		ConfigurableApplicationContext context = new SpringApplicationBuilder(ForumSpringApplication.class).headless(false).run(args);
		Register appFrame = context.getBean(Register.class);
	}

	@Override
	public void run(String... args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					registerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					registerFrame.setSize(400, 400);
					registerFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
