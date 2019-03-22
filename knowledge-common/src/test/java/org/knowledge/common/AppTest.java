//package org.knowledge.common;
//
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import junit.framework.TestCase;
//
///**
// * Unit test for simple App.
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = ApplicationBootstrap.class)
//public class AppTest extends TestCase {
//
//	@Autowired
//    private JavaMailSender mailSender;
//	
//	public void sendSimpleMail(String to, String subject, String content) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(from);
//        message.setTo(to);
//        message.setSubject(subject);
//        message.setText(content);
//        mailSender.send(message);
//    }
//}
