package com.codemaniac.flightreservation.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.model.MessageProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessagingServiceImpl implements MessagingService {
    @Value("${email.url}")
    private String emailUrl;

    @Value("${sms.url}")
    private String smsUrl;
    private final WebClient webClient;

    @Override
    public Mono<Void> sendEmail(MessageProperties messageProperties) {
       return  webClient.method(HttpMethod.POST)
                .uri(emailUrl)
                .header("Calling-Application","customer-service")
                .bodyValue(messageProperties)
                .retrieve()
                .bodyToMono(Void.class);
    }

    @Override
    public Mono<String> sendSms(MessageProperties messageProperties) {
        return  webClient.method(HttpMethod.POST)
                .uri(smsUrl)
                .header("Calling-Application","customer-service")
                .bodyValue(messageProperties)
                .retrieve()
                .bodyToMono(String.class);
    }
}