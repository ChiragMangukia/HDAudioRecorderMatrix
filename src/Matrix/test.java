package Matrix;

import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.ScreenOrientation;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class test {
	
	protected static AndroidDriver<AndroidElement> driver;

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		
		for(String temp : MyMethods.getSettingsFromMatrix()) {
			String settings = (temp.split("/")[1]);	// Splitting the string from /
			
			
		}

	}
	
	

}
