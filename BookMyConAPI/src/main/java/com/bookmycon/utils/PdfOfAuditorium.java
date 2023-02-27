package com.bookmycon.utils;

import com.bookmycon.model.Auditoriums;

import com.lowagie.text.*;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PdfOfAuditorium {

    public void generateAudi(List<Auditoriums> auditoriumsList, HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTiltle.setSize(20);

        Paragraph paragraph1 = new Paragraph("List of the Auditoriums", fontTiltle);

        paragraph1.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(paragraph1);

        PdfPTable table = new PdfPTable(6);

        table.setWidthPercentage(100f);
        table.setWidths(new int[] {3,3,3,3,3,3});
        table.setSpacingBefore(5);

        PdfPCell cell = new PdfPCell();

        cell.setBackgroundColor(CMYKColor.GRAY);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(CMYKColor.WHITE);

        cell.setPhrase(new Phrase("ID", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Name", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Aminity", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Location", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("type", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Capacity", font));
        table.addCell(cell);

        for (Auditoriums auditoriums: auditoriumsList) {

            table.addCell(String.valueOf(auditoriums.getAuditoriumId()));

            table.addCell(auditoriums.getAuditoriumName());

            table.addCell(auditoriums.getAuditoriumAminity());

            table.addCell(auditoriums.getAuditoriumLocation());

            table.addCell(auditoriums.getAuditoriumType());

            table.addCell(String.valueOf(auditoriums.getAuditoriumCapacity()));
        }

        document.add(table);

        document.close();
    }
}
