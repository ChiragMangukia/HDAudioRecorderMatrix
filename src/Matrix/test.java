package Matrix;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;

public class test extends MyMethods {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		
		for(String temp : MyMethods.getSettingsFromMatrix()) {
			String settings = (temp.split("/")[0]).split("_")[0];	//Spliting the string from /
			String format = (temp.split("/")[0]).split("_")[1];		//Spliting the string from /
			String bitDepth = (temp.split("/")[0]).split("_")[2];	//Spliting the string from /
			String samplingRate = (temp.split("/")[0]).split("_")[3].replaceAll("[^\\d.]", "");	//Spliting the string from / and removing alphabets
			System.out.println(settings + format + bitDepth + samplingRate);
		}

	}

}
