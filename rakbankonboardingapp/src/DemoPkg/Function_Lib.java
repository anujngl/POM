package DemoPkg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;
import java.util.List;
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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.*;

public class Function_Lib
{
  private static WebDriver tab2;

public static void BoardAcct(String filePath, String fileName, String sheetName, WebDriver driver)
    throws IOException, InterruptedException, TimeoutException, ConnectionClosedException
  {
    try
    {
    	String val = "";
      
    	File file = new File(filePath + "\\" + fileName);
    	FileInputStream inputStream = new FileInputStream(file);
    	Workbook newWorkbook = null;
    	String fileExtensionName = fileName.substring(fileName.indexOf("."));
    	if (fileExtensionName.equals(".xlsx")) 
    	{
    		newWorkbook = new XSSFWorkbook();
    	} 
    	else if (fileExtensionName.equals(".xls")) 
    	{
        newWorkbook = new HSSFWorkbook(inputStream);
    	}
    	Sheet newSheet = newWorkbook.getSheet(sheetName);
    	int rowCount = newSheet.getLastRowNum() - newSheet.getFirstRowNum();
      
    	for (int i = 1; i < rowCount + 1; i++)
    	{
    		Row row = newSheet.getRow(i);

    		val = "";
    		//String lastrowValflag = "false";
    		if (row.getCell(205) == null) 
    		{
    			System.out.println("Null");
    		}
    		if ((row.getCell(205) == null) || (row.getCell(205).getStringCellValue().equalsIgnoreCase("Y")) || (row.getCell(205).getStringCellValue().equalsIgnoreCase("Yes")))
    		{
    			int lastcolval = row.getLastCellNum();
    			for (int j = 0; j < lastcolval; j++)
    			{
    				System.out.print(row.getCell(j) + "||");
            
    				val = val + row.getCell(j) + "@@";
            
    			}
    			WebDriverWait wait = new WebDriverWait(driver, 30);
    			
    			driver.switchTo().defaultContent();
    			// + button
    			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='id30']")));
    			driver.findElement(By.xpath("//a[@class='addR']")).click();
        
          
    			String[] splitval = val.split("@@");
    			//Contact person
          
    			  driver.switchTo().frame(0); 
		          driver.findElement(By.xpath("//input[@fld_fqn='merContactName']")).sendKeys(new CharSequence[] { splitval[0] });
		          //to be filled until submit
		          driver.findElement(By.xpath("//input[@fld_fqn='merName']")).sendKeys(new CharSequence[] { splitval[1] });
		          
		          
		          //sample starts for Listbox
		          Select elem1 = new Select(driver.findElement(By.name("merType:input:dropdowncomponent")));  
		          elem1.selectByVisibleText(splitval[2]);
		          
		          //outlet radio box control
		          if (splitval[3].equalsIgnoreCase("N")) {
		        	  driver.findElement(By.cssSelector("input[value='N']")).click();
		          }else {
		        	  driver.findElement(By.cssSelector("input[value='Y']")).click();
		          }
		          
		          driver.findElement(By.xpath("//input[@fld_fqn='merBussName']")).sendKeys(new CharSequence[] { splitval[4] });
		          driver.findElement(By.xpath("//input[@fld_fqn='merCompanyName']")).sendKeys(new CharSequence[] { splitval[5] });
		          elem1 = new Select(driver.findElement(By.name("hubCode:input:dropdowncomponent")));  
		          elem1.selectByVisibleText(splitval[6]);

		          //for (int second = 0;; second++) {
		        	//    if (second >= 60) ;
		        	  //  try { 
		        	    //    Select droplist = new Select(driver.findElement(By.name("chainCode:input:dropdowncomponent")));
		        	      //  if(!droplist.getOptions().isEmpty()){
		        	        //	Thread.sleep(2000);
		        	          //  break;
		        	        //}
		        	    //} catch (Exception e) {
		        	         // best put something here
		        	    //}
		        	    //Thread.sleep(1000);
		        	//}
		          waitforloader(driver);
		          new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElementLocated(By.name("chainCode:input:dropdowncomponent"), splitval[7]));		          
		          elem1 = new Select(driver.findElement(By.name("chainCode:input:dropdowncomponent")));  
		          elem1.selectByVisibleText(splitval[7]);
		          waitforloader(driver);
		          new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElementLocated(By.name("chainCode:input:dropdowncomponent"), splitval[7]));		          
		          elem1 = new Select(driver.findElement(By.name("chainCode:input:dropdowncomponent")));  
		          elem1.selectByVisibleText(splitval[7]);
		          waitforloader(driver);
		          new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElementLocated(By.name("merMcc:input:dropdowncomponent"), splitval[8]));
		          elem1 = new Select(driver.findElement(By.name("merMcc:input:dropdowncomponent")));
		          elem1.selectByVisibleText(splitval[8]);
		          
		          waitforloader(driver);
		          
		          WebElement TextBox = driver.findElement(By.name("foundingDate:input:dateTextField"));
		          String input= splitval[9];
		          TextBox.getAttribute("name");
		          JavascriptExecutor jst= (JavascriptExecutor) driver;
		          jst.executeScript("arguments[1].value = arguments[0]; ", input, TextBox); 
		          
		          elem1 = new Select(driver.findElement(By.name("businessStatus:input:dropdowncomponent"))); 
		          elem1.selectByVisibleText(splitval[10]);
		          elem1 = new Select(driver.findElement(By.name("languagePreferences:input:dropdowncomponent"))); 
		          elem1.selectByVisibleText(splitval[11]);
		          
