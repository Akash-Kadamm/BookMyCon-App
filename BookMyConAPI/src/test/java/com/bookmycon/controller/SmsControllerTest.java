package com.bookmycon.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Timestamp;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class SmsControllerTest {

//    @Test
//    public void getTimeStampReturnsCurrentTime() {
//        String expectedFormat = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}";
//        String timeStamp = String.valueOf(new Timestamp(2020 ,2,25, 4,05,05,2000).getTime());
//        assertTrue(timeStamp.matches(expectedFormat), "Invalid time stamp format");
//    }
//
//    @Test
//    public void testGetTimeStampAll() {
//        String expectedPattern = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}";
//        String actualTimeStamp = String.valueOf(new Timestamp(2020 ,2,25, 4,05,05,2000).getTime());
//        assertTrue(actualTimeStamp.matches(expectedPattern));
//    }
//
//    @Test
//    public void testGetTimeStamp() {
//        LocalDateTime now = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        String expected = formatter.format(now);
//
//        Timestamp timeStamp = new Timestamp(2020 ,2,25, 4,05,05,2000);
//        String actual = String.valueOf(timeStamp.getTime());
//        assertEquals(expected, actual);
//    }

//    @Test
//    public void testGetTimeStampWith() {
//        // Setup
//        Timestamp myClass = new Timestamp();
//
//        // Test
//        String timestamp = String.valueOf(myClass.getTime());
//
//        // Verify
//        assertNotNull(timestamp);
//        assertEquals(timestamp.length(), 19);
//        assertTrue(timestamp.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}"));
//    }

    @Test
    public void testGetTimeStampqwer() {
        // Create a fixed date time
        LocalDateTime dateTime = LocalDateTime.of(2023, 5, 3, 10, 30, 0);

        // Set the clock to a fixed date time
        Clock clock = Clock.fixed(dateTime.atZone(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());

        // Use the fixed clock
        String expectedTimeStamp = "2023-05-03 10:30:00:00";

        String actualTimeStamp = String.valueOf(new Timestamp(2023, 5, 3, 10, 30, 0,0).getTime());

        assertEquals(expectedTimeStamp, actualTimeStamp);
    }

}
