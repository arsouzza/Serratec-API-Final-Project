package br.com.ecommerce.service.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmail(String to, String subject, String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("rodrigompf15@gmail.com");
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            
            javaMailSender.send(message);
        } catch (Exception e) {
            System.err.println("Erro ao tentar enviar e-mail: " + e.getMessage());
        }
    }
}