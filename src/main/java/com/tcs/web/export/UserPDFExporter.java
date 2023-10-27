package com.tcs.web.export;

import java.awt.Color;
import java.io.IOException;
import java.util.List;
 
import javax.servlet.http.HttpServletResponse;
 
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.tcs.web.model.Student;
 
 
public class UserPDFExporter {
	private List<Student> stdlist;
     
    public UserPDFExporter(List<Student> stdlist) {
        this.stdlist = stdlist;
    }
 
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase("Student ID", font));
         
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Name", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Email", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Phone No", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Address", font));
        table.addCell(cell);    
        
     //   cell.setPhrase(new Phrase("Date Of Admission", font));
       // table.addCell(cell); 
    }
     
    private void writeTableData(PdfPTable table) {
        for (Student std : stdlist) {
            table.addCell((String.valueOf(std.getId())));
            table.addCell(std.getName());
            table.addCell(std.getEmail());
            table.addCell(std.getPhoneno());
            table.addCell(std.getAddress());
        }
    }
     
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);
         
        Paragraph p = new Paragraph("List of Students", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
         
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
        table.setSpacingBefore(20);
         
        writeTableHeader(table);
        writeTableData(table);
        document.add(table);
        document.close();
         
    }
}
