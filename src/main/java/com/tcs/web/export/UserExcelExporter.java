package com.tcs.web.export;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.tcs.web.model.Student;

public class UserExcelExporter {
	
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private List<Student> stdlist;
	
	public UserExcelExporter(List<Student> stdlist) {
		super();
		this.stdlist = stdlist;
		workbook= new XSSFWorkbook();
		sheet=workbook.createSheet("students");
	}
	
	
	
	private void writeHeaderRow() {
		Row row =sheet.createRow(0);
		Cell cell =row.createCell(0);
		cell.setCellValue("Student Id");
		
		
		cell =row.createCell(1);
		cell.setCellValue("Name");
		
		cell =row.createCell(2);
		cell.setCellValue("Email");
		
		cell =row.createCell(3);
		cell.setCellValue("Phone no");
		
		cell =row.createCell(4);
		cell.setCellValue("Address");
		
		cell =row.createCell(5);
		cell.setCellValue("Date Of Admission");
		
		
		
	}
	private void writedataRows(){
		int rowcount=1;
		for(Student std:stdlist) {
			Row row =sheet.createRow(rowcount++);
			Cell cell =row.createCell(0);
			cell.setCellValue(std.getId());
			
		    cell =row.createCell(1);
			cell.setCellValue(std.getName());
			

		    cell =row.createCell(2);
			cell.setCellValue(std.getEmail());
			

		    cell =row.createCell(3);
			cell.setCellValue(std.getPhoneno());
			

		    cell =row.createCell(4);
			cell.setCellValue(std.getAddress());
			
			cell =row.createCell(5);
			cell.setCellValue(std.getDate_of_add());
			
			
			
			
		}
	}
	public void export(HttpServletResponse response) throws IOException {
		writeHeaderRow();
		writedataRows();
		ServletOutputStream outputstream=response.getOutputStream();
		workbook.write(outputstream);
		workbook.close();
		outputstream.close();
		
		
		
	}
	

}
