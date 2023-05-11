package com.bookmycon.service;

import com.bookmycon.model.SmsPojo;
import com.twilio.Twilio;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import org.springframework.util.LinkedMultiValueMap;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.util.MultiValueMap;

public class SmsServiceTest {

    private SmsService smsService;
    private Twilio twilio;
    SmsPojo sms;

    private final String ACCOUNT_SID ="AC3edb50e58da775d43f54e0ebd85939e0";

    private final String AUTH_TOKEN = "15bfcb98febc1c41c82384f932b79be2";

    private final String FROM_NUMBER = "+14406412988";

    @Before
    public void setUp() {
        twilio = mock(Twilio.class);
    }


}
