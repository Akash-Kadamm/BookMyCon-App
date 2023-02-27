package com.bookmycon.utils;

import com.bookmycon.model.Booking;
import com.lowagie.text.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PdfOfBooking {

    public void generateBooking(List<Booking> bookingList, HttpServletResponse response) throws DocumentException, IOException {

        for (Booking booking:bookingList ) {
            System.out.println(booking.getBookingId());
            System.out.println(booking.getBookingAgenda());
            System.out.println(booking.getAduitoriamId().getAuditoriumName());
        }
    }
//        Document document = new Document(PageSize.A4);
//        PdfWriter.getInstance(document, response.getOutputStream());
//        document.open();
//
//        Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
//        fontTiltle.setSize(20);
//
//        Paragraph paragraph1 = new Paragraph("List of the Auditoriums", fontTiltle);
//
//        paragraph1.setAlignment(Paragraph.ALIGN_CENTER);
//
//        document.add(paragraph1);
//
//        PdfPTable table = new PdfPTable(8);
//
//        table.setWidthPercentage(100f);
//        table.setWidths(new int[] {3,3,3,3,3,3,3,3});
//        table.setSpacingBefore(5);
//
//        PdfPCell cell = new PdfPCell();
//
//        cell.setBackgroundColor(CMYKColor.GRAY);
//        cell.setPadding(5);
//
//        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
//        font.setColor(CMYKColor.WHITE);
//
//        cell.setPhrase(new Phrase("ID", font));
//        table.addCell(cell);
////        cell.setPhrase(new Phrase("Auditorium Name", font));
////        table.addCell(cell);
////        cell.setPhrase(new Phrase("Auditorium Location", font));
////        table.addCell(cell);
////        cell.setPhrase(new Phrase("Booking From", font));
////        table.addCell(cell);
////        cell.setPhrase(new Phrase("Booking To", font));
////        table.addCell(cell);
////        cell.setPhrase(new Phrase("Booking Date From", font));
////        table.addCell(cell);
////        cell.setPhrase(new Phrase("Booking Date to", font));
////        table.addCell(cell);
//        cell.setPhrase(new Phrase("Booking Agenda", font));
//        table.addCell(cell);
//
//        for (Booking booking:bookingList ) {
//
//            table.addCell(String.valueOf(booking.getBookingId()));
//
////            table.addCell((booking.getAduitoriamId().getAuditoriumName()));
////
////            table.addCell(booking.getAduitoriamId().getAuditoriumLocation());
//
////            table.addCell(String.valueOf(booking.getBookingDateFrom()));
////
////            table.addCell(String.valueOf(booking.getBookingDateTo()));
////
////            table.addCell(String.valueOf(booking.getBookingTimeFrom()));
////
////            table.addCell(String.valueOf(booking.getBookingTimeTO()));
//
//            table.addCell(booking.getBookingAgenda());
//        }
//
//        document.add(table);
//
//        document.close();
//    }
}
