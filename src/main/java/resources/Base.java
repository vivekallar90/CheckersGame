package resources;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Properties;

public class Base {
	
public static WebDriver driver;


	public  WebDriver driver_init() throws IOException {
		
		
		
		if(readProperty("browser").equalsIgnoreCase("chrome")) {
			/*
			 * ChromeOptions chromeOptions = new ChromeOptions();
			 * WebDriverManager.chromedriver().setup();
			 */driver = new ChromeDriver();
			driver.manage().window().maximize();
		}
		if(readProperty("browser").equalsIgnoreCase("Edge")) {
			EdgeOptions edgeOptions = new EdgeOptions();
			WebDriverManager.edgedriver().setup();
			driver = new  EdgeDriver(edgeOptions);
			driver.manage().window().maximize();
		}
		
		if(readProperty("browser").equalsIgnoreCase("Firefox")) {
			
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			WebDriverManager.firefoxdriver().setup();
			driver = new  FirefoxDriver(firefoxOptions);
			driver.manage().window().maximize();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		driver.get("https://www.gamesforthebrain.com/game/checkers/");
		return driver;
	}

	public  String readProperty(String key) throws IOException{
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources"+"\\config.properties");
		Properties pros=new Properties();
		pros.load(fis);
		System.out.println("reading from file"+pros.getProperty(key));
		return pros.getProperty(key);
	}
	

}
