package Matrix;

import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.ScreenOrientation;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class Recording {
	
	protected static AndroidDriver<AndroidElement> driver;

	public static void main(String[] args) throws EncryptedDocumentException, IOException, InterruptedException {
		setDriver();
		openCustomMode();		
		//openSettings();
		//setSettings("FLAC", "16", "192");
		setMic("Earphones");
		//startExecution();
		//enableStudioOriginals();
		selectBackgroundFile();
	}
	
	public static void setDriver() throws MalformedURLException {
		Recording.driver = Base.Capabilities();		
	}
	
	private static void openCustomMode() throws MalformedURLException, InterruptedException {
		driver.findElementByXPath("//android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/"
				+ "android.view.ViewGroup/android.widget.ImageButton").click();
		Thread.sleep(3000);
		driver.findElementByXPath("//android.support.v4.widget.DrawerLayout/android.widget.ListView[1]/android.widget.LinearLayout[3]").click();
		Thread.sleep(3000);
	}
	
	private static void openSettings() throws InterruptedException {
		driver.findElementByXPath("//android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/"
				+ "android.widget.ImageButton").click();
		Thread.sleep(3000);
		driver.findElementByXPath("//android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[3]/"
				+ "android.widget.RelativeLayout").click();
	}
	
	private static void setSettings(String fileType, String bitDepth, String samplingRate) throws InterruptedException {
		//Set File type
		driver.findElementByXPath("//android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.LinearLayout[1]/"
				+ "android.widget.FrameLayout/android.widget.ListView/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.RelativeLayout"
				+ "").click();
		
		if(fileType.contains("M4A") || fileType.contains("WAV") || fileType.contains("FLAC")) {
			driver.findElementByXPath("//android.widget.CheckedTextView[@text='" + fileType + "']").click();
		}
		
		//Set Bit depth
		
		driver.findElementByXPath("//android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.LinearLayout[1]/"
				+ "android.widget.FrameLayout/android.widget.ListView/android.widget.FrameLayout[3]/android.widget.LinearLayout/android.widget.RelativeLayout"
				+ "").click();
		
		if(bitDepth.contains("16") || bitDepth.contains("24"))	{
			driver.findElementByXPath("//android.widget.CheckedTextView[@text='" + bitDepth + " bit']").click();
		}
		
		//Set Sampling rate
		
		driver.findElementByXPath("//android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.LinearLayout[1]/"
				+ "android.widget.FrameLayout/android.widget.ListView/android.widget.FrameLayout[4]/android.widget.LinearLayout/android.widget.RelativeLayout"
				+ "").click();
		
		if(samplingRate.contains("44.1") || samplingRate.contains("48") || samplingRate.contains("88.2") || samplingRate.contains("96") || samplingRate.contains("176.4") || samplingRate.contains("192"))	{
			driver.findElementByXPath("//android.widget.CheckedTextView[@text='" + samplingRate + " kHz']").click();
		}
		Thread.sleep(3000);
		enableStudioOriginals();
		Thread.sleep(3000);
		driver.navigate().back();
	}
	
	public static void setMic(String mic) throws InterruptedException {
		if(mic.contains("Phone") || mic.contains("Earphones") || mic.contains("USB")) {
			driver.findElementByXPath("//android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/"
					+ "android.widget.LinearLayout/android.widget.TextView[1]").click();
			driver.findElementByXPath("//android.widget.CheckedTextView[@text='" + mic + "']").click();
			Thread.sleep(7000);
		}
	}
	
	public static void startExecution() throws EncryptedDocumentException, IOException, InterruptedException {
		for(String temp : MyMethods.getSettingsFromMatrix()) {
			String settings = (temp.split("/")[0]).split("_")[0];	// Splitting the string from /
			String format = (temp.split("/")[0]).split("_")[1];		// Splitting the string from /
			String bitDepth = (temp.split("/")[0]).split("_")[2].replaceAll("[^\\d.]", "");	// Splitting the string from / and removing alphabets
			String samplingRate = (temp.split("/")[0]).split("_")[3].replaceAll("[^\\d.]", "");	// Splitting the string from / and removing alphabets
			if(settings.contains("Stereo")) {
				driver.rotate(ScreenOrientation.LANDSCAPE);
			} else {
				driver.rotate(ScreenOrientation.PORTRAIT);
			}
			openSettings();
			Thread.sleep(3000);
			setSettings(format, bitDepth, samplingRate);
			Thread.sleep(3000);
		}
	}
	
	public static void enableStudioOriginals() throws InterruptedException {
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Studio originals\"));");
		AndroidElement studioOriginal = driver.findElementByXPath("//android.widget.Switch[@resource-id='android:id/switch_widget']");
		String isChecked = studioOriginal.getAttribute("checked");
		if(isChecked.contains("false")) {
			studioOriginal.click();
		}		
	}
	
	public static void selectBackgroundFile() throws InterruptedException {
		Thread.sleep(3000);
		//driver.findElementByXPath("//android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/"
			//	+ "android.widget.LinearLayout/android.widget.TextView[2]").click();
		driver.findElementByXPath("//android.widget.TextView[@bounds='[978,94][1132,248]']").click();
	}

}
