package com.tcs.web.export;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.tcs.web.StudentService.StudentService;
import com.tcs.web.model.Student;
import com.tcs.web.repo.studentRepo;

public class AckReceipt {
	
	private String str;
	private Student std;
	public int stid;

 
 public  AckReceipt(Student std)
 {
	 
	 this.std=std;
	 
	 System.out.println("Acknowledgement no from controller "+std.getId());
	 
	 stid=std.getId();
	
 }
    
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
   
        cell.setBackgroundColor(Color.magenta);
        cell.setPadding(5);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase("Your acknowledge no is  :", font));              
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Name", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Course",font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Date",font));
        table.addCell(cell);
        
                    
    }

	@Autowired
	studentRepo studentrepo2;
   private void writeTableData(PdfPTable table) {
       
	        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	        LocalDateTime dt = LocalDateTime.now();
	        System.out.println(dtf.format(dt));
	        String date=dt.toString();
	        table.addCell((String.valueOf(std.getId())));
            System.out.println("Student name  in table data of ack receipt class "+std.getName());
            table.addCell(std.getName());	
            table.addCell(std.getCourse());	
            dt=std.getDate_of_add();
           
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = dt.format(outputFormatter);
            String dateOfAddmsn=formattedDateTime.toString();
            table.addCell(dateOfAddmsn);
            //studentrepo2.
           
        }
    
       
     
        @Autowired 
        StudentService student;
        
		   Path p;
		   public Path passFileName(Path path) {
		   System.out.println("File name in Ack Receipt class "+path);
		   p=path;
		   return path; }
		  
		
		 
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        Rectangle layout = new Rectangle(PageSize.A4);
        layout.setBorderColor(Color.BLACK);
        layout.setBorderWidth(6);
        layout.setBorder(Rectangle.BOX);
        document.add(layout);
        String imagePath = "E:\\temp\\"; // changed for prod deployment
        //String  imagePath="/usr/uploaded_files/";
        String finalPath=null;
        File folder = new File(imagePath);
        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();

            if (files != null && files.length > 0) {
                // Sort files by modification time in descending order
                Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());

                // Get the latest file name
                String latestFileName = files[0].getName();
            finalPath = imagePath + latestFileName;
                System.out.println("Latest file in the folder: " + latestFileName);
            } else {
                System.err.println("The folder is empty.");
            }
        } else {
            System.err.println("The folder does not exist or is not a directory.");
        } 
        //Logo Path
       //Image img = Image.getInstance("C:\\Users\\aminul\\Documents\\workspace-spring-tool-suite-4-4.13.0.RELEASE\\SpringBootTest\\src\\main\\resources\\static\\assets\\images\\logo1.jpg");
       Image img = Image.getInstance("E:\\STS\\SpringBootTest\\src\\main\\resources\\static\\assets\\images\\logo1.jpg");
       
      //  Image img = Image.getInstance("/opt/logo.jpg");
       
       if(finalPath.endsWith(".jpg") || finalPath.endsWith(".png") )
       {
        Image image = Image.getInstance(finalPath);
        image.scaleToFit(100, 100);
        img.scaleAbsolute(80f, 80f);
        image.scaleAbsolute(80f, 80f);
        image.setAbsolutePosition(430f, 730f);
        document.add(img);
        document.add(image);
        
       }
       
       else
       {
    	   img.scaleAbsolute(80f, 80f);
    	   document.add(img);
       }
      
  
  
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        Font font1 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        Font font2 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        Font font3 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font1.setSize(22);
        font2.setSize(14);
        font3.setSize(14);
        font1.setColor(Color.RED);
        font.setColor(Color.DARK_GRAY);
        font2.setColor(Color.DARK_GRAY);
        font3.setColor(Color.RED);
            
        Paragraph p = new Paragraph("Assam Computer Tech Institute", font1);
        Paragraph p1 = new Paragraph("Acknowledgement Receipt",font);
        Paragraph p2 = new Paragraph("This is a system generated receipt, hence no signature required", font2);
        Paragraph p3 = new Paragraph("kindly visit office with this receipt within 7 days", font3);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        p1.setAlignment(Paragraph.ALIGN_CENTER);
        p2.setAlignment(Paragraph.ALIGN_CENTER);  
        p3.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p);
        document.add(p1);
        document.add(p2);
        document.add(p3);
        document.addCreationDate();
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {2.5f,1.5f,1f,3f});
        table.setSpacingBefore(150f);
        System.out.println("Ack no in export function() "+str);
        writeTableHeader(table);
        writeTableData(table);           
        document.add(table);
        document.close();         
    }

    //for duplicate receipt 
    
    public void export1(HttpServletResponse response)/// throws DocumentException, IOException 
    {
       
      try {	
    	Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        Rectangle layout = new Rectangle(PageSize.A4);
        layout.setBorderColor(Color.BLACK);
        layout.setBorderWidth(6);
        layout.setBorder(Rectangle.BOX);
        document.add(layout);
       // String imagePath = "E:\\temp\\";
        String  imagePath="/usr/uploaded_files/";
        String finalPath=null;
        File folder = new File(imagePath);
        List<File> imageFiles = new ArrayList<>();
        File[] files = folder.listFiles();
         if(files!=null)
         {
        	 for (File file : files) {
                
                     imageFiles.add(file);
                 }
             
         }
           
       	 String sid=Integer.toString(stid);
       	 sid= sid+ "_";
      // Search for the image based on the criteria (in this case, by filename)
        /// for (File imageFile : imageFiles) {
        	 System.out.println("Inside image search :"+sid);
        	 
            /* if (imageFile.getName().startsWith(sid))
            		 {
            	 System.out.println("Found in student ID: " + imageFile.getAbsolutePath());
                 break; // If you found the image, you can break out of the loop
            		 }
            	*/
                      
           
        //Logo Path
       // Image img = Image.getInstance(" Image img = Image.getInstance(\"E:\\\\STS\\\\SpringBootTest\\\\src\\\\main\\\\resources\\\\static\\\\assets\\\\images\\\\logo1.jpg\");");
        Image img = Image.getInstance("E:\\STS\\SpringBootTest\\src\\main\\resources\\static\\assets\\images\\logo1.jpg");
        
       // Image img = Image.getInstance("/opt/logo.jpg");
           
     
       /// Image image = Image.getInstance(imageFile.getName());
       // image.scaleToFit(100, 100);
        img.scaleAbsolute(80f, 80f);
       // image.scaleAbsolute(80f, 80f);
       // image.setAbsolutePosition(430f, 730f);
        document.add(img);
      //  document.add(image);
        
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        Font font1 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        Font font2 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        Font font3 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font1.setSize(22);
        font2.setSize(14);
        font3.setSize(14);
        font1.setColor(Color.RED);
        font.setColor(Color.DARK_GRAY);
        font2.setColor(Color.DARK_GRAY);
        font3.setColor(Color.RED);
            
        Paragraph p = new Paragraph("Assam Computer Tech Institute", font1);
        Paragraph p1 = new Paragraph("Duplicate Acknowledgement Receipt",font);
        Paragraph p2 = new Paragraph("This is a system generated receipt, hence no signature required", font2);
        Paragraph p3 = new Paragraph("kindly visit office with this receipt within 7 days", font3);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        p1.setAlignment(Paragraph.ALIGN_CENTER);
        p2.setAlignment(Paragraph.ALIGN_CENTER);  
        p3.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p);
        document.add(p1);
        document.add(p2);
        document.add(p3);
        document.addCreationDate();
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {2.5f,1.5f,1f,3f});
        table.setSpacingBefore(150f);
        System.out.println("Ack no in export function() "+str);
        writeTableHeader(table);
        writeTableData(table);           
        document.add(table);
        document.close();
         
    
    }
    
    catch(Exception e)
      {
    	e.printStackTrace();
      }

  finally
  {
	  
  }

}
}