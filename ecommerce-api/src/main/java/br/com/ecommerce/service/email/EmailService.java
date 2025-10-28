package br.com.ecommerce.service.email;

public interface EmailService {
    void sendEmail(String to, String subject, String body);
}