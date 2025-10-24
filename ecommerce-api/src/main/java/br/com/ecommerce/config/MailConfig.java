package br.com.ecommerce.config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class MailConfig {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void enviarEmail(String para, String assunto, String texto) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("williamthais147gp4123@gmail.com");
		message.setTo(para);
		message.setSubject(assunto);
		message.setText("Registro do cliente alterado: \n" + texto + "\n\n\n Não responder, mensagem automática do nosso e-commerce");
		
		javaMailSender.send(message);
		
	}

}
