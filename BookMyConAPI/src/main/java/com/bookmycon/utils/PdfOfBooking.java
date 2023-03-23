package com.bookmycon.utils;

import com.bookmycon.model.Booking;
import com.lowagie.text.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PdfOfBooking {

    public void generateBooking(List<Booking> bookingList, HttpServletResponse response) throws DocumentException, IOException {

        for (Booking booking : bookingList) {
            System.out.println(booking.getBookingId());
            System.out.println(booking.getBookingAgenda());
            System.out.println(booking.getAduitoriamId().getAuditoriumName());
        }
    }

}
