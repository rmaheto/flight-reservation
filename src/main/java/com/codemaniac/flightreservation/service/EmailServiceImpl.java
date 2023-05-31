package com.codemaniac.flightreservation.service;

import com.codemaniac.flightreservation.exception.EmailSendException;
import com.codemaniac.flightreservation.exception.SmsSendException;
import org.openapitools.model.MessageDTO;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class EmailServiceImpl implements EmailService {
    @Value("${email.url}")
    private String emailUrl;

    @Value("${sms.url}")
    private String smsUrl;

    private final WebClient webClient;

    public EmailServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Mono<Void> sendEmail(MessageDTO messageDTO) {
        return webClient.post ()
                .uri(emailUrl)
                .header ("Calling-Application","customer-service")
                .bodyValue (messageDTO)
                .retrieve ()
                .bodyToMono (Void.class)
                .onErrorMap (throwable -> new EmailSendException("Failed to send Email",throwable));
    }

    @Override
    public Mono<String> sendSms(MessageDTO messageDTO) {
        return webClient.post ()
                .uri(smsUrl)
                .header ("Calling-Application","customer-service")
                .bodyValue (messageDTO)
                .retrieve ()
                .bodyToMono (String.class)
                .onErrorMap (throwable -> new SmsSendException ("Failed to send sms",throwable));
    }
}