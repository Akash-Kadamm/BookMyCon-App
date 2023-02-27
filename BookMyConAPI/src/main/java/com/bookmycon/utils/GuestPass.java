package com.bookmycon.utils;

import com.bookmycon.model.User;
import com.lowagie.text.*;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GuestPass {

    public void generatePass(User user, HttpServletResponse response) throws DocumentException, IOException {

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTiltle.setSize(30);

        Paragraph paragraph1 = new Paragraph("Guest Pass", fontTiltle);

        paragraph1.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(paragraph1);

        PdfPTable table = new PdfPTable(2);
        table.setSpacingBefore(10);

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String currentDateTime = dateFormat.format(new Date());

        table.addCell("\r" +
                "User ID "+ " : " + String.valueOf(user.getUserId()) + "\r\r" +
                "Name " + " : " + String.valueOf(user.getUserName()) + "\r\r" +
                "Contact " + " : " + String.valueOf(user.getUserContact()) + "\r\r" +
                "Date" + " : " + currentDateTime
        );

//        FileInputStream in = new FileInputStream("E:/Manager/Images/logo.jpg");
//          final String IMG1 = "E:/Manager/Images/logo.jsp";
//        Image img1 = Image.getInstance(IMG1);
//        Image img1 = Image.getInstance(String.valueOf(in));
//        table.addCell(img1);
//          table.addCell("\r" + String.valueOf(dataDir) + "logo.jsp");

        String dataDir = "E:/Manager/Images/";
        table.addCell("\r\r" +  dataDir + "logo.jpg");

        document.add(table);

        Font fontTiltle1 = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTiltle1.setSize(15);
        Paragraph paragraph2 = new Paragraph( " The only purpose of this pass is for guest visit and it is valid for one day.", fontTiltle1 );
        paragraph2.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(paragraph2);

        document.close();
        System.out.println("Table created successfully..");

    }
}