		          driver.findElement(By.xpath("//input[@fld_fqn='establishedYear']")).sendKeys(new CharSequence[] { splitval[12] });
		          driver.findElement(By.xpath("//input[@fld_fqn='shopEstaNo']")).sendKeys(new CharSequence[] { splitval[13] });
		          elem1 = new Select(driver.findElement(By.name("establishmentType:input:dropdowncomponent"))); 
		          elem1.selectByVisibleText(splitval[14]);
		          elem1 = new Select(driver.findElement(By.name("merOfficeType:input:dropdowncomponent"))); 
		          elem1.selectByVisibleText(splitval[15]);
		          driver.findElement(By.xpath("//input[@fld_fqn='merOfficeTenure']")).sendKeys(new CharSequence[] { splitval[16] });
		          driver.findElement(By.xpath("//input[@fld_fqn='merResiTenure']")).sendKeys(new CharSequence[] { splitval[17] });
		          elem1 = new Select(driver.findElement(By.name("merResiType:input:dropdowncomponent"))); 
		          elem1.selectByVisibleText(splitval[18]);
		          driver.findElement(By.xpath("//input[@fld_fqn='merBankName']")).sendKeys(new CharSequence[] { splitval[19] });
		          driver.findElement(By.xpath("//input[@fld_fqn='merBranchName']")).sendKeys(new CharSequence[] { splitval[20] });
		          driver.findElement(By.xpath("//input[@fld_fqn='merBranchCode']")).sendKeys(new CharSequence[] { splitval[21] });
		          driver.findElement(By.cssSelector("input[value='2']")).click();
		          driver.findElement(By.xpath("//input[@fld_fqn='merAccountNo']")).sendKeys(new CharSequence[] { splitval[23] });
		          driver.findElement(By.xpath("//input[@fld_fqn='merIban']")).sendKeys(new CharSequence[] { splitval[24] });
		          driver.findElement(By.xpath("//*[@name='remark:input:textAreaComponent']")).sendKeys(new CharSequence[] { splitval[25] });
	          
		         
		          driver.findElement(By.linkText("Business Details")).click();
		          driver.findElement(By.xpath("//input[@fld_fqn='merAnnualIncome']")).sendKeys(new CharSequence[] { splitval[26] });
		          driver.findElement(By.xpath("//input[@fld_fqn='averageBillAmount']")).sendKeys(new CharSequence[] { splitval[27] });
		          driver.findElement(By.xpath("//input[@fld_fqn='merAnnualTurnover']")).sendKeys(new CharSequence[] { splitval[28] });
		          driver.findElement(By.xpath("//input[@fld_fqn='expectedCardBusiness']")).sendKeys(new CharSequence[] { splitval[29] });
		          driver.findElement(By.xpath("//input[@fld_fqn='saleTaxNo']")).sendKeys(new CharSequence[] { splitval[30] });
		          driver.findElement(By.xpath("//input[@fld_fqn='merNatTaxId']")).sendKeys(new CharSequence[] { splitval[31] });
		          driver.findElement(By.xpath("//input[@fld_fqn='merLegalId']")).sendKeys(new CharSequence[] { splitval[32] });
		          driver.findElement(By.xpath("//input[@fld_fqn='turnoverAmt1']")).sendKeys(new CharSequence[] { splitval[33] });
		          driver.findElement(By.xpath("//input[@fld_fqn='turnoverAmt2']")).sendKeys(new CharSequence[] { splitval[34] });
		          driver.findElement(By.xpath("//input[@fld_fqn='turnoverAmt3']")).sendKeys(new CharSequence[] { splitval[35] });
		          driver.findElement(By.xpath("//input[@fld_fqn='regAddr1']")).sendKeys(new CharSequence[] { splitval[36] });
		          driver.findElement(By.xpath("//input[@fld_fqn='regAddr2']")).sendKeys(new CharSequence[] { splitval[37] });
		          driver.findElement(By.xpath("//input[@fld_fqn='regAddr3']")).sendKeys(new CharSequence[] { splitval[38] });
		          driver.findElement(By.xpath("//input[@fld_fqn='regAddr4']")).sendKeys(new CharSequence[] { splitval[39] });

		          waitforloader(driver);
		          
		          elem1 = new Select(driver.findElement(By.name("regCountryCode:input:dropdowncomponent")));  

		          elem1.selectByValue(splitval[40]);

