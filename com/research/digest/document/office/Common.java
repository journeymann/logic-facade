/**
 * 
 */
package com.research.digest.document.office;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

/**
 * @author cgordon
 *
 */
public class Common {
	
	enum CellDataType { STRING, BOOLEAN, NUMERIC, CURRENCY, BLANK, INVALID }; //determine expected type from column name predefined application rule....
	
	public static CellType inferCellDataType(Cell cell){
		
		String value = cell.getStringCellValue();
		
		if(value==null) return CellType.ERROR; //null or invalid
		if(value.isEmpty()) return CellType.BLANK; //empty
		if(!String.valueOf(value).matches("^[a-zA-Z0-9]*$")) return CellType.ERROR; // not alphanumeric 
		if(String.valueOf(value).matches("/^-?\\d+\\.?\\d*$/")) return CellType.NUMERIC; // in number
		if(String.valueOf(value.toLowerCase()).matches("true|yes|false|no|1|0|t|f")) return CellType.BOOLEAN; // is a boolean 
		
		return CellType.STRING; //otherwise is a alphanumeric string
	}
	
	

}
