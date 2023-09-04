package com.gamesforthebrain;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.Base;

public class Pom_gamesforthebrain extends Base {
	
	  WebDriver driver;
	    @FindBy(id = "message")
	   private WebElement message;
	   
	    @FindBy(linkText = "Restart...")
	    private WebElement restart;

	    
	    public Pom_gamesforthebrain(WebDriver driver) {

	        this.driver = driver;

	        // This initElements method will create all WebElements

	        PageFactory.initElements(driver, this);

	    }
	  
	    public void restart() {
	    	System.out.println("restarting...");
	    	restart.click();
	    	
	    }
	    public String getMessageText() {
	    	return message.getText();
	    }
	    public void ClickOn(int num1,int num2) throws InterruptedException {
	    	WebElement space1,space2;
	    
	    	String messagetext=message.getText();
			if(messagetext.contains("Select an orange piece to move.") || messagetext.contains("Make a move.")) {
				space1= driver.findElement(By.name("space"+num1));
				space2= driver.findElement(By.name("space"+num2));
				space1.click();
				space2.click();
				Thread.sleep(2000);
			}
	    	}
	    	
		}


