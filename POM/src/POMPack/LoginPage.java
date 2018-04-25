package POMPack;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.http.ConnectionClosedException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage 
{
	WebDriver driver;
	By UserName = By.xpath("//*[@id='userid'][@name='username']");
	By Password = By.xpath("//input[@id='password']");
	By LoginButton = By.xpath("//*[@id='id2']");
	By Confrimbutton = By.xpath("//*[@name='confirm'][@value='Confirm']");
	By Institution = By.xpath("//*[@id='topmenu']/ul/div[2]/li/a/span");
	By Merchanttree = By.xpath("//*[@id='ACM000']");
	By Activity = By.xpath("//*[@id='MEA001']");
	By CreateApp = By.xpath("//*[@id='MRAAN0']");
	
	
public LoginPage(WebDriver driver) {
	this.driver = driver;
}

public void typeusername (String uname) {
    //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='userid'][@name='username']")));
	WebDriverWait wait = new WebDriverWait(driver, 180);
	wait.until(ExpectedConditions.elementToBeClickable(UserName));
    driver.findElement(UserName).sendKeys(new CharSequence[] { uname });
}

public void typepassword (String pwd) {
	driver.findElement(Password).sendKeys(new CharSequence[] { pwd });
}

public void clickloginbutton () {
	driver.findElement(LoginButton).click();
}

public void clickconfirmbutton () {
	driver.findElement(Confrimbutton).click();
}

public void Launchcreateapp () {
	driver.findElement(Institution).click();
	driver.findElement(Merchanttree).click();
    WebDriverWait wait1 = new WebDriverWait(driver, 20);
    wait1.until(ExpectedConditions.elementToBeClickable(Activity));	
	driver.findElement(Activity).click();
	wait1.until(ExpectedConditions.elementToBeClickable(CreateApp));
	driver.findElement(CreateApp).click();
}


}
