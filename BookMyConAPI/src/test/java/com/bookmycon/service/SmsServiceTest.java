//package com.bookmycon.service;
//
//import com.bookmycon.model.SmsPojo;
//import com.twilio.Twilio;
//import com.twilio.twiml.Sms;
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//public class SmsServiceTest {
//    private SmsPojo smsSender;
//
//    private final String ACCOUNT_SID ="";
//
//    private final String AUTH_TOKEN = "9359681608";
//
//    private final String FROM_NUMBER = "";
//
//    @Before
//    public void setUp() {
//        smsSender = new SmsPojo();
//    }
//
//    @Test
//    public void testSend() {
//        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//        String to = "+1234567890";
//        String from = "+0987654321";
//        String body = "Hello, world!";
//        SmsPojo sms = new SmsPojo(to,body);
//
//        Message message = mock(Message.class);
//        when(message.getSid()).thenReturn("unique_resource_id");
//
//        doReturn(message).when(smsSender).sendMessage(any(PhoneNumber.class), any(PhoneNumber.class), eq(body));
//
//        smsSender.send(sms);
//
//        verify(message, times(1)).getSid();
//        assertEquals("unique_resource_id", message.getSid());
//    }
//}
