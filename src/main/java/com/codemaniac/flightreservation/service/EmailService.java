package com.codemaniac.flightreservation.service;

import org.openapitools.model.MessageDTO;
import reactor.core.publisher.Mono;

public interface EmailService {
   public void sendEmail(MessageDTO messageDTO);
   public String sendSms(MessageDTO messageDTO);
}

