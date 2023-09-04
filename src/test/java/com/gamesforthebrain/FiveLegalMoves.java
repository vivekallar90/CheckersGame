package com.gamesforthebrain;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.github.dockerjava.api.command.RestartContainerCmd;

import resources.Base;



public class FiveLegalMoves extends Base{

	Pom_gamesforthebrain page;
	 @BeforeTest(alwaysRun = true)
	    public void setUp() throws IOException {
	        driver = driver_init();
	        page= new Pom_gamesforthebrain(driver);
	    }
	 @Test
	 public void performFiveMoves() throws InterruptedException {
		 String title=driver.getTitle();
		 System.out.println(title);
		 Assert.assertTrue(title.contains("Checkers - Games for the Brain"));
		 page.ClickOn(62,73);
		
		 page.ClickOn(42,33);
		 page.ClickOn(51,33);
		 page.ClickOn(31,42);
		 page.ClickOn(33,24);
		 page.restart();
		 Assert.assertTrue(page.getMessageText().contains("Select an orange piece to move."));
	 }
	 @AfterTest
	 public void tearDown() 
	 {
		driver.close(); 
	 }
}
