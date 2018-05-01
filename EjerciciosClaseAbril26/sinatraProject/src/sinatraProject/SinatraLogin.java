package sinatraProject;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SinatraLogin extends SinatraParentTest {

	

	public static void main(String[] args) {
		openSinatraSite();
		validateMainMenu();
		logIntoSite();
		validateSongList();
		closeBrowser();

	}

	
}
