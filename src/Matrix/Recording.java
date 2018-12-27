package Matrix;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class Recording {
	
	protected static AndroidDriver<AndroidElement> driver;
	public static String settings;

	public static void main(String[] args) throws EncryptedDocumentException, IOException, InterruptedException {
		setDriver();
		acceptEulaPopup();
		//acceptHelpActivity();
		openCustomMode();
		//openSettings();
		//setSettings("FLAC", "16", "192");
		//setMic("Earphones");
		startExecution();
		//enableStudioOriginals();
		//selectBackgroundFile();
		//selectMusicPicker();
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
		Thread.sleep(3000);
		String currentActivity = driver.currentActivity();
		String expectedActivity = ".SettingsActivity";
		if(currentActivity.equalsIgnoreCase(expectedActivity)) {} else {
			driver.navigate().back();
			driver.findElementByXPath("//android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/"
					+ "android.widget.ImageButton").click();
			Thread.sleep(3000);
			driver.findElementByXPath("//android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[4]/"
					+ "android.widget.RelativeLayout").click();
		}
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
		driver.runAppInBackground(Duration.ofMillis(3500));
		if(mic.contains("Phone") || mic.contains("Earphones") || mic.contains("USB")) {
			driver.findElementByXPath("//android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/"
					+ "android.widget.LinearLayout/android.widget.TextView[1]").click();
			driver.findElementByXPath("//android.widget.CheckedTextView[@text='" + mic + "']").click();
			Thread.sleep(3000);
		}
	}
	
	public static void startExecution() throws EncryptedDocumentException, IOException, InterruptedException {
			int i = 1; //Serial number
		for(String temp : MyMethods.getSettingsFromMatrix()) {
			settings = (temp.split("/")[0]).split("_")[0];	// Splitting the string from /
			String format = (temp.split("/")[0]).split("_")[1];		// Splitting the string from /
			String bitDepth = (temp.split("/")[0]).split("_")[2].replaceAll("[^\\d.]", "");	// Splitting the string from / and removing alphabets
			String samplingRate = (temp.split("/")[0]).split("_")[3].replaceAll("[^\\d.]", "");	// Splitting the string from / and removing alphabets
			String backgroundFile = (temp.split("/")[1]);
			if(settings.contains("Stereo")) {
				driver.rotate(ScreenOrientation.LANDSCAPE);
			} else {
				driver.rotate(ScreenOrientation.PORTRAIT);
			}
			Thread.sleep(3000);
			openSettings();
			Thread.sleep(3000);
			setSettings(format, bitDepth, samplingRate);
			Thread.sleep(3000);
			setMic("Earphones");
			selectBackgroundFile();
			selectMusicPicker();
			switch (backgroundFile) {
			case "MP3":
				driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Bulleya (Mr-Jatt.com)\"));");
				driver.findElementByXPath("//android.widget.TextView[@text='Bulleya (Mr-Jatt.com)']").click();
				break;
				
			case "Stereo_WAV_16Bit_48KHz_10Sec":
				driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Stereo_WAV_16Bit_48KHz\"));");
				driver.findElementByXPath("//android.widget.TextView[@text='Stereo_WAV_16Bit_48KHz']").click();
				break;
				
			case "Mono_AAC_16Bit_96KHz_10Sec":
				driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Mono_M4A_16Bit_96KHz\"));");
				driver.findElementByXPath("//android.widget.TextView[@text='Mono_M4A_16Bit_96KHz']").click();
				break;
				
			case "Mono_WAV_16Bit_192KHz_10Sec":
				driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Mono_WAV_16Bit_192KHz\"));");
				driver.findElementByXPath("//android.widget.TextView[@text='Mono_WAV_16Bit_192KHz']").click();
				break;
				
			case "MQA":	
				driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Doin' It Right (feat. Panda Bear)\"));");
				driver.findElementByXPath("//android.widget.TextView[@text=\"Doin' It Right (feat. Panda Bear)\"]").click();
				break;
				
			case "AIFF":
				driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"AIFF_32bit(96khz)\"));");
				driver.findElementByXPath("//android.widget.TextView[@text='AIFF_32bit(96khz)']").click();
				break;
				
			case "Stereo_FLAC_16Bit_88.2KHz_10Sec.flac":
				driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Stereo_FLAC_16Bit_88.2KHz\"));");
				driver.findElementByXPath("//android.widget.TextView[@text='Stereo_FLAC_16Bit_88.2KHz']").click();
				break;
				
			default:
				System.out.println("Invalid file");
				driver.navigate().back();
				break;
			}		
			Thread.sleep(5000);
			studioModeFileLimitPopup();
			startRecording();
			System.out.println("(" + i + ") " + "File Recorded -> Settings: " + settings + "_" + format + "_" + bitDepth + " bit_" + samplingRate + "kHz || Background File: " + backgroundFile);
			i++;
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
	
	public static void selectBackgroundFile() throws InterruptedException, MalformedURLException {
		Thread.sleep(3000);
		driver.runAppInBackground(Duration.ofMillis(5000));
		driver.findElementByXPath("//android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/"
				+ "android.widget.LinearLayout/android.widget.TextView[2]").click();
		
	}
	
	public static void acceptEulaPopup() throws InterruptedException {
		Thread.sleep(3000);
		String currentActivity = driver.currentActivity();
		if(currentActivity.contains(".UnifiedEula")) {
			driver.findElementByXPath("//android.widget.Button[@resource-id='com.lge.eula:id/btn_agree']").click();
		}
	}
	
	public static void acceptHelpActivity() throws InterruptedException {
		Thread.sleep(3000);
		String currentActivity = driver.currentActivity();
		String expectedActivity = ".HelpActivity";
		while(currentActivity == expectedActivity) {
			Thread.sleep(3500);
			driver.findElementByXPath("//android.widget.Button[@resource-id='com.lge.hifirecorder:id/tip_help_next_button']").click();
		}
	}
	
	public static void selectMusicPicker() throws InterruptedException {
		Thread.sleep(3000);
		String currentActivity = driver.currentActivity();
		String expectedActivity = "com.android.internal.app.ResolverActivity";
		if(expectedActivity.contains(currentActivity)) {
			driver.findElementByXPath("//android.widget.ImageView[@resource-id='com.lge:id/icon_lg']").click();
			Thread.sleep(3000);
			AndroidElement checkBox = driver.findElementByXPath("//android.widget.CheckBox[@resource-id='com.lge:id/always_use_lg']");
			AndroidElement okButton = driver.findElementByXPath("//android.widget.Button[@resource-id='android:id/button_always']");
			String isChecked = checkBox.getAttribute("checked");
			if(isChecked.equals("true")) {
				okButton.click();
			} else {
				checkBox.click();
				okButton.click();
			}
			
		}
	}
	
	public static void studioModeFileLimitPopup() throws InterruptedException {
		Thread.sleep(3000);
		try {
			AndroidElement checkBox = driver.findElementByXPath("//android.widget.CheckBox[@resource-id='android:id/checkbox']");
			checkBox.click();
			driver.findElementByXPath("//android.widget.Button[@resource-id='android:id/button3']").click();
		} catch (Exception e) {
			//System.out.println("");
		}
		Thread.sleep(3000);
	}
	
	public static void startRecording() throws InterruptedException {
		driver.runAppInBackground(Duration.ofMillis(2000));
		Thread.sleep(1000);
		if(settings.contains("Stereo")) {
			driver.rotate(ScreenOrientation.LANDSCAPE);
		} else {
			driver.rotate(ScreenOrientation.PORTRAIT);
		}
		Thread.sleep(2500);
		driver.findElementByXPath("//android.widget.Button[@resource-id='com.lge.hifirecorder:id/recordButton']").click();
		Thread.sleep(1000);
		AndroidElement bookmarkButton = driver.findElementByXPath("//android.widget.Button[@resource-id='com.lge.hifirecorder:id/bookmarkButton']");
		bookmarkButton.click();
		Thread.sleep(1000);
		bookmarkButton.click();
		Thread.sleep(1000);
		bookmarkButton.click();
		Thread.sleep(1000);
		bookmarkButton.click();
		Thread.sleep(2000);
		driver.findElementByXPath("//android.widget.Button[@resource-id='com.lge.hifirecorder:id/stopButton']").click();
		Thread.sleep(3000);
		driver.runAppInBackground(Duration.ofMillis(2000));
		Thread.sleep(1000);
		if(settings.contains("Stereo")) {
			driver.rotate(ScreenOrientation.LANDSCAPE);
		} else {
			driver.rotate(ScreenOrientation.PORTRAIT);
		}
		Thread.sleep(3000);
		driver.findElementByXPath("//android.widget.Button[@resource-id='com.lge.hifirecorder:id/saveButton']").click();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		String element = "//android.widget.LinearLayout[@index=1]";
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(element)));
		Thread.sleep(3000);
		driver.navigate().back();
		driver.navigate().back();
		driver.runAppInBackground(Duration.ofMillis(2000));
		Thread.sleep(3000);
		//Remove Background File
		driver.findElementByXPath("//android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/"
				+ "android.widget.LinearLayout/android.widget.TextView[1]").click();
		driver.runAppInBackground(Duration.ofMillis(2000));
		Thread.sleep(2500);
		
	}

}
