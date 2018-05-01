package sinatraProject;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SinatraParentTest {
	protected static WebDriver driver;
	protected static void closeBrowser() {
		driver.quit();
		
	}

	protected static void openSinatraSite() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("localhost:4567");
	}

	protected static void validateMainMenu() {
		//validar que los elementos del menu existen
		//validar que existe el link 'Home'
		validateElementExists(By.xpath("//nav//a[text() = 'Home']"), "Link Home");
		validateElementExists(By.xpath("//nav//a[text() = 'About']"), "Link About");
		validateElementExists(By.xpath("//nav//a[text() = 'Contact']"), "Link Contact");
		validateElementExists(By.xpath("//nav//a[text() = 'Songs']"), "Link Songs");
		validateElementExists(By.xpath("//nav//a[text() = 'Login']"), "Link Login");
		validateElementExists(By.xpath("//nav//a[text() = 'Logout']"), "Link Logout");		
	}

	protected static void logIntoSite() {
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

	protected static void validateSongList() {
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
	protected static void clickSongs() {
		WebElement songsLink = driver.findElement(By.xpath("//nav//a[text() = 'About']"));
		songsLink.click();
		//
	}
	//Validar que existe Create a new songs
	protected static void validateCreateSong() {
		
		validateElementEnabled(By.xpath("//a[text() = 'Create a new songs']"), "Link cCreate Song");
	}
	//create song
	protected static void createSong() {
		
		//Click Create a new songs
		WebElement createSongLink = driver.findElement(By.xpath("//a[text() = 'Create a new songs']"));
		createSongLink.click();
		
		//Validar que exten los campos de entrada
		validateElementExists(By.id("title"), "Input title");
		validateElementExists(By.id("length"), "Input length");
		validateElementExists(By.id("released_on"), "Input date");
		validateElementExists(By.id("lyrics"), "Input lyrics");
		validateElementEnabled(By.xpath("//*[@value='Save Song']"), "Button Save Song");
		//Agregar Titulo
		
		//Agregar Lenght
	}
	
	private static void validateElementEnabled(By locator, String elementName) {
		WebElement elem = driver.findElement(locator);
		if (elem.isEnabled()){
			System.out.println("Se encontro el " + elementName);
			}else{
				System.out.println("No se encontro el " + elementName);
				System.exit(-1);
			}
		
	}

	protected static void validateElementExists(By locator, String elementName) {
		WebElement elem = driver.findElement(locator);
		if (elem.isDisplayed()){
			System.out.println("Se encontro el " + elementName);
			}else{
				System.out.println("No se encontro el " + elementName);
				System.exit(-1);
			}
	}

}
