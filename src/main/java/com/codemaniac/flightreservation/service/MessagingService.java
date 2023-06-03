package com.codemaniac.flightreservation.service;

import org.openapitools.model.MessageProperties;
import reactor.core.publisher.Mono;

public interface MessagingService {
   public Mono<Void> sendEmail(MessageProperties messageProperties);
   public Mono<String> sendSms(MessageProperties messageProperties);
}

