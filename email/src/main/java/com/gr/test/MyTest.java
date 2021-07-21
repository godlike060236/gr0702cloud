package com.gr.test;

import com.gr.EmailApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EmailApp.class)
public class MyTest {
    @Resource
    JavaMailSender javaMailSender;

    @Test
    public void handler() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText("正文内容");
        message.setSubject("邮件的主题");
        message.setTo("547459120@qq.com");
        message.setFrom("dico935@163.com");
        javaMailSender.send(message);
        System.out.println("success");
    }
}
