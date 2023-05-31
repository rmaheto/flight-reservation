package com.codemaniac.flightreservation.service;

import org.openapitools.model.MessageDTO;
import reactor.core.publisher.Mono;

public interface EmailService {
   public Mono<Void> sendEmail(MessageDTO messageDTO);
   public Mono<String> sendSms(MessageDTO messageDTO);
}

