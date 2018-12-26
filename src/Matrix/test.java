package Matrix;

import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class test {
	
	protected static AndroidDriver<AndroidElement> driver;

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		String element = "//android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/"
				+ "android.widget.LinearLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.ExpandableListView/"
				+ "android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.RelativeLayoutandroid.widget.TextView";
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(element)));
			
			
		

	}
	
	

}
