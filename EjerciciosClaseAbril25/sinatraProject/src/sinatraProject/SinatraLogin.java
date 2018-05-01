package sinatraProject;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SinatraLogin {

	protected static WebDriver driver;

	public static void main(String[] args) {
		openSinatraSite();
		validateMainMenu();
		logIntoSite();
		validateSongList();
		closeBrowser();

	}

	private static void closeBrowser() {
		driver.quit();
		
	}

	private static void openSinatraSite() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("localhost:4567");
	}

	private static void validateMainMenu() {
		//validar que los elementos del menu existen
		//validar que existe el link 'Home'
		String xpathHomeLink = "//nav//a[text() = 'Home']";
		WebElement homeLink = driver.findElement(By.xpath(xpathHomeLink));
		if(homeLink.isDisplayed()) {
			System.out.println("Home link is displayed");
		}
		else {
			System.out.println("Home link is not displayed");
		}
		//validar que existe el link 'About'
		String xpathAboutLink = "//nav//a[text() = 'About']";
		WebElement aboutLink = driver.findElement(By.xpath(xpathAboutLink));
		if(aboutLink.isDisplayed()) {
			System.out.println("About link is displayed");
		}
		else {
			System.out.println("About link is not displayed");
		}
	}

	private static void logIntoSite() {
		//dar click al link 'Login'
		WebElement loginLink = driver.findElement(By.xpath("//nav//a[text() = 'Login']"));
		loginLink.click();
		//introducimos el usuario
		WebElement userNameField = driver.findElement(By.name("username"));
		userNameField.clear();
		userNameField.sendKeys("frank");
		//introducimos el password
		WebElement passwordField = driver.findElement(By.name("password"));
		passwordField.clear();
		passwordField.sendKeys("sinatra");
		//click en el boton de 'Log in'
		WebElement logInButton = driver.findElement(By.xpath("//input[@value = 'Log In']"));
		logInButton.click();	
	}

	private static void validateSongList() {
		if(driver.findElement(By.xpath("//h1[text() = 'Songs']")).isDisplayed()) {
			System.out.println("Si encontro las canciones");
		}
		else {
			System.out.println("No encontro las canciones");
			System.exit(-1);
		}
		if( driver.findElement(By.id("songs")).isDisplayed()   ) {
			System.out.println("Si encontro la lista de las canciones");
			List<WebElement> songListElements = driver.findElements(By.xpath("//ul[@id = 'songs']/li"));
			for(WebElement listElement : songListElements) {
				System.out.println("Cancion: " + listElement.getText());
			}
		}
		else {
			System.out.println("No encontro la lista de las canciones");
			System.exit(-1);
		}
		
		
	}

}
