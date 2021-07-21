package com.gr.test;

import com.alibaba.fastjson.JSONObject;
import com.gr.util.MyEmail;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.nio.charset.Charset;

@Component
public class MyEmailListener {
    @Resource
    JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    String from;

    @RabbitListener(queues = "email")
    public void handler(Message message) {
        try {
            byte[] body = message.getBody();
            String str = new String(body, Charset.forName("UTF-8"));
            MyEmail myEmail = JSONObject.parseObject(str, MyEmail.class);
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(from);
            mailMessage.setTo(myEmail.getTo());
            mailMessage.setSubject(myEmail.getSubject());
            mailMessage.setText(myEmail.getText());
            javaMailSender.send(mailMessage);
            System.out.println(str);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(message);
        }
    }
}