		          new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElementLocated(By.name("regStateCode:input:dropdowncomponent"), splitval[41]));		          
		          elem1 = new Select(driver.findElement(By.name("regStateCode:input:dropdowncomponent")));  
		          elem1.selectByVisibleText(splitval[41]);
		          waitforloader(driver);

		          new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElementLocated(By.name("regCityCode:input:dropdowncomponent"), splitval[42]));
		          elem1 = new Select(driver.findElement(By.name("regCityCode:input:dropdowncomponent")));  
		          elem1.selectByVisibleText(splitval[42]);
		          //new WebDriverWait(driver, 2).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='flyout-mask']")));
		          //driver.findElement(By.xpath("//input[@fld_fqn='regZipCode']")).sendKeys(new CharSequence[] { splitval[43] });
		          
		          //List<WebElement> Checkbox = driver.findElements(By.xpath("//input[@type='checkbox']"));
		          //int a=0;
		          //while(a<Checkbox.size())
		          //{
		            //  WebElement El =Checkbox.get(a);   
		              //String id =El.getAttribute("name");
		              //driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
		              //if(id.equals(splitval[44]))
		              //{
		                //  Checkbox.get(a).click();
		                  //break;
		              //}
		              
		          //elem1 = new Select(driver.findElement(By.xpath("//input[@type='checkbox']")));  
		          //elem1.selectByVisibleText(splitval[44]);
		          if (splitval[44].equalsIgnoreCase("Y")) {
		        	  driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		          }
		          else 
		          {
		        	  driver.findElement(By.xpath("//input[@fld_fqn='mailAddr1']")).sendKeys(new CharSequence[] { splitval[45] });
	 		          driver.findElement(By.xpath("//input[@fld_fqn='mailAddr2']")).sendKeys(new CharSequence[] { splitval[46] });
	 		          driver.findElement(By.xpath("//input[@fld_fqn='mailAddr3']")).sendKeys(new CharSequence[] { splitval[47] });
	 		          driver.findElement(By.xpath("//input[@fld_fqn='mailAddr4']")).sendKeys(new CharSequence[] { splitval[48] });
	 		          elem1 = new Select(driver.findElement(By.name("mailCountryCode:input:dropdowncomponent")));  
	 		          elem1.selectByVisibleText(splitval[49]);
	 		          
	 		          new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElementLocated(By.name("mailStateCode:input:dropdowncomponent"), splitval[50]));		          
	 		          elem1 = new Select(driver.findElement(By.name("mailStateCode:input:dropdowncomponent")));  
	 		          elem1.selectByVisibleText(splitval[50]);
	 		          waitforloader(driver);
	 		          
	 		          new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElementLocated(By.name("mailCityCode:input:dropdowncomponent"), splitval[51]));		          
	 		          elem1 = new Select(driver.findElement(By.name("mailCityCode:input:dropdowncomponent")));  
	 		          elem1.selectByVisibleText(splitval[51]);  
		          }
		                    
		          //new WebDriverWait(driver, 2).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='flyout-mask']")));
		          //driver.findElement(By.xpath("//input[@fld_fqn='mailZipCode']")).sendKeys(new CharSequence[] { splitval[52] });
		          driver.findElement(By.xpath("//input[@fld_fqn='merEmailId']")).sendKeys(new CharSequence[] { splitval[53] });
		          driver.findElement(By.xpath("//input[@fld_fqn='merAlternateEmail']")).sendKeys(new CharSequence[] { splitval[54] });
		          driver.findElement(By.xpath("//input[@fld_fqn='mobileNo']")).sendKeys(new CharSequence[] { splitval[55] });
		          driver.findElement(By.xpath("//input[@fld_fqn='merPhoneNo']")).sendKeys(new CharSequence[] { splitval[56] });
		          driver.findElement(By.xpath("//input[@fld_fqn='merAlternatePhone']")).sendKeys(new CharSequence[] { splitval[57] });
		          driver.findElement(By.xpath("//input[@fld_fqn='merFaxNo']")).sendKeys(new CharSequence[] { splitval[58] });
		          driver.findElement(By.xpath("//input[@fld_fqn='merAlternateFax']")).sendKeys(new CharSequence[] { splitval[59] });
		          driver.findElement(By.linkText("Owner's Information(1)")).click();
		          
		          //first owner information starts
		          driver.findElement(By.xpath("//input[@fld_fqn='merOwner1Fname']")).sendKeys(new CharSequence[] { splitval[60] });
		          driver.findElement(By.xpath("//input[@fld_fqn='merOwner1Mname']")).sendKeys(new CharSequence[] { splitval[61] });
		          driver.findElement(By.xpath("//input[@fld_fqn='merOwner1Lname']")).sendKeys(new CharSequence[] { splitval[62] });
		          driver.findElement(By.xpath("//input[@fld_fqn='merOwner1LegalId']")).sendKeys(new CharSequence[] { splitval[63] });
		          driver.findElement(By.xpath("//input[@fld_fqn='merOwner1Addr1']")).sendKeys(new CharSequence[] { splitval[64] });
		          driver.findElement(By.xpath("//input[@fld_fqn='merOwner1Addr2']")).sendKeys(new CharSequence[] { splitval[65] });
		          driver.findElement(By.xpath("//input[@fld_fqn='merOwner1Addr3']")).sendKeys(new CharSequence[] { splitval[66] });
		          driver.findElement(By.xpath("//input[@fld_fqn='merOwner1Addr4']")).sendKeys(new CharSequence[] { splitval[67] });
		          waitforloader(driver);
		          elem1 = new Select(driver.findElement(By.name("owner1CountryCode:input:dropdowncomponent")));
		          elem1.selectByVisibleText(splitval[68]);
		          waitforloader(driver);		          
		          
		          new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElementLocated(By.name("owner1StateCode:input:dropdowncomponent"), splitval[69]));		          
		          elem1 = new Select(driver.findElement(By.name("owner1StateCode:input:dropdowncomponent")));  
		          elem1.selectByVisibleText(splitval[69]);
		          waitforloader(driver);
		          
		          new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElementLocated(By.name("owner1CityCode:input:dropdowncomponent"), splitval[70]));		          
		          elem1 = new Select(driver.findElement(By.name("owner1CityCode:input:dropdowncomponent")));  
		          elem1.selectByVisibleText(splitval[70]);
		          
		          //new WebDriverWait(driver, 2).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='flyout-mask']")));
		          //driver.findElement(By.xpath("//input[@fld_fqn='owner1ZipCode']")).sendKeys(new CharSequence[] { splitval[71] });
		          driver.findElement(By.xpath("//input[@fld_fqn='owner1Email']")).sendKeys(new CharSequence[] { splitval[72] });
		          driver.findElement(By.xpath("//input[@fld_fqn='owner1PhoneNo']")).sendKeys(new CharSequence[] { splitval[73] });
		          driver.findElement(By.xpath("//input[@fld_fqn='merOwner1LicenseId']")).sendKeys(new CharSequence[] { splitval[74] });

		          waitforloader(driver);
		          new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElementLocated(By.name("merOwner1LicenseCountry:input:dropdowncomponent"), splitval[75]));		          
		          elem1 = new Select(driver.findElement(By.name("merOwner1LicenseCountry:input:dropdowncomponent")));  
		          elem1.selectByVisibleText(splitval[75]);			          

		          waitforloader(driver);		          
		          
		          new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElementLocated(By.name("merOwner1LicenseCountry:input:dropdowncomponent"), splitval[75]));		          
		          elem1 = new Select(driver.findElement(By.name("merOwner1LicenseCountry:input:dropdowncomponent")));  
		          elem1.selectByVisibleText(splitval[75]);		          
		          
		          waitforloader(driver);

		          
		          new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElementLocated(By.name("merOwner1LicenseState:input:dropdowncomponent"), splitval[76]));		          
		          elem1 = new Select(driver.findElement(By.name("merOwner1LicenseState:input:dropdowncomponent")));  
		          elem1.selectByVisibleText(splitval[76]);
		          waitforloader(driver);
		          
		          new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElementLocated(By.name("merOwner1LicenseState:input:dropdowncomponent"), splitval[76]));		          
		          elem1 = new Select(driver.findElement(By.name("merOwner1LicenseState:input:dropdowncomponent")));  
		          elem1.selectByVisibleText(splitval[76]);	
		          waitforloader(driver);

		          //first owner information ends
		          
		          //second owner information starts
		          driver.findElement(By.xpath("//input[@fld_fqn='merOwner2Fname']")).sendKeys(new CharSequence[] { splitval[77] });
		          driver.findElement(By.xpath("//input[@fld_fqn='merOwner2Mname']")).sendKeys(new CharSequence[] { splitval[78] });
		          driver.findElement(By.xpath("//input[@fld_fqn='merOwner2Lname']")).sendKeys(new CharSequence[] { splitval[79] });
		          driver.findElement(By.xpath("//input[@fld_fqn='merOwner2LegalId']")).sendKeys(new CharSequence[] { splitval[80] });
		          driver.findElement(By.xpath("//input[@fld_fqn='merOwner2Addr1']")).sendKeys(new CharSequence[] { splitval[81] });
		          driver.findElement(By.xpath("//input[@fld_fqn='merOwner2Addr2']")).sendKeys(new CharSequence[] { splitval[82] });
		          driver.findElement(By.xpath("//input[@fld_fqn='merOwner2Addr3']")).sendKeys(new CharSequence[] { splitval[83] });
		          driver.findElement(By.xpath("//input[@fld_fqn='merOwner2Addr4']")).sendKeys(new CharSequence[] { splitval[84] });
		          waitforloader(driver);
		          elem1 = new Select(driver.findElement(By.name("owner2CountryCode:input:dropdowncomponent")));  
		          elem1.selectByVisibleText(splitval[85]);
		          
	          
		          new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElementLocated(By.name("owner2StateCode:input:dropdowncomponent"), splitval[86]));		          
		          elem1 = new Select(driver.findElement(By.name("owner2StateCode:input:dropdowncomponent")));  
		          elem1.selectByVisibleText(splitval[86]);
		          waitforloader(driver);
		          new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElementLocated(By.name("owner2CityCode:input:dropdowncomponent"), splitval[87]));		          
		          elem1 = new Select(driver.findElement(By.name("owner2CityCode:input:dropdowncomponent")));  
		          elem1.selectByVisibleText(splitval[87]);
		          
		          //new WebDriverWait(driver, 2).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='flyout-mask']")));
		          //driver.findElement(By.xpath("//input[@fld_fqn='owner2ZipCode']")).sendKeys(new CharSequence[] { splitval[88] });
		          driver.findElement(By.xpath("//input[@fld_fqn='owner2Email']")).sendKeys(new CharSequence[] { splitval[89] });
		          driver.findElement(By.xpath("//input[@fld_fqn='owner2PhoneNo']")).sendKeys(new CharSequence[] { splitval[90] });
		          driver.findElement(By.xpath("//input[@fld_fqn='merOwner2LicenseId']")).sendKeys(new CharSequence[] { splitval[91] });
    
		          elem1 = new Select(driver.findElement(By.name("merOwner2LicenseCountry:input:dropdowncomponent")));  
		          elem1.selectByVisibleText(splitval[92]);
		          waitforloader(driver);

		          new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElementLocated(By.name("merOwner2LicenseCountry:input:dropdowncomponent"), splitval[92]));		          
		          elem1 = new Select(driver.findElement(By.name("merOwner2LicenseCountry:input:dropdowncomponent")));  
		          elem1.selectByVisibleText(splitval[92]);
		          waitforloader(driver);

		          new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElementLocated(By.name("merOwner2LicenseState:input:dropdowncomponent"), splitval[93]));		          
		          elem1 = new Select(driver.findElement(By.name("merOwner2LicenseState:input:dropdowncomponent")));  
		          elem1.selectByVisibleText(splitval[93]);		
		          waitforloader(driver);
		          new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElementLocated(By.name("merOwner2LicenseState:input:dropdowncomponent"), splitval[93]));		          
		          elem1 = new Select(driver.findElement(By.name("merOwner2LicenseState:input:dropdowncomponent")));  
		          elem1.selectByVisibleText(splitval[93]);			          
	
		          //second owner information ends
		          
		          //third owner information starts
		          driver.findElement(By.xpath("//input[@fld_fqn='merOwner3Fname']")).sendKeys(new CharSequence[] { splitval[94] });
		          driver.findElement(By.xpath("//input[@fld_fqn='merOwner3Mname']")).sendKeys(new CharSequence[] { splitval[95] });
		          driver.findElement(By.xpath("//input[@fld_fqn='merOwner3Lname']")).sendKeys(new CharSequence[] { splitval[96] });
		          driver.findElement(By.xpath("//input[@fld_fqn='merOwner3LegalId']")).sendKeys(new CharSequence[] { splitval[97] });
		          driver.findElement(By.xpath("//input[@fld_fqn='merOwner3Addr1']")).sendKeys(new CharSequence[] { splitval[98] });
		          driver.findElement(By.xpath("//input[@fld_fqn='merOwner3Addr2']")).sendKeys(new CharSequence[] { splitval[99] });
		          driver.findElement(By.xpath("//input[@fld_fqn='merOwner3Addr3']")).sendKeys(new CharSequence[] { splitval[100] });
		          driver.findElement(By.xpath("//input[@fld_fqn='merOwner3Addr4']")).sendKeys(new CharSequence[] { splitval[101] });
		          
		          elem1 = new Select(driver.findElement(By.name("owner3CountryCode:input:dropdowncomponent")));  
		          elem1.selectByVisibleText(splitval[102]);
		          

		          
		          new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElementLocated(By.name("owner3StateCode:input:dropdowncomponent"), splitval[103]));		          
		          elem1 = new Select(driver.findElement(By.name("owner3StateCode:input:dropdowncomponent")));  
		          elem1.selectByVisibleText(splitval[103]);	
		          waitforloader(driver);
		          new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElementLocated(By.name("owner3CityCode:input:dropdowncomponent"), splitval[104]));		          
		          elem1 = new Select(driver.findElement(By.name("owner3CityCode:input:dropdowncomponent")));  
		          elem1.selectByVisibleText(splitval[104]);			          
		          
		          //new WebDriverWait(driver, 2).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='flyout-mask']")));
		          //driver.findElement(By.xpath("//input[@fld_fqn='owner3ZipCode']")).sendKeys(new CharSequence[] { splitval[105] });
		          driver.findElement(By.xpath("//input[@fld_fqn='owner3Email']")).sendKeys(new CharSequence[] { splitval[106] });
		          driver.findElement(By.xpath("//input[@fld_fqn='owner3PhoneNo']")).sendKeys(new CharSequence[] { splitval[107] });
		          driver.findElement(By.xpath("//input[@fld_fqn='merOwner3LicenseId']")).sendKeys(new CharSequence[] { splitval[108] });
        
		          elem1 = new Select(driver.findElement(By.name("merOwner3LicenseCountry:input:dropdowncomponent")));  
		          elem1.selectByVisibleText(splitval[109]);

		          
		          new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElementLocated(By.name("merOwner3LicenseCountry:input:dropdowncomponent"), splitval[109]));		          
		          elem1 = new Select(driver.findElement(By.name("merOwner3LicenseCountry:input:dropdowncomponent")));  
		          elem1.selectByVisibleText(splitval[109]);		          
		          waitforloader(driver);
		          
	          
		          new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElementLocated(By.name("merOwner3LicenseState:input:dropdowncomponent"), splitval[110]));		          
		          elem1 = new Select(driver.findElement(By.name("merOwner3LicenseState:input:dropdowncomponent")));  
		          elem1.selectByVisibleText(splitval[110]);
		          waitforloader(driver);
		          new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElementLocated(By.name("merOwner3LicenseState:input:dropdowncomponent"), splitval[110]));		          
		          elem1 = new Select(driver.findElement(By.name("merOwner3LicenseState:input:dropdowncomponent")));  
		          elem1.selectByVisibleText(splitval[110]);		

		          //third owner information ends
		          
		          //fourth owner information starts
		          driver.findElement(By.linkText("Owner's Information(2)")).click();
		          driver.findElement(By.xpath("//input[@fld_fqn='merOwner4Fname']")).sendKeys(new CharSequence[] { splitval[111] });
		          driver.findElement(By.xpath("//input[@fld_fqn='merOwner4Mname']")).sendKeys(new CharSequence[] { splitval[112] });
		          driver.findElement(By.xpath("//input[@fld_fqn='merOwner4Lname']")).sendKeys(new CharSequence[] { splitval[113] });
		          driver.findElement(By.xpath("//input[@fld_fqn='merOwner4LegalId']")).sendKeys(new CharSequence[] { splitval[114] });
		          driver.findElement(By.xpath("//input[@fld_fqn='merOwner4Addr1']")).sendKeys(new CharSequence[] { splitval[115] });
		          driver.findElement(By.xpath("//input[@fld_fqn='merOwner4Addr2']")).sendKeys(new CharSequence[] { splitval[116] });
		          driver.findElement(By.xpath("//input[@fld_fqn='merOwner4Addr3']")).sendKeys(new CharSequence[] { splitval[117] });
		          driver.findElement(By.xpath("//input[@fld_fqn='merOwner4Addr4']")).sendKeys(new CharSequence[] { splitval[118] });
		          waitforloader(driver);
		          elem1 = new Select(driver.findElement(By.name("owner4CountryCode:input:dropdowncomponent")));  
		          elem1.selectByVisibleText(splitval[119]);
		          
		          
		          new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElementLocated(By.name("owner4StateCode:input:dropdowncomponent"), splitval[120]));		          
		          elem1 = new Select(driver.findElement(By.name("owner4StateCode:input:dropdowncomponent")));  
		          elem1.selectByVisibleText(splitval[120]);
		          waitforloader(driver);
		          new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElementLocated(By.name("owner4CityCode:input:dropdowncomponent"), splitval[121]));		          
		          elem1 = new Select(driver.findElement(By.name("owner4CityCode:input:dropdowncomponent")));  
		          elem1.selectByVisibleText(splitval[121]);		          
		          
		          //driver.findElement(By.xpath("//input[@fld_fqn='owner4ZipCode']")).sendKeys(new CharSequence[] { splitval[122] });
		          driver.findElement(By.xpath("//input[@fld_fqn='owner4Email']")).sendKeys(new CharSequence[] { splitval[123] });
		          driver.findElement(By.xpath("//input[@fld_fqn='owner4PhoneNo']")).sendKeys(new CharSequence[] { splitval[124] });
		          driver.findElement(By.xpath("//input[@fld_fqn='merOwner4LicenseId']")).sendKeys(new CharSequence[] { splitval[125] });
         
		          elem1 = new Select(driver.findElement(By.name("merOwner4LicenseCountry:input:dropdowncomponent")));
		          elem1.selectByVisibleText(splitval[126]);
		          waitforloader(driver);
		          
		          new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElementLocated(By.name("merOwner4LicenseCountry:input:dropdowncomponent"), splitval[126]));		          
		          elem1 = new Select(driver.findElement(By.name("merOwner4LicenseCountry:input:dropdowncomponent")));  
		          elem1.selectByVisibleText(splitval[126]);
		          waitforloader(driver);
		          new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElementLocated(By.name("merOwner4LicenseState:input:dropdowncomponent"), splitval[127]));		          
		          elem1 = new Select(driver.findElement(By.name("merOwner4LicenseState:input:dropdowncomponent")));  
		          elem1.selectByVisibleText(splitval[127]);
		          waitforloader(driver);
		          new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElementLocated(By.name("merOwner4LicenseState:input:dropdowncomponent"), splitval[127]));		          
		          elem1 = new Select(driver.findElement(By.name("merOwner4LicenseState:input:dropdowncomponent")));  
		          elem1.selectByVisibleText(splitval[127]);		          

		          //fourth owner information ends
		          
		          //fifth owner information starts
		          driver.findElement(By.xpath("//input[@fld_fqn='merOwner5Fname']")).sendKeys(new CharSequence[] { splitval[128] });
		          driver.findElement(By.xpath("//input[@fld_fqn='merOwner5Mname']")).sendKeys(new CharSequence[] { splitval[129] });
		          driver.findElement(By.xpath("//input[@fld_fqn='merOwner5Lname']")).sendKeys(new CharSequence[] { splitval[130] });
		          driver.findElement(By.xpath("//input[@fld_fqn='merOwner5LegalId']")).sendKeys(new CharSequence[] { splitval[131] });
		          driver.findElement(By.xpath("//input[@fld_fqn='merOwner5Addr1']")).sendKeys(new CharSequence[] { splitval[132] });
		          driver.findElement(By.xpath("//input[@fld_fqn='merOwner5Addr2']")).sendKeys(new CharSequence[] { splitval[133] });
		          driver.findElement(By.xpath("//input[@fld_fqn='merOwner5Addr3']")).sendKeys(new CharSequence[] { splitval[134] });
		          driver.findElement(By.xpath("//input[@fld_fqn='merOwner5Addr4']")).sendKeys(new CharSequence[] { splitval[135] });
		          waitforloader(driver);
		          elem1 = new Select(driver.findElement(By.name("owner5CountryCode:input:dropdowncomponent")));  
		          elem1.selectByVisibleText(splitval[136]);
		          
		          new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElementLocated(By.name("owner5StateCode:input:dropdowncomponent"), splitval[137]));		          
		          elem1 = new Select(driver.findElement(By.name("owner5StateCode:input:dropdowncomponent")));  
		          elem1.selectByVisibleText(splitval[137]);
		          waitforloader(driver);
		          new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElementLocated(By.name("owner5CityCode:input:dropdowncomponent"), splitval[138]));		          
		          elem1 = new Select(driver.findElement(By.name("owner5CityCode:input:dropdowncomponent")));  
		          elem1.selectByVisibleText(splitval[138]);
		          waitforloader(driver);
		          new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElementLocated(By.name("owner5CityCode:input:dropdowncomponent"), splitval[138]));		          
		          elem1 = new Select(driver.findElement(By.name("owner5CityCode:input:dropdowncomponent")));  
		          elem1.selectByVisibleText(splitval[138]);		          
		          
		          //driver.findElement(By.xpath("//input[@fld_fqn='owner5ZipCode']")).sendKeys(new CharSequence[] { splitval[139] });
		          driver.findElement(By.xpath("//input[@fld_fqn='owner5Email']")).sendKeys(new CharSequence[] { splitval[140] });
		          driver.findElement(By.xpath("//input[@fld_fqn='owner5PhoneNo']")).sendKeys(new CharSequence[] { splitval[141] });
		          driver.findElement(By.xpath("//input[@fld_fqn='merOwner5LicenseId']")).sendKeys(new CharSequence[] { splitval[142] });
		          waitforloader(driver);
       
		          elem1 = new Select(driver.findElement(By.name("merOwner5LicenseCountry:input:dropdowncomponent")));
		          elem1.selectByVisibleText(splitval[143]);
		          waitforloader(driver);
		          
		          new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElementLocated(By.name("merOwner5LicenseCountry:input:dropdowncomponent"), splitval[143]));		          
		          elem1 = new Select(driver.findElement(By.name("merOwner5LicenseCountry:input:dropdowncomponent")));  
		          elem1.selectByVisibleText(splitval[143]);
		          waitforloader(driver);
		          new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElementLocated(By.name("merOwner5LicenseState:input:dropdowncomponent"), splitval[144]));		          
		          elem1 = new Select(driver.findElement(By.name("merOwner5LicenseState:input:dropdowncomponent")));  
		          elem1.selectByVisibleText(splitval[144]);
		          waitforloader(driver);
		          new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElementLocated(By.name("merOwner5LicenseState:input:dropdowncomponent"), splitval[144]));		          
		          elem1 = new Select(driver.findElement(By.name("merOwner5LicenseState:input:dropdowncomponent")));  
		          elem1.selectByVisibleText(splitval[144]);		          
           
		          //fifth owner information ends
		          
		          //Document, Plans & Settlement starts
		          WebDriverWait wait1 = new WebDriverWait(driver, 20);
		          wait1.until(ExpectedConditions.elementToBeClickable(By.linkText("Document,Plans & Settlement")));
		          driver.findElement(By.linkText("Document,Plans & Settlement")).click();
		          waitforloader(driver);
		          driver.findElement(By.name("appVerificationList:1:childdataPanel:inlineTable:container:dataList:0:colList:colHeaders:2:inputField:checkBoxComponent")).click();
		          driver.findElement(By.name("appVerificationList:1:childdataPanel:inlineTable:container:dataList:0:colList:colHeaders:3:inputField:input:inputTextField")).sendKeys(new CharSequence[] { splitval[150] });
		          driver.findElement((By.name("appVerificationList:1:childdataPanel:inlineTable:container:dataList:1:colList:colHeaders:2:inputField:checkBoxComponent"))).click();
		          driver.findElement(By.name("appVerificationList:1:childdataPanel:inlineTable:container:dataList:1:colList:colHeaders:3:inputField:input:inputTextField")).sendKeys(new CharSequence[] { splitval[151] });
		          driver.findElement(By.name("appVerificationList:1:childdataPanel:inlineTable:container:dataList:2:colList:colHeaders:2:inputField:checkBoxComponent")).click();
		          driver.findElement(By.name("appVerificationList:1:childdataPanel:inlineTable:container:dataList:2:colList:colHeaders:3:inputField:input:inputTextField")).sendKeys(new CharSequence[] { splitval[152] });
		          driver.findElement(By.name("appVerificationList:1:childdataPanel:inlineTable:container:dataList:3:colList:colHeaders:2:inputField:checkBoxComponent")).click();
		          driver.findElement(By.name("appVerificationList:1:childdataPanel:inlineTable:container:dataList:3:colList:colHeaders:3:inputField:input:inputTextField")).sendKeys(new CharSequence[] { splitval[153] });

		          waitforloader(driver);
		          driver.findElement(By.name("appVerificationList:1:childdataPanel:inlineTable:container:dataList:4:colList:colHeaders:2:inputField:checkBoxComponent")).click();
		          waitforloader(driver);
		          driver.findElement(By.name("appVerificationList:1:childdataPanel:inlineTable:container:dataList:4:colList:colHeaders:2:inputField:checkBoxComponent")).click();
		          driver.findElement(By.name("appVerificationList:1:childdataPanel:inlineTable:container:dataList:4:colList:colHeaders:3:inputField:input:inputTextField")).sendKeys(new CharSequence[] { splitval[154] });
		          //driver.findElement(By.name("merSettlementPeriod:input:dropdowncomponent")).click();
		          //driver.findElement(By.xpath("//input[@fld_fqn='merFaxNo']")).sendKeys(new CharSequence[] { splitval[154] });
		          elem1 = new Select(driver.findElement(By.name("merSettlementPeriod:input:dropdowncomponent")));  
		          elem1.selectByVisibleText(splitval[155]);	
		          driver.findElement(By.xpath("//input[@fld_fqn='merSettlValueDays']")).sendKeys(new CharSequence[] { splitval[157] });
		          elem1 = new Select(driver.findElement(By.name("merStatMode:input:dropdowncomponent")));  
		          elem1.selectByVisibleText(splitval[158]);
		          elem1 = new Select(driver.findElement(By.name("merStatLayoutId:input:dropdowncomponent")));  
		          elem1.selectByVisibleText(splitval[159]);
		          
		          driver.findElement(By.xpath("//a[@class='addR']")).click();
		          waitforloader(driver);
		          driver.switchTo().defaultContent();
		          driver.switchTo().frame(1);
		          elem1 = new Select(driver.findElement(By.xpath("(//span[@id='merSetlCurrency']/select)[1]")));

		          elem1.selectByVisibleText(splitval[160]);
		          elem1 = new Select(driver.findElement(By.name("tables:1:rows:1:cols:nextCol:colspanMarkup:inputField:input:dropdowncomponent")));  
		          elem1.selectByVisibleText(splitval[161]);
		          //driver.findElement(By.name("tables:1:rows:1:cols:colspanMarkup:inputField:input:dropdowncomponent")).sendKeys(new CharSequence[] { splitval[161] });
		          //driver.findElement(By.name("tables:1:rows:1:cols:nextCol:colspanMarkup:inputField:input:dropdowncomponent")).sendKeys(new CharSequence[] { splitval[162] });
		          //driver.findElement(By.name("tables:1:rows:2:cols:colspanMarkup:inputField:input:dropdowncomponent")).sendKeys(new CharSequence[] { splitval[161] }); not needed
		          //driver.findElement(By.name("tables:1:rows:2:cols:nextCol:colspanMarkup:inputField:input:inputTextField")).sendKeys(new CharSequence[] { splitval[163] });
		          //driver.findElement(By.name("tables:1:rows:3:cols:colspanMarkup:inputField:input:inputTextField")).sendKeys(new CharSequence[] { splitval[164] });
		          //driver.findElement(By.name("tables:1:rows:4:cols:colspanMarkup:inputField:input:inputTextField")).sendKeys(new CharSequence[] { splitval[165] });
		          //driver.findElement(By.name("tables:1:rows:4:cols:nextCol:colspanMarkup:inputField:input:inputTextField")).sendKeys(new CharSequence[] { splitval[166] });
		          //driver.findElement(By.name("tables:1:rows:5:cols:colspanMarkup:inputField:input:inputTextField")).sendKeys(new CharSequence[] { splitval[167] });
		          driver.findElement(By.name("tables:1:rows:5:cols:nextCol:colspanMarkup:inputField:input:inputTextField")).sendKeys(new CharSequence[] { splitval[168] });
		          //elem1 = new Select(driver.findElement(By.xpath("(//span[@class='btn_or_span']/input)[1]")));
		          driver.findElement(By.xpath("//input[@value='Save']")).click();
		          waitforloader(driver);
		          driver.switchTo().defaultContent();
		          driver.switchTo().frame(0);
		          
		          //Outlet info
		          if(splitval[3].equalsIgnoreCase("Y")) {
		          driver.findElement(By.linkText("Outlet Information")).click();
		          driver.findElement(By.xpath("//input[@fld_fqn='outletName']")).sendKeys(new CharSequence[] { splitval[169] });
		          elem1 = new Select(driver.findElement(By.name("outletType:input:dropdowncomponent")));  
		          elem1.selectByVisibleText(splitval[171]);
		          elem1 = new Select(driver.findElement(By.name("outletBusinessStatus:input:dropdowncomponent")));  
		          elem1.selectByVisibleText(splitval[172]);
		          //elem1 = new Select(driver.findElement(By.name("outletMccCode:input:dropdowncomponent")));  
		          //elem1.selectByVisibleText(splitval[173]);
		          
		          driver.findElement(By.xpath("//input[@fld_fqn='outletContactName']")).sendKeys(new CharSequence[] { splitval[174] });
		          driver.findElement(By.xpath("//input[@fld_fqn='outletMailAddr1']")).sendKeys(new CharSequence[] { splitval[175] });
		          driver.findElement(By.xpath("//input[@fld_fqn='outletMailAddr2']")).sendKeys(new CharSequence[] { splitval[176] });
		          driver.findElement(By.xpath("//input[@fld_fqn='outletMailAddr3']")).sendKeys(new CharSequence[] { splitval[177] });
		          driver.findElement(By.xpath("//input[@fld_fqn='outletMailAddr4']")).sendKeys(new CharSequence[] { splitval[178] });
		          elem1 = new Select(driver.findElement(By.name("outletMailCountryCode:input:dropdowncomponent")));  
		          elem1.selectByVisibleText(splitval[179]);
		          
		          new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElementLocated(By.name("outletMailStateCode:input:dropdowncomponent"), splitval[180]));		          
		          elem1 = new Select(driver.findElement(By.name("outletMailStateCode:input:dropdowncomponent")));  
		          elem1.selectByVisibleText(splitval[180]);
		          waitforloader(driver);
		          new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElementLocated(By.name("outletMailCityCode:input:dropdowncomponent"), splitval[181]));		          
		          elem1 = new Select(driver.findElement(By.name("outletMailCityCode:input:dropdowncomponent")));  
		          elem1.selectByVisibleText(splitval[181]);
		          
		          //new WebDriverWait(driver, 100).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='flyout-mask']")));
		          //driver.findElement(By.xpath("//input[@fld_fqn='outletMailZipCode']")).sendKeys(new CharSequence[] { splitval[182] });
		          driver.findElement(By.xpath("//input[@fld_fqn='outletEmailId']")).sendKeys(new CharSequence[] { splitval[183] });
		          driver.findElement(By.xpath("//input[@fld_fqn='outletAlternateEmail']")).sendKeys(new CharSequence[] { splitval[184] });
		          driver.findElement(By.xpath("//input[@fld_fqn='outletMobileNo']")).sendKeys(new CharSequence[] { splitval[185] });
		          driver.findElement(By.xpath("//input[@fld_fqn='outletPhoneNo']")).sendKeys(new CharSequence[] { splitval[186] });
		          driver.findElement(By.xpath("//input[@fld_fqn='outletAlternatePhone']")).sendKeys(new CharSequence[] { splitval[187] });
		          driver.findElement(By.xpath("//input[@fld_fqn='outletFaxNo']")).sendKeys(new CharSequence[] { splitval[188] });
		          driver.findElement(By.xpath("//input[@fld_fqn='outletAlternateFax']")).sendKeys(new CharSequence[] { splitval[189] });
		          waitforloader(driver);  

		          if (splitval[190].equalsIgnoreCase("C"))
		          {
		        	  driver.findElement(By.cssSelector("input[value='C']")).click();
		        	  waitforloader(driver);
		        	  driver.findElement(By.xpath("//*[@value='Submit']")).click();

		        	  System.out.println("Application Successfully Saved");
		        	  waitforloader(driver);
		          }
		          		else 
		          		{
				        	  driver.findElement(By.cssSelector("input[value='D']")).click();
				        	  waitforloader(driver);
				        	  waitforloader(driver);
					          new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//span[@id='outletSettlementPeriod']/..//*[@name = 'outletSettlementPeriod:input:dropdowncomponent']"), splitval[191]));		          
					          elem1 = new Select(driver.findElement(By.xpath("//span[@id='outletSettlementPeriod']/..//*[@name = 'outletSettlementPeriod:input:dropdowncomponent']")));  
					          elem1.selectByVisibleText(splitval[191]);
					          driver.findElement(By.xpath("//span[@id='outletSettlValDays']/..//*[@name = 'outletSettlValDays:input:inputTextField']")).sendKeys(new CharSequence[] { splitval[193] });
		
				              elem1 = new Select(driver.findElement(By.name("outletStatLayoutID:input:dropdowncomponent")));  
				              elem1.selectByVisibleText(splitval[195]);
		
				        	  elem1 = new Select(driver.findElement(By.name("outletStatMode:input:dropdowncomponent")));  
				              elem1.selectByVisibleText(splitval[194]);
				              waitforloader(driver);
				              
				              WebElement element = driver.findElement(By.xpath("//span[@id = 'appOutletSetlAccount']/..//*[@class='addR']"));
				              		              
				              JavascriptExecutor executor = (JavascriptExecutor)driver;
				              executor.executeScript("arguments[0].click();", element);
				              driver.switchTo().defaultContent();
					          driver.switchTo().frame(1);
					          elem1 = new Select(driver.findElement(By.name("tables:1:rows:1:cols:colspanMarkup:inputField:input:dropdowncomponent")));
					          elem1.selectByVisibleText(splitval[196]);
					          elem1 = new Select(driver.findElement(By.name("tables:1:rows:1:cols:nextCol:colspanMarkup:inputField:input:dropdowncomponent")));  
					          elem1.selectByVisibleText(splitval[197]);
					          //driver.findElement(By.name("tables:1:rows:1:cols:colspanMarkup:inputField:input:dropdowncomponent")).sendKeys(new CharSequence[] { splitval[161] });
					          //driver.findElement(By.name("tables:1:rows:1:cols:nextCol:colspanMarkup:inputField:input:dropdowncomponent")).sendKeys(new CharSequence[] { splitval[162] });
					          //driver.findElement(By.name("tables:1:rows:2:cols:colspanMarkup:inputField:input:dropdowncomponent")).sendKeys(new CharSequence[] { splitval[161] }); not needed
					          //driver.findElement(By.name("tables:1:rows:2:cols:nextCol:colspanMarkup:inputField:input:inputTextField")).sendKeys(new CharSequence[] { splitval[163] });
					          //driver.findElement(By.name("tables:1:rows:3:cols:colspanMarkup:inputField:input:inputTextField")).sendKeys(new CharSequence[] { splitval[164] });
					          //driver.findElement(By.name("tables:1:rows:4:cols:colspanMarkup:inputField:input:inputTextField")).sendKeys(new CharSequence[] { splitval[165] });
					          //driver.findElement(By.name("tables:1:rows:4:cols:nextCol:colspanMarkup:inputField:input:inputTextField")).sendKeys(new CharSequence[] { splitval[166] });
					          //driver.findElement(By.name("tables:1:rows:5:cols:colspanMarkup:inputField:input:inputTextField")).sendKeys(new CharSequence[] { splitval[167] });
					          driver.findElement(By.name("tables:1:rows:5:cols:nextCol:colspanMarkup:inputField:input:inputTextField")).sendKeys(new CharSequence[] { splitval[204] });
					          //elem1 = new Select(driver.findElement(By.xpath("(//span[@class='btn_or_span']/input)[1]")));
					          driver.findElement(By.xpath("//input[@value='Save']")).click();
					          //driver.findElement(By.name("save")).click();
					          waitforloader(driver);
					          driver.switchTo().defaultContent();
					          driver.switchTo().frame(0);

				              driver.findElement(By.xpath("//*[@value='Submit']")).click();
				              waitforloader(driver);
							  
				              
				             
				              }
		          		
	              }
		          else {
		        	  driver.findElement(By.xpath("//*[@value='Submit']")).click();
		        	  waitforloader(driver);  
		          }
		          //driver.switchTo().defaultContent();
		          //driver.switchTo().frame(0);
		          try
	              {
		        	  		//Boolean isPresent = driver.findElements(By.xpath("//*[@class='feedbackPanelERROR']")).size() > 0;
		        	  		//Boolean isPresent = driver.findElements(By.yourLocator).size() > 0
		        	  		//String isPresent = driver.findElement(By.xpath("//*[contains(@id='id4')][@class='feedbackPanelERROR']")).getText();
		        	  		
		        	  		//Boolean isPresent = driver.findElement(By.xpath("//*[@class='feedbackPanelERROR']/..//*[@id='id4b']")).isSelected();
		        	  		//if (isPresent==true)
		        	  	
		        	  			//if(driver.findElements(By.xpath("//*[contains(@id, 'id4')]//*[contains(@class, 'feedbackPanelERROR')]/ul/li/span")).size() != 0)
		        	  			
		        	  		//*[contains(@id, 'ctl00_btnAircraftMapCell')]//*[contains(@title, 'Select Seat')]
		        	  	//if(driver.findElements(By.xpath("//*[@id='id2f']/ul/li/span")).size() > 0)
		        	  	
		        	  //new WebDriverWait(driver, 2).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='flyout-mask']")));
		        	//if(driver.findElements(By.xpath("//*[@id='id2f']/ul/li/span")).size() !=0)
		        		//if(driver.findElements(By.xpath("//*[@class='feedbackPanel']")).size() == 0)
		        		  //if(driver.findElements(By.xpath("//*[@id='id2f']/ul/li/span")).size() == 0)
		        			//if(driver.findElement(By.xpath("//*[@value='Submit']")).isDisplayed())
	            	  		
	            		  		//if(!(driver.findElements(By.xpath("//*[@id='id2f']/ul/li/span")).size() > 0))
		        	  			//if(!(driver.findElements(By.xpath("//*[@id='id4b']/ul/li/span")).size() > 0))
		        	  			//driver.findElement(By.xpath("//*[starts-with(@id,'id')/..//*[@class='feedbackPanelERROR']/ul/li/span"));
		        	  			
		        	  		//	if(!(driver.findElements(By.xpath("//*[contains(@id='id4')][@class='feedbackPanelERROR']")).size() > 0))
		        	  //driver.findElement(By.xpath("//input[contains(@id,'alpaca')][@name='4001']")).click();
		        	  if(driver.findElements(By.className("feedbackPanelERROR")).size() > 0)		
		        	  {
		        	  		System.out.println("Element present");
		        	  			//driver.switchTo().defaultContent();
						        //driver.switchTo().frame(0);
						        //WebElement e2 = driver.findElement(By.xpath("//input[starts-with(@id,'id')/..//*[@class='feedbackPanelERROR']/ul/li/span"));
	            		  		//WebElement e2 = driver.findElement(By.xpath("//*[@class='feedbackPanelERROR']/ul/li/span"));
		        	  			//WebElement e2 = driver.findElement(By.xpath("//*[@id='id4b']/ul/li/span"));
		        	  			//WebElement e2 = driver.findElement(By.xpath("//*[contains(@id='id4')][@class='feedbackPanelERROR']"));
		        	  			//WebElement e2 = driver.findElement(By.xpath("//ul[contains(@class='feedbackPanel')]/..//*li[contains(@class='feedbackPanelERROR')]"));
						        WebElement e2 = driver.findElement(By.className("feedbackPanelERROR"));
						        //driver.findElement(By.xpath("//input[contains(@id,'alpaca')][@name='4001']")).click();
		        	  			//WebElement e2 = driver.findElement(By.xpath("//*[contains(@id, 'id4')]//*[contains(@class, 'feedbackPanelERROR')]/ul/li/span"));
						        //WebElement e2 = driver.findElement(By.xpath("//input[starts-with(@id,'id')/..//*[@class='feedbackPanelERROR']/ul/li/span"));
	            		  		String error = e2.getAttribute("class");
	            		  		error = e2.getText();
	            		  		Cell cell5 = row.createCell(205);
	            		  		cell5.setCellValue("N");
					         	Cell cell1 = row.createCell(206);
					         	cell1.setCellValue(error);
					         	System.out.println("Error");
				         	 //driver.findElement(By.xpath("//body")).sendKeys(Keys.chord(Keys.CONTROL, Keys.HOME));
				         	 
				              //WebElement element = driver.findElement(By.xpath("//div[@class='w_caption']/..//a[@class='w_close']"));
				         	  WebElement element = driver.findElement(By.xpath("//*[@name='cancel']"));
				              element.click();
				         	  //JavascriptExecutor executor = (JavascriptExecutor)driver;
				              //executor.executeScript("arguments[0].click();", element);				         	  
				         	  
				         	  //driver.findElement(By.xpath("//*[@id='_wicket_window_2']/a")).click();
				         	  driver.switchTo().defaultContent();
				         	  Thread.sleep(1000);
		        	  }	
		        	  else
		        	  {
		        		  driver.switchTo().defaultContent();
		            	  //WebElement el = driver.findElement(By.xpath("//*[@id='id2f']/ul/li/span"));
		            	  //WebElement el = driver.findElement(By.xpath("//ul[@class='feedbackPanel' and ./li[contains(@class='feedbackPanelINFO')]]"));
		            	  //WebElement el = driver.findElement(By.xpath("//*[contains(@id, 'id2')]//*[contains(@class, 'feedbackPanelINFO')]/ul/li/span"));
		            	  WebElement el = driver.findElement(By.className("feedbackPanelINFO"));
		            	  String value = el.getAttribute("value");
						  value = el.getText();
					  	  System.out.println(value);
					  	  Cell cell5 = row.createCell(205);
					  	  cell5.setCellValue("N");
					  	  Cell cell1 = row.createCell(206);
					  	  cell1.setCellValue(value);
		        	  }
		        	  		/*else {
		        	  			waitforloader(driver);  	
	  		            	  //driver.switchTo().defaultContent();
	  		            	  WebElement el = driver.findElement(By.xpath("//*[@class='feedbackPanel']/..//*[@id='id2f']"));	
	  		            	  //WebElement el = driver.findElement(By.xpath("//*[@id='id2f']/ul/li/span"));
							  	String value = el.getAttribute("value");
		            	  		value = el.getText();
						  		System.out.println(value);
				        		Cell cell5 = row.createCell(205);
					  			cell5.setCellValue("N");
					  			Cell cell1 = row.createCell(206);
					  			cell1.setCellValue(value);
	            	  			
	            	  		}*/
	            	  			
	              }
	              catch (Exception e)
	              {
	            	        	e.printStackTrace();
	              }
      				          
		          
		    
		    inputStream.close();
            FileOutputStream outputStream = new FileOutputStream(file);
            newWorkbook.write(outputStream);
            outputStream.close();
            
            break;
          
          }

        }
      
    
    }
    	catch (Exception e)
    {
      e.printStackTrace();
    }
  }

public static void waitforloader(WebDriver driver) {
    driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    new WebDriverWait(driver, 20).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='flyout-mask']")));
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
}
}
