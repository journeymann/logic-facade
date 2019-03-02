/**
 * 
 */
package com.research.digest.document.adobe;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Iterator;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.research.digest.document.office.Common;
import org.apache.poi.ss.usermodel.CellType;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

/**
 * @author cgordon
 *
 */
public class PDFMakerUtil {
	
    public void convertXlsToPdf(String xls_name, String pdf_name) throws FileNotFoundException, DocumentException,  IOException{
   	
    		File xlsfile = new File(xls_name);
    		
    		if(!xlsfile.exists()) throw new FileNotFoundException(xls_name);
    		
            FileInputStream input_document = new FileInputStream(xlsfile);
            HSSFWorkbook xls_workbook = new HSSFWorkbook(input_document); 
            HSSFSheet worksheet = xls_workbook.getSheetAt(0); 
            Iterator<Row> rowIterator = worksheet.iterator();
            Document document_xls_pdf = new Document();
            PdfWriter.getInstance(document_xls_pdf, new FileOutputStream(pdf_name));
            
            document_xls_pdf.open();
            PdfPTable table = new PdfPTable(2);
            PdfPCell table_cell;

            while(rowIterator.hasNext()) {
                    Row row = rowIterator.next(); 
                    Iterator<Cell> cellIterator = row.cellIterator();
                            while(cellIterator.hasNext()) {
                                    Cell cell = cellIterator.next(); 
                                    
                                    CellType type = Common.inferCellDataType(cell);
                                    switch(type) { 

	                                    case NUMERIC :

	                                    	 DecimalFormat formatter = new DecimalFormat("#,###,##0.00");
	                                    	
                                             table_cell=new PdfPCell(new Phrase(formatter.format(cell.getStringCellValue())));
                                             table.addCell(table_cell);
                                             
                                            break;

	                                    case BOOLEAN :
	                                    	
                                             table_cell=new PdfPCell(new Phrase(new Boolean(cell.getBooleanCellValue()).toString()));
                                             table.addCell(table_cell);
                                            break;
                                            
                                        default:

                                            table_cell=new PdfPCell(new Phrase(cell.getStringCellValue()));
                                            table.addCell(table_cell);
                                            
                                            break;
                                    }
                            }

            }
            
            document_xls_pdf.add(table);                       
            document_xls_pdf.close();                
            input_document.close(); 
            xls_workbook.close();            
        }
    
    public void convertDocToPdf(String doc_name, String pdf_name) throws FileNotFoundException, DocumentException,  IOException{

		XWPFWordExtractor wx = null;    	
		File docsfile = new File(doc_name);
		if(!docsfile.exists()) throw new FileNotFoundException(doc_name);

		
        try{
             Document document = new Document();        	
	         XWPFDocument doc = new XWPFDocument(POIXMLDocument.openPackage(doc_name));  
	         wx = new XWPFWordExtractor(doc); 
	         PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdf_name));  
	
	         document.open();  
	         writer.setPageEmpty(true);  
	         document.newPage();  
	         writer.setPageEmpty(true);  
	         String text = wx.getText();
	         text=text.replaceAll("\\cM?\r?\n", "");
	         document.add(new Paragraph(text));         
        } 
        catch(Exception e){
             System.out.println("Exception during test");  
             e.printStackTrace();  
        }finally{
        	wx.close();
        }

    }
    
}
