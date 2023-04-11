package com.bookmycon.utils;

import com.bookmycon.model.Guest;
import com.lowagie.text.*;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GuestPass {

    public void generatePass(Guest guest, HttpServletResponse response) throws DocumentException, IOException {

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTiltle.setSize(30);

        PdfPTable table1 = new PdfPTable(1);
        table1.setSpacingBefore(10);
        Image image = Image.getInstance("E://Manager/bookmycon-logo.png");
//        Image myImage = Image.getInstance("E://uploads//"+guest.getThumbnail());
        image.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(image);

        Paragraph paragraph1 = new Paragraph("Guest Pass", fontTiltle);

        paragraph1.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(paragraph1);

        PdfPTable table = new PdfPTable(2);
        table.setSpacingBefore(10);

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String currentDateTime = dateFormat.format(new Date());

        table.addCell("\r" +
                "Guest ID "+ " : " + String.valueOf(guest.getGuestId()) + "\r\r" +
                "Name " + " : " + String.valueOf(guest.getGuestName()) + "\r\r" +
                "Company Name " + " : " + String.valueOf(guest.getGuestCompany()) + "\r\r" +
                "Mobile No. " + " : " + String.valueOf(guest.getGuestMobileNo()) + "\r\r" +
                "Date" + " : " + currentDateTime
        );
        
        Image myImage = Image.getInstance("E://uploads//"+guest.getThumbnail());
        table.addCell(myImage);
        document.add(table);

        Font fontTiltle1 = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTiltle1.setSize(15);
        Paragraph paragraph2 = new Paragraph( " The only purpose of this pass is for guest visit and it is valid for one day.", fontTiltle1 );
        paragraph2.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(paragraph2);

        document.close();
        System.out.println("Pass created successfully..");

    }
}

