package com.codemaniac.flightreservation.service;

import lombok.extern.slf4j.Slf4j;
import org.openapitools.model.MessageDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {
    @Value("${email.url}")
    private String emailUrl;

    @Value("${sms.url}")
    private String smsUrl;

    private WebClient webClient;

    private RestTemplate restTemplate;

    public EmailServiceImpl(WebClient webClient, RestTemplate restTemplate) {
        this.webClient = webClient;
        this.restTemplate = restTemplate;
    }

    @Override
    public void sendEmail(MessageDTO messageDTO) {
        ResponseEntity<Void> responseEntity = restTemplate.exchange(
                emailUrl,
                HttpMethod.POST,
                createRequestEntity(messageDTO,createHeaders()),
                Void.class
        );
        int statusCode = responseEntity.getStatusCode().value();
        log.warn("Status Code: " + statusCode);
    }

    @Override
    public String sendSms(MessageDTO messageDTO) {
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                smsUrl,
                HttpMethod.POST,
                createRequestEntity(messageDTO,createHeaders()),
                String.class
        );
        // Get the response body and status code
        String responseBody = responseEntity.getBody();
        int statusCode = responseEntity.getStatusCode().value();

        // Handle the response
        log.warn("Response Body: " + responseBody);
        log.warn("Status Code: " + statusCode);
        return responseBody;
    }


    private HttpHeaders createHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Calling-Application","customer-service");
        return headers;
    }

    private HttpEntity<MessageDTO> createRequestEntity(MessageDTO messageDTO, HttpHeaders headers){
        return new HttpEntity<>(messageDTO, headers);
    }
}