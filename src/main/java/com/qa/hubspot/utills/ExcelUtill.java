package com.qa.hubspot.utills;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtill {
	
	private static Workbook book;
	private static Sheet sheet;
	
	private static String Test_DATA_SHEET_PATH = "./src/main/java/com/hubspot/qa/testdata/HubSpot_TestData.xlsx";
	
	public static Object[][] getTestData(String sheetname) {
		
		Object data[][] = null;
		
		try {
			FileInputStream ip = new FileInputStream(Test_DATA_SHEET_PATH);
			book = WorkbookFactory.create(ip);
			sheet = book.getSheet(sheetname);
			
			data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			
			for(int  i=0; i<sheet.getLastRowNum();i++) {
				for(int j=0; j<sheet.getRow(0).getLastCellNum(); j++) {
					
					data[i][j] = sheet.getRow(i+1).getCell(j).toString();
				}
			}
						
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return data;	
		
	}
	
	
}
