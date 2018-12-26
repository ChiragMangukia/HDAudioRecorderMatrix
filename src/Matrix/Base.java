package Matrix;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class Base {

public static AndroidDriver<AndroidElement> Capabilities() throws MalformedURLException {
	
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "G7 ThinQ");
		desiredCapabilities.setCapability("appPackage", "com.lge.hifirecorder");
		desiredCapabilities.setCapability("appActivity", ".LaunchHifiRecorder");
		desiredCapabilities.setCapability("autoGrantPermissions", true);
		desiredCapabilities.setCapability (MobileCapabilityType.CLEAR_SYSTEM_FILES, true);
		
		AndroidDriver<AndroidElement> androidDriver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
		androidDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return androidDriver;

	}
}
