package utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class DataDrivenUtil {
	
	public static Object[][] getData(String filepath,String sheetName) throws EncryptedDocumentException, IOException{
		
		FileInputStream fis = new FileInputStream(filepath);
		
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sheet = wb.getSheet(sheetName);
		int rows = sheet.getPhysicalNumberOfRows();
		int cols = sheet.getRow(0).getPhysicalNumberOfCells();
		
		Object[][] data = new Object[rows-1][cols];
		
		for(int i=1;i<rows;i++) {
			for(int j=0;j<cols;j++) {
				String cell = sheet.getRow(i).getCell(j).getStringCellValue();
				if(cell.equals("blank"))
					data[i-1][j]="";
				else
					data[i-1][j]=cell;
			}
		}
		
		return data;
	}

}
