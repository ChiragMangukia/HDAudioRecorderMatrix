package Matrix;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

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
	
	public static String pullRecordedFiles() throws IOException {

		//Create a Folder		
		File file = new File("C:\\AudioRecorderMatrix");
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }
		
        Runtime runtime = Runtime.getRuntime();
		String command = "adb pull /sdcard/AudioRecorder C:\\AudioRecorderMatrix";
		Process process = runtime.exec(command);

		BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));

		BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

		// Read the output from the command
		while ((stdInput.readLine()) != null) {
		    return "Success";
		}

		// Read any errors from the attempted command
		while ((stdError.readLine()) != null) {
		    return "Error";
		}
		return "";
	}
	
}
