package com.bookmycon.utils;

import com.bookmycon.model.Booking;
import com.lowagie.text.*;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PdfOfBooking {

    Logger logger = Logger.getLogger(PdfOfBooking.class);
    public void generateBooking(List<Booking> bookingList, HttpServletResponse response) throws DocumentException, IOException {

        for (Booking booking : bookingList) {
            //System.out.println(booking.getBookingId());
            logger.log(Level.INFO, booking.getBookingId());

            //System.out.println(booking.getBookingAgenda());
            logger.log(Level.INFO, booking.getBookingAgenda());

            //System.out.println(booking.getAduitoriamId().getAuditoriumName());
            logger.log(Level.INFO, booking.getAduitoriamId().getAuditoriumName());

        }
    }

}
