package Matrix;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class MyMethods {
		
	private static Workbook wb;
	private static Sheet sh;
	private static FileInputStream fis;
	
	public static String[] getSettingsFromMatrix() throws EncryptedDocumentException, IOException {
		
		fis = new FileInputStream("./Matrix.xlsx");
		wb = WorkbookFactory.create(fis);
		sh = wb.getSheet("Sheet1");
		int noOfRows = sh.getLastRowNum();
		String dataArray[] = new String[noOfRows];
		if(noOfRows != 0) {
			for(int i=0; i<=(noOfRows-1); i++) {
				dataArray[i] = sh.getRow(i+1).getCell(0) + "/" + sh.getRow(i+1).getCell(1) + "/" + sh.getRow(i+1).getCell(2)
						+ "/" + sh.getRow(i+1).getCell(3) + "/" + sh.getRow(i+1).getCell(4) + "/" + sh.getRow(i+1).getCell(5);
			}			
		}
		else {
			System.out.println("No Data");
		}
		return dataArray;				
	}
	
}
