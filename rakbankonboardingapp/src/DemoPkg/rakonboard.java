package DemoPkg;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class rakonboard
{
  public static void main(String[] args)
    throws InterruptedException, IOException, TimeoutException, ConnectionClosedException 
  {
    try
    {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
		System.out.println("start Time : " + sdf.format(new Date()));
      //String filePathGecko = System.getProperty("user.dir") + "\\Lib";
      //System.setProperty("webdriver.firefox.marionette", filePathGecko + "\\geckodriver.exe");
      String filePathGecko = System.getProperty("user.dir") + "\\Lib";
      System.setProperty("webdriver.ie.driver", filePathGecko + "\\IEDriverServer.exe");
        
    	
      //WebDriver driver = new FirefoxDriver();
      WebDriver driver = new InternetExplorerDriver();
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      driver.manage().timeouts().pageLoadTimeout(500,TimeUnit.SECONDS);
      driver.manage().window().maximize();
      
      Thread.sleep(2000);
      String filename = "Datasheet.xls";
      String filePath = System.getProperty("user.dir") + "\\excelIO";
      File file = new File(filePath + "\\" + filename);
      FileInputStream inputStream = new FileInputStream(file);
      Workbook newWorkbook = null;
      String fileExtensionName = filename.substring(filename.indexOf("."));
      if (fileExtensionName.equals(".xlsx")) {
        newWorkbook = new XSSFWorkbook();
      } else if (fileExtensionName.equals(".xls")) {
        newWorkbook = new HSSFWorkbook(inputStream);
      }
      Sheet newSheet = newWorkbook.getSheet("DataSheet");
      double rowCountval = newSheet.getLastRowNum() - newSheet.getFirstRowNum();
      
      Row row = newSheet.getRow(1);
      String uname = row.getCell(208).getStringCellValue();
      String pwd = row.getCell(209).getStringCellValue();
      String urllink = row.getCell(210).getStringCellValue();
      
      inputStream.close();
      
      //get URL
      driver.get(urllink);
      
      WebDriverWait wait = new WebDriverWait(driver, 180);
      
      //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='userid'][]")));
      driver.findElement(By.xpath("//*[@id='userid'][@name='username']")).sendKeys(new CharSequence[] { uname });
      driver.findElement(By.xpath("//input[@id='password']")).sendKeys(new CharSequence[] { pwd });
      
      driver.findElement(By.xpath("//*[@id='id2']")).click();
      
      driver.findElement(By.xpath("//*[@name='confirm'][@value='Confirm']")).click();
      //Function_Lib.waitforloader(driver);  
      
      driver.findElement(By.xpath("//*[@id='topmenu']/ul/div[2]/li/a/span")).click();
      
      //Merchant tree
      driver.findElement(By.xpath("//*[@id='ACM000']")).click();
      //Activity under Merchant
      WebDriverWait wait1 = new WebDriverWait(driver, 20);
      wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='MEA001']")));
	
      driver.findElement(By.xpath("//*[@id='MEA001']")).click();
      //Create App
      WebDriverWait wait2 = new WebDriverWait(driver, 20);
      wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='MRAAN0']")));
      driver.findElement(By.xpath("//*[@id='MRAAN0']/a")).click();
      
      int totalcount = (int)Math.ceil(rowCountval);
      for (int i = 1; i <= totalcount; i++) {
        Function_Lib.BoardAcct(filePath, "Datasheet.xls", "DataSheet", driver);
      }
      Thread.sleep(1000L);
      //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@ng-click='logout()']")));
      //driver.findElement(By.xpath("//a[@ng-click='logout()']")).click();
      
      driver.quit();
      System.out.println("Execution Completed");
      System.out.println("start Time : " + sdf.format(new Date()));
    }
    
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}
