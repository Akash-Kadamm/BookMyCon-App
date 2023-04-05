package com.bookmycon.service;

import com.bookmycon.model.SmsPojo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


@Component
public class SmsService {


    private final String ACCOUNT_SID ="AC3edb50e58da775d43f54e0ebd85939e0";

    private final String AUTH_TOKEN = "d541d8a0371def813e453985703eff0d";

    private final String FROM_NUMBER = "+14406412988";

    public void send(SmsPojo sms) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber(sms.getTo()), new PhoneNumber(FROM_NUMBER), sms.getMessage())
                .create();
        System.out.println("here is my id:"+message.getSid());// Unique resource ID created to manage this transaction

    }

    public void receive(MultiValueMap<String, String> smscallback) {

    }

}

