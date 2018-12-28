package Matrix;

import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.ScreenOrientation;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class test {
	
	protected static AndroidDriver<AndroidElement> driver;

	public static void main(String[] args) throws EncryptedDocumentException, IOException, InterruptedException {
		
		setDriver();
		Thread.sleep(3000);
		ScreenOrientation screenOrientation = driver.getOrientation();
		System.out.println(screenOrientation);
		driver.rotate(ScreenOrientation.LANDSCAPE);
		System.out.println(driver.getOrientation());
		if(driver.getOrientation().equals("PORTRAIT")) {
			System.out.println("Hi");
		} else {
			System.out.println("Hello");
		}
			
	
	}
	
	public static void setDriver() throws MalformedURLException {
		driver = Base.Capabilities();
	}
}
