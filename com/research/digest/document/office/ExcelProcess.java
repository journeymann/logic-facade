package com.research.digest.document.office;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.research.digest.document.office.Common.CellDataType;

/**
 * @author cgordon
 * 
 * This Java class is a wrapper solution to address the common problem of reading and writing to/from Microsoft Excel spreadsheet(s) using the Apachi Poi API.
 * 
 * It is intended to be generic in its processing of the internal data present in the spreadsheet.
 * A custom Tuple type was used to address the issue of maintaining the data type of the cells. The code assumes the user (developer) has knowledge of the 
 * spreadsheet's contents and an internal map maintains the meta data type(s) information the columns. Otherwise there is functionality that can infer the data 
 * types.  
 *
 */

public class ExcelProcess {

	private final String XLSX = "xlsx";
	private final String XLS = "xls";
	
	Map<Integer, Object> meta = new HashMap<Integer, Object>(); // meta data about data type of spreadsheet column
	Map<Integer, Object> headers = new HashMap<Integer, Object>(); //column (index/type) key value pair
	Map<Integer, Object> rows = new HashMap<Integer, Object>(); //column (index/value) key value pair
	List<HashMap<Integer, Object>> spreadsheet = new ArrayList<HashMap<Integer, Object>>();
	
	@SuppressWarnings("serial")
	private final Map<String, Object> columnMetaTypes = new HashMap<String, Object>(){
		{
        put("FIRSTNAME", CellDataType.STRING);
        put("LASTNAME", CellDataType.STRING);
        put("SEX", CellDataType.STRING);
        put("AGE", CellDataType.NUMERIC);
        put("COST", CellDataType.CURRENCY);
        put("", CellDataType.BLANK);
        put("SMOKER", CellDataType.BOOLEAN);
		};
	};
	

	
	/**
	 * Reads in an Excel file based of the filename
	 * 
	 * @param filename of spreadsheet to be read/processed
	 * @throws IOException
	 * @throws InvalidFormatException
	 */
	public List<HashMap<Integer, Tuple<String, Object>>> readExcelFormat(String filename) throws FileNotFoundException, InvalidFormatException {

		if(filename==null || filename.isEmpty()) throw new FileNotFoundException();
		
		Iterator<Row> sheets = null;
		List<HashMap<Integer, Tuple<String, Object>>> table = new ArrayList<HashMap<Integer, Tuple<String, Object>>>();
		File file = new File(filename);
		String extension = filename.substring(filename.lastIndexOf("."));
		
		try{
			if(file.exists()){
				
				switch(extension){
				
					case XLSX:
						
						try (XSSFWorkbook xwb =(XSSFWorkbook) WorkbookFactory.create(file)){
							XSSFSheet xsheet = xwb.getSheetAt(0);
							sheets = xsheet.rowIterator();
						}
						
						break;
		
					case XLS:
						
						try (Workbook wb =(Workbook) WorkbookFactory.create(file)){
							Sheet sheet = wb.getSheetAt(0);
							sheets = sheet.rowIterator();
						}
						
						break;
						
					default:	
					
				}
				table = readXLSDataFile(sheets);
			}
		}catch(IOException e){
			System.out.printf("ERROR:: %s \n\n", e.getMessage());
		}		

		return table;		
	}

	/**
	 * Reads in an Excel file based of the filename
	 * 
	 * @param file <File> descriptor of the file to write the data
	 * @param record data structure List<HashMap<Integer, Tuple<String, Object>>> to maintain  the spreadsheet
	 * @throws FileNotFoundException
	 * @throws IOFormatException	 * 
	 */	
	public void exportExcel(File file, List<HashMap<Integer, Tuple<String, Object>>> records) throws FileNotFoundException, IOException {
	     
		//create (generic) workbook
	    Workbook wbx;
	    if (file.getName().endsWith(XLSX)) {
	        wbx = new XSSFWorkbook();
	    } else {
	        wbx = (Workbook)(new XSSFWorkbook());
	    }
	    Sheet sheet = wbx.createSheet();

	    HashMap<Integer, Tuple<String, Object>> labels = records.get(0);
	    
	    // add headers row to spreadsheet
	    org.apache.poi.ss.usermodel.Row header = sheet.createRow(0);
	    for (int j = 0; j < labels.size(); j++) {
	        header.createCell(j).setCellValue(((Tuple<String, Object>)labels.get(j)).toString());
	    }

	    // add cells to spreadsheet
	    for (int i = 1; i < records.size(); i++) {
	        org.apache.poi.ss.usermodel.Row row = sheet.createRow(i);
	        HashMap<Integer, Tuple<String, Object>> record = records.get(i);
	        for (int j = 0; j < record.size(); j++) {
	        	row.createCell(j).setCellValue(((Tuple<String, Object>)labels.get(j)).toString());
	        }
	    }

	    // write output to data spreadsheet
	    FileOutputStream fileOut = new FileOutputStream(file);
	    
	    wbx.write(fileOut);
	    wbx.close();
	    fileOut.close();
	}
	
	private List<HashMap<Integer, Tuple<String, Object>>> readXLSDataFile(Iterator<Row> rows){
		
		List<HashMap<Integer, Tuple<String, Object>>> output = new ArrayList<HashMap<Integer, Tuple<String, Object>>>();
		Map<Integer, Object> meta = new HashMap<Integer, Object>();
		
		if(rows.hasNext()){
			HSSFRow tmp = (HSSFRow) rows.next();
			meta = setMetaTypes(readRowRecord(tmp));
			output.add(readRowRecord(tmp, meta));			
		}
		
        while (rows.hasNext()) {
                      
           	output.add(readRowRecord((HSSFRow) rows.next(), meta));
        }
        
        return output;
	}
	
	private HashMap<Integer, Object> readRowRecord(HSSFRow row){
		
		HashMap<Integer, Object> output = new HashMap<Integer, Object>();
		
        Iterator<Cell> cells = row.cellIterator();
        while (cells.hasNext()) {
            HSSFCell cell = (HSSFCell) cells.next();
            
            output.put( cell.getColumnIndex(), cell.getRichStringCellValue());
        }

		return output;
	}

	private HashMap<Integer, Tuple<String, Object>> readRowRecord(HSSFRow row, Map<Integer, Object> meta){
		
		HashMap<Integer, Tuple<String, Object>> output = new HashMap<Integer, Tuple<String, Object>>();
		
        Iterator<Cell> cells = row.cellIterator();
        while (cells.hasNext()) {
            HSSFCell cell = (HSSFCell) cells.next();
            
            Integer index = cell.getColumnIndex();
            String value = cell.getRichStringCellValue().toString();
            output.put( index, new Tuple<String,Object>(value,meta.get(index)));
        }

		return output;
	}

	private HashMap<Integer, Object> setMetaTypes(Map<Integer, Object> header){
		
		Iterator<Integer> columns = header.keySet().iterator();
		
		HashMap<Integer, Object> output = new HashMap<Integer, Object>();
		
        while (columns.hasNext()) {
        	Integer key = columns.next();
            String label = (String)header.get(columns.next());
            
            output.put( key, columnMetaTypes.get(label));
        }
		
		return output;
	}
	
}
