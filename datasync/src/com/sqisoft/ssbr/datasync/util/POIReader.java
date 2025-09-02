package com.sqisoft.ssbr.datasync.util;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.mitzz.frame.vo.ValueObject;

public class POIReader {
	protected Logger logger = Logger.getLogger(getClass());
	
	public POIReader() {
		System.out.println("POIReader");
	}
	
	public ValueObject reader(String name) {
		
		logger.debug("reader() START");
		System.out.println("reader() START");
		
		String [][] data = null;
		Workbook workbook = null;
		Sheet sheet = null;
		Row row = null;
		Cell cell = null;
		String value;
		ValueObject result = new ValueObject();
		
		try {
			File file = new File(name);
			workbook = WorkbookFactory.create(file);
			System.out.println("Workbook Read START");
			logger.debug("Workbook Read START");
			
			int i =0;
			while(i < 2){
				sheet = workbook.getSheetAt(i);
				int cells = sheet.getRow(0).getLastCellNum();
				data = new String[1][cells];
				ValueObject rowvo = new ValueObject();
				
				for(int idx = 0; idx < sheet.getPhysicalNumberOfRows(); idx ++){
					row = sheet.getRow(idx);
					ValueObject vo = new ValueObject();
					
					for(int cellidx = 0; cellidx < cells; cellidx++){
						cell = row.getCell(cellidx);
						
						if(idx == 0){
							data[0][cellidx] = cell.getStringCellValue()+"";
						}
						else{
							if (cell == null) {
								value = "";
							}
							else{
								if(cell.getCellTypeEnum() != null && cell.getCellTypeEnum().toString() == "NUMERIC"){
									cell.setCellType(CellType.STRING);
									value = cell.toString();
								}
								else{
									value = cell.toString();
								}
							}
							vo.set(data[0][cellidx], value);
						}
					}
					if(idx != 0){
						rowvo.add(vo);
					}
				}
				i++;
				result.add(rowvo);
			}
			System.out.println("Workbook Read END");
			logger.debug("Workbook Read END");
		}catch(Exception e){
			System.out.println("ExcelReadPoi error : " + e.getMessage());
			logger.error("ExcelReadPoi error : " + e.getMessage());
		}
		
		logger.debug("reader() END");
		System.out.println("reader() END");
		return result;
	}
}
