package com.nexsoft.testng.frontpage;

import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

public class FrontpageTest {
	WebDriver driver;

	

	public String screenshoot() {
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String waktu = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		String namaFile = "E:\\TestScreenshoot\\"+waktu+".png";
		File screenshoot = new File(namaFile);
		try {
			FileUtils.copyFile(srcFile, screenshoot);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return namaFile;
	}
	
	
	@Test(dataProvider = "data-provider",
    dataProviderClass = com.nexsoft.testng.dataprovider.DataProviderNexSoft.class)
	public void consumeData(String data) {
		System.out.println(data);
	}
	
	
	@BeforeClass
	public void init() {
		System.setProperty("url", "http://localhost/cicool/");
		System.setProperty("webdriver.chrome.driver", "E:\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(System.getProperty("url"));
		driver.manage().window().maximize();
    	driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);

	}

	@Test
	public void login() {
		driver.findElement(By.cssSelector(".fa.fa-sign-in")).click();

		driver.findElement(By.cssSelector("input[placeholder='Email']")).clear();
		driver.findElement(By.cssSelector("input[placeholder='Email']")).sendKeys("dewabrata@gmail.com");
		// input[placeholder='Password']
		driver.findElement(By.cssSelector("input[placeholder='Password']")).clear();
		driver.findElement(By.cssSelector("input[placeholder='Password']")).sendKeys("123456");
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		
		String file = "<img src='file://"+screenshoot()+"' width=\"250\" height=\"300\" />";

		
		Reporter.log(file);
		
		
		String username = driver.findElement(By.cssSelector("span[class='hidden-xs']")).getText();
		Assert.assertEquals(username, "Dewabrata");
	}
	
	@Test
	public void setLanguage() {
	
	}

}
