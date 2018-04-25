package TestCases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import POMPack.LoginPage;

public class Testcase1 {
	public static ResultSet TestStepsResults = null;
	/* public static Connection conn = null;
	public static Statement stmtTests = null;	
	public static Statement stmtSteps = null;	
	public static Statement stmt = null;
	public static Statement stmtsid = null;
	public static Statement stmtSDS = null;
	public static Statement stmtTDS = null; */
	public static void main(String[] args) throws SQLException {
		
		String browser = "";
		String filePathGecko = System.getProperty("user.dir") + "\\Lib";
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", filePathGecko + "\\chromedriver.exe");					

				
		WebDriver driver = new ChromeDriver();
		browser = "CHROME";
	      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	      driver.manage().timeouts().pageLoadTimeout(500,TimeUnit.SECONDS);
	      driver.manage().window().maximize();
	      
	      LoginPage Login = new LoginPage(driver);
	      
	      
	      try
	      {
	      	String val = "";
	        String filename = "Datasheet.xls";
	        String filePath = System.getProperty("user.dir") + "\\excelIO";	    
	        String sheetName = "DataSheet";
	        
	      	File file = new File(filePath + "\\" + filename);
	      	FileInputStream inputStream = new FileInputStream(file);
	      	Workbook newWorkbook = null;
	      	String fileExtensionName = filename.substring(filename.indexOf("."));
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

	      		if ((row.getCell(205) == null) || (row.getCell(205).getStringCellValue().equalsIgnoreCase("Y")) || (row.getCell(205).getStringCellValue().equalsIgnoreCase("Yes")))
	      		{
	      			int lastcolval = row.getLastCellNum();
	      			for (int j = 0; j < lastcolval; j++)
	      			{
	      				System.out.print(row.getCell(j) + "||");
	              
	      				val = val + row.getCell(j) + "@@";
	      				
	      				String[] splitval = val.split("@@");
	      		      
	      			}
	      		      String uname = row.getCell(208).getStringCellValue();
	      		      String pwd = row.getCell(209).getStringCellValue();
	      		      String urllink = row.getCell(210).getStringCellValue();
	      		      
	      		      driver.get(urllink);
	      		      
	      		      Login.typeusername(uname);
	      		      Login.typepassword(pwd);
	      		      Login.clickloginbutton();
	              
	      			
	      
	      		}
	      	}
	      	
		    inputStream.close();
            //FileOutputStream outputStream = new FileOutputStream(file);
            //newWorkbook.write(outputStream);
            //outputStream.close();
            
	      }catch(Exception e){
	    	e.printStackTrace();  
	      }
/*			String Keyword = "";
			String Test_Id = "TS3";
			String Workflow_Name = "";			
			String SharedDataSet = "";
			String TestDataSet = "";
			//TestStepsResults = TestAttributes.stmtSteps.executeQuery("SELECT * FROM SQS_TEST_STEPS where SQS_TS_Test_Id = '" + TestAttributes.Test_Id + "' and Upper(SQS_TS_Run_Type) LIKE 'YES' and SQS_TS_Step_Id >= " + StartStepID + " order by SQS_TS_Step_Id");
			String StepSelectionQuery = "SELECT * FROM SQS_TEST_STEPS where SQS_TS_Test_Id = '" + Test_Id + "' and upper(SQS_TS_Run_Type) LIKE '%YES%' and SQS_TS_Step_Id >= 0 and SQS_TS_Step_Id not in (Select SQS_TW_Step_Id from SQS_TEST_WORKFLOW where SQS_TW_Test_Id = '" + Test_Id + "' and SQS_TW_Workflow_Name = '" + Workflow_Name + "' and upper(SQS_TW_Test_Data) = 'NOTAPPLICABLE') order by SQS_TS_Step_Id";
			TestStepsResults = Functionlib.stmtSteps.executeQuery(StepSelectionQuery);
			while (TestStepsResults.next()) {
				
				
			}*/
				
	      
	      
	      
	}

}
