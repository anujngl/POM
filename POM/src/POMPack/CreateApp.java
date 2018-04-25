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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import TestCases.Testcase1;

public class CreateApp 
{
	WebDriver driver;
	By PlusButton = By.xpath("//a[@class='addR']");
	By ContactPerson = By.xpath("//input[@fld_fqn='merContactName']");
	By ApplicantName = By.xpath("//input[@fld_fqn='merName']");
	By MerchantType = By.name("merType:input:dropdowncomponent");
	By CreateOutletYes = By.cssSelector("input[value='Y']");
	By CreateOutletNo = By.cssSelector("input[value='N']");
	
	/****************************
	General 
	****************************/
	By BusinessName = By.xpath("//input[@fld_fqn='merBussName']");
	By RegdCompanyName = By.xpath("//input[@fld_fqn='merCompanyName']");
	By HubName = By.name("hubCode:input:dropdowncomponent");
	By ChainName = By.name("chainCode:input:dropdowncomponent");
	By MCC = By.name("merMcc:input:dropdowncomponent");
	By OpeningDate = By.name("foundingDate:input:dateTextField");
	By BusinessStatus = By.name("businessStatus:input:dropdowncomponent");
	By PreferredLang = By.name("languagePreferences:input:dropdowncomponent");
	By YearOfEstablishment = By.xpath("//input[@fld_fqn='establishedYear']");
	By EstablishmentNumber = By.xpath("//input[@fld_fqn='shopEstaNo']");
	By EstablishmentType = By.name("establishmentType:input:dropdowncomponent");
	By OfficeOwnership = By.name("merOfficeType:input:dropdowncomponent");
	By OfficeTenure = By.xpath("//input[@fld_fqn='merOfficeTenure']");
	By ResTenure = By.xpath("//input[@fld_fqn='merResiTenure']");
	By MercResidenceType = By.name("merResiType:input:dropdowncomponent");
	By BankName = By.xpath("//input[@fld_fqn='merBankName']");
	By BranchName = By.xpath("//input[@fld_fqn='merBranchName']");
	By BranchCode = By.xpath("//input[@fld_fqn='merBranchCode']");
	By AccountTypeChecking = By.cssSelector("input[value='2']");
	By MerAcctNumber = By.xpath("//input[@fld_fqn='merAccountNo']");
	By IbanNumber = By.xpath("//input[@fld_fqn='merIban']");
	By remark = By.xpath("//*[@name='remark:input:textAreaComponent']");
	
	/****************************
	BusinessDetails
	****************************/
	By BusinessDetailsLink = By.linkText("Business Details");
	By AnnualBusinessIncome = By.xpath("//input[@fld_fqn='merAnnualIncome']");
	By AverageBillAmount = By.xpath("//input[@fld_fqn='averageBillAmount']");
	By EstimatedAnnualTurnOver = By.xpath("//input[@fld_fqn='merAnnualTurnover']");
	By ExpectedCardPymntTO = By.xpath("//input[@fld_fqn='expectedCardBusiness']");
	By StateTaxNo = By.xpath("//input[@fld_fqn='saleTaxNo']");
	By NationalTaxID = By.xpath("//input[@fld_fqn='merNatTaxId']");
	By LegalId = By.xpath("//input[@fld_fqn='merLegalId']");
	By TurnOver1 = By.xpath("//input[@fld_fqn='turnoverAmt1']");
	By TurnOver2 = By.xpath("//input[@fld_fqn='turnoverAmt2']");
	By TurnOver3 = By.xpath("//input[@fld_fqn='turnoverAmt3']");
	By RegdAddr1 = By.xpath("//input[@fld_fqn='regAddr1']");
	By RegdAddr2 = By.xpath("//input[@fld_fqn='regAddr2']");
	By RegdAddr3 = By.xpath("//input[@fld_fqn='regAddr3']");
	By RegdAddr4 = By.xpath("//input[@fld_fqn='regAddr4']");
	By RegdAddrCountry = By.name("regCountryCode:input:dropdowncomponent");
	By RegdAddrState = By.name("regStateCode:input:dropdowncomponent");
	By RegdAddrCity = By.name("regCityCode:input:dropdowncomponent");
	By SameAsRegdMailAddr = By.xpath("//input[@type='checkbox']");
	By MailingAddr1 = By.xpath("//input[@fld_fqn='mailAddr1']");
	By MailingAddr2 = By.xpath("//input[@fld_fqn='mailAddr2']");
	By MailingAddr3 = By.xpath("//input[@fld_fqn='mailAddr3']");
	By MailingAddr4 = By.xpath("//input[@fld_fqn='mailAddr4']");
	By MailingAddrCountry = By.name("mailCountryCode:input:dropdowncomponent");
	By MailingAddrState = By.name("mailStateCode:input:dropdowncomponent");
	By MailingAddrCity = By.name("mailCityCode:input:dropdowncomponent");
	By BDEmailID = By.xpath("//input[@fld_fqn='merEmailId']");
	By BDAlternateEmail = By.xpath("//input[@fld_fqn='merAlternateEmail']");
	By BDMobileNo = By.xpath("//input[@fld_fqn='mobileNo']");
	By BDPhoneNo = By.xpath("//input[@fld_fqn='merPhoneNo']");
	By BDAlternatePhoneNo = By.xpath("//input[@fld_fqn='merAlternatePhone']");
	By BDFaxNo = By.xpath("//input[@fld_fqn='merFaxNo']");
	By BDMerAlternateFaxNo = By.xpath("//input[@fld_fqn='merAlternateFax']");
	
	/****************************
	OwnerInfo1
	****************************/	
	By OwnerInfo1Link = By.linkText("Owner's Information(1)");
	By Mer1OwnerFirstName = By.xpath("//input[@fld_fqn='merOwner1Fname']");
	By Mer1OwnerMiddleName = By.xpath("//input[@fld_fqn='merOwner1Mname']");
	By Mer1OwnerLastName = By.xpath("//input[@fld_fqn='merOwner1Lname']");
	By Mer1OwnerLegalId = By.xpath("//input[@fld_fqn='merOwner1LegalId']");
	By Mer1OwnerAddr1 = By.xpath("//input[@fld_fqn='merOwner1Addr1']");
	By Mer1OwnerAddr2 = By.xpath("//input[@fld_fqn='merOwner1Addr2']");
	By Mer1OwnerAddr3 = By.xpath("//input[@fld_fqn='merOwner1Addr3']");
	By Mer1OwnerAddr4 = By.xpath("//input[@fld_fqn='merOwner1Addr4']");
	By Mer1OwnerCountry = By.name("owner1CountryCode:input:dropdowncomponent");
	By Mer1OwnerState = By.name("owner1StateCode:input:dropdowncomponent");
	By Mer1OwnerCity = By.name("owner1CityCode:input:dropdowncomponent");
	By Mer1OwnerEmail = By.xpath("//input[@fld_fqn='owner1Email']");
	By Mer1OwnerPhoneNo = By.xpath("//input[@fld_fqn='owner1PhoneNo']");
	By Mer1OwnerDrivingLicense = By.xpath("//input[@fld_fqn='merOwner1LicenseId']");
	By Mer1OwnerCountryofLicense = By.name("merOwner1LicenseCountry:input:dropdowncomponent");
	By Mer1OwnerStateofLicense = By.name("merOwner1LicenseState:input:dropdowncomponent");
	
	By Mer2OwnerFirstName = By.xpath("//input[@fld_fqn='merOwner2Fname']");
	By Mer2OwnerMiddleName = By.xpath("//input[@fld_fqn='merOwner2Mname']");
	By Mer2OwnerLastName = By.xpath("//input[@fld_fqn='merOwner2Lname']");
	By Mer2OwnerLegalId = By.xpath("//input[@fld_fqn='merOwner2LegalId']");
	By Mer2OwnerAddr1 = By.xpath("//input[@fld_fqn='merOwner2Addr1']");
	By Mer2OwnerAddr2 = By.xpath("//input[@fld_fqn='merOwner2Addr2']");
	By Mer2OwnerAddr3 = By.xpath("//input[@fld_fqn='merOwner2Addr3']");
	By Mer2OwnerAddr4 = By.xpath("//input[@fld_fqn='merOwner2Addr4']");
	By Mer2OwnerCountry = By.name("owner2CountryCode:input:dropdowncomponent");
	By Mer2OwnerState = By.name("owner2StateCode:input:dropdowncomponent");
	By Mer2OwnerCity = By.name("owner2CityCode:input:dropdowncomponent");
	By Mer2OwnerEmail = By.xpath("//input[@fld_fqn='owner2Email']");
	By Mer2OwnerPhoneNo = By.xpath("//input[@fld_fqn='owner2PhoneNo']");
	By Mer2OwnerDrivingLicense = By.xpath("//input[@fld_fqn='merOwner2LicenseId']");
	By Mer2OwnerCountryofLicense = By.name("merOwner2LicenseCountry:input:dropdowncomponent");
	By Mer2OwnerStateofLicense = By.name("merOwner2LicenseState:input:dropdowncomponent");
	   
	By Mer3OwnerFirstName = By.xpath("//input[@fld_fqn='merOwner3Fname']");
	By Mer3OwnerMiddleName = By.xpath("//input[@fld_fqn='merOwner3Mname']");
	By Mer3OwnerLastName = By.xpath("//input[@fld_fqn='merOwner3Lname']");
	By Mer3OwnerLegalId = By.xpath("//input[@fld_fqn='merOwner3LegalId']");
	By Mer3OwnerAddr1 = By.xpath("//input[@fld_fqn='merOwner3Addr1']");
	By Mer3OwnerAddr2 = By.xpath("//input[@fld_fqn='merOwner3Addr2']");
	By Mer3OwnerAddr3 = By.xpath("//input[@fld_fqn='merOwner3Addr3']");
	By Mer3OwnerAddr4 = By.xpath("//input[@fld_fqn='merOwner3Addr4']");
	By Mer3OwnerCountry = By.name("owner3CountryCode:input:dropdowncomponent");
	By Mer3OwnerState = By.name("owner3StateCode:input:dropdowncomponent");
	By Mer3OwnerCity = By.name("owner3CityCode:input:dropdowncomponent");
	By Mer3OwnerEmail = By.xpath("//input[@fld_fqn='owner3Email']");
	By Mer3OwnerPhoneNo = By.xpath("//input[@fld_fqn='owner3PhoneNo']");
	By Mer3OwnerDrivingLicense = By.xpath("//input[@fld_fqn='merOwner3LicenseId']");
	By Mer3OwnerCountryofLicense = By.name("merOwner3LicenseCountry:input:dropdowncomponent");
	By Mer3OwnerStateofLicense = By.name("merOwner3LicenseState:input:dropdowncomponent");
	   
	
	/****************************
	OwnerInfo2
	****************************/	
	By OwnerInfo2Link = By.linkText("Owner's Information(2)");
	By Mer4OwnerFirstName = By.xpath("//input[@fld_fqn='merOwner4Fname']");
	By Mer4OwnerMiddleName = By.xpath("//input[@fld_fqn='merOwner4Mname']");
	By Mer4OwnerLastName = By.xpath("//input[@fld_fqn='merOwner4Lname']");
	By Mer4OwnerLegalId = By.xpath("//input[@fld_fqn='merOwner4LegalId']");
	By Mer4OwnerAddr1 = By.xpath("//input[@fld_fqn='merOwner4Addr1']");
	By Mer4OwnerAddr2 = By.xpath("//input[@fld_fqn='merOwner4Addr2']");
	By Mer4OwnerAddr3 = By.xpath("//input[@fld_fqn='merOwner4Addr3']");
	By Mer4OwnerAddr4 = By.xpath("//input[@fld_fqn='merOwner4Addr4']");
	By Mer4OwnerCountry = By.name("owner4CountryCode:input:dropdowncomponent");
	By Mer4OwnerState = By.name("owner4StateCode:input:dropdowncomponent");
	By Mer4OwnerCity = By.name("owner4CityCode:input:dropdowncomponent");
	By Mer4OwnerEmail = By.xpath("//input[@fld_fqn='owner4Email']");
	By Mer4OwnerPhoneNo = By.xpath("//input[@fld_fqn='owner4PhoneNo']");
	By Mer4OwnerDrivingLicense = By.xpath("//input[@fld_fqn='merOwner4LicenseId']");
	By Mer4OwnerCountryofLicense = By.name("merOwner4LicenseCountry:input:dropdowncomponent");
	By Mer4OwnerStateofLicense = By.name("merOwner4LicenseState:input:dropdowncomponent");
	   
	By Mer5OwnerFirstName = By.xpath("//input[@fld_fqn='merOwner5Fname']");
	By Mer5OwnerMiddleName = By.xpath("//input[@fld_fqn='merOwner5Mname']");
	By Mer5OwnerLastName = By.xpath("//input[@fld_fqn='merOwner5Lname']");
	By Mer5OwnerLegalId = By.xpath("//input[@fld_fqn='merOwner5LegalId']");
	By Mer5OwnerAddr1 = By.xpath("//input[@fld_fqn='merOwner5Addr1']");
	By Mer5OwnerAddr2 = By.xpath("//input[@fld_fqn='merOwner5Addr2']");
	By Mer5OwnerAddr3 = By.xpath("//input[@fld_fqn='merOwner5Addr3']");
	By Mer5OwnerAddr4 = By.xpath("//input[@fld_fqn='merOwner5Addr4']");
	By Mer5OwnerCountry = By.name("owner5CountryCode:input:dropdowncomponent");
	By Mer5OwnerState = By.name("owner5StateCode:input:dropdowncomponent");
	By Mer5OwnerCity = By.name("owner5CityCode:input:dropdowncomponent");
	By Mer5OwnerEmail = By.xpath("//input[@fld_fqn='owner5Email']");
	By Mer5OwnerPhoneNo = By.xpath("//input[@fld_fqn='owner5PhoneNo']");
	By Mer5OwnerDrivingLicense = By.xpath("//input[@fld_fqn='merOwner5LicenseId']");
	By Mer5OwnerCountryofLicense = By.name("merOwner5LicenseCountry:input:dropdowncomponent");
	By Mer5OwnerStateofLicense = By.name("merOwner5LicenseState:input:dropdowncomponent");
	
	/****************************
	Docs plan & info
	****************************/
	
	By EmiratesID = By.name("appVerificationList:1:childdataPanel:inlineTable:container:dataList:0:colList:colHeaders:2:inputField:checkBoxComponent");
	By EmiratesIDDetail = By.name("appVerificationList:1:childdataPanel:inlineTable:container:dataList:0:colList:colHeaders:3:inputField:input:inputTextField");
	By Passport = By.name("appVerificationList:1:childdataPanel:inlineTable:container:dataList:1:colList:colHeaders:2:inputField:checkBoxComponent");
	By PassportDetail = By.name("appVerificationList:1:childdataPanel:inlineTable:container:dataList:1:colList:colHeaders:3:inputField:input:inputTextField");
	By TradeLicense = By.name("appVerificationList:1:childdataPanel:inlineTable:container:dataList:2:colList:colHeaders:2:inputField:checkBoxComponent");
	By TradeLicenseDetail = By.name("appVerificationList:1:childdataPanel:inlineTable:container:dataList:2:colList:colHeaders:3:inputField:input:inputTextField");
	By VISA = By.name("appVerificationList:1:childdataPanel:inlineTable:container:dataList:3:colList:colHeaders:2:inputField:checkBoxComponent");
	By VISADetail = By.name("appVerificationList:1:childdataPanel:inlineTable:container:dataList:3:colList:colHeaders:3:inputField:input:inputTextField");
	By Poasport = By.name("appVerificationList:1:childdataPanel:inlineTable:container:dataList:4:colList:colHeaders:2:inputField:checkBoxComponent");
	By PoasportDetail = By.name("appVerificationList:1:childdataPanel:inlineTable:container:dataList:4:colList:colHeaders:3:inputField:input:inputTextField");
	By MerSettPeriodicity = By.name("appVerificationList:1:childdataPanel:inlineTable:container:dataList:4:colList:colHeaders:3:inputField:input:inputTextField");
	By MerSettValueDays = By.xpath("//input[@fld_fqn='merSettlValueDays']");
	By MerSettStateMode = By.name("merStatMode:input:dropdowncomponent");
	By MerSettStateLayout = By.name("merStatLayoutId:input:dropdowncomponent");
	By MerSettlementDetails = By.xpath("//a[@class='addR']");
	By MerSettCurrency = By.xpath("(//span[@id='merSetlCurrency']/select)[1]");
	By MerSettPayableTo = By.name("tables:1:rows:1:cols:nextCol:colspanMarkup:inputField:input:dropdowncomponent");
	By MerSettSave = By.xpath("//input[@value='Save']");

	/****************************
	OutletInfo
	****************************/	
	By OutletInfoLink = By.linkText("Outlet Information");
	By OutletName = By.xpath("//input[@fld_fqn='outletName']");
	By OutletType = By.name("outletType:input:dropdowncomponent");
	By OutletBusinessStatus = By.name("outletBusinessStatus:input:dropdowncomponent");
	By OutletContactName = By.xpath("//input[@fld_fqn='outletContactName']");
	By OutletMailAddr1 = By.xpath("//input[@fld_fqn='outletMailAddr1']");
	By OutletMailAddr2 = By.xpath("//input[@fld_fqn='outletMailAddr2']");
	By OutletMailAddr3 = By.xpath("//input[@fld_fqn='outletMailAddr3']");
	By OutletMailAddr4 = By.xpath("//input[@fld_fqn='outletMailAddr4']");
	By OutletCountry = By.name("outletMailCountryCode:input:dropdowncomponent");
	By OutletState = By.name("outletMailStateCode:input:dropdowncomponent");
	By OutletCity = By.name("outletMailCityCode:input:dropdowncomponent");
	By OutletEmail = By.xpath("//input[@fld_fqn='outletEmailId']");
	By OutletAlternateEmail = By.xpath("//input[@fld_fqn='outletAlternateEmail']");
	By OutletMobileNo = By.xpath("//input[@fld_fqn='outletMobileNo']");
	By OutletPhoneNo = By.xpath("//input[@fld_fqn='outletPhoneNo']");
	By OutletAlternatePhone = By.xpath("//input[@fld_fqn='outletAlternatePhone']");
	By OutletFaxNo = By.xpath("//input[@fld_fqn='outletFaxNo']");
	By OutletAlternateFax = By.xpath("//input[@fld_fqn='outletAlternateFax']");
	By OutletTypeCentralized = By.cssSelector("input[value='C']");
	By CreateAppSubmit = By.xpath("//*[@value='Submit']");
	By OutletTypeDeCentralized = By.cssSelector("input[value='D']");
	By OutletSettPeriod = By.xpath("//span[@id='outletSettlementPeriod']/..//*[@name = 'outletSettlementPeriod:input:dropdowncomponent']");
	By OutletSettValDays = By.xpath("//span[@id='outletSettlValDays']/..//*[@name = 'outletSettlValDays:input:inputTextField']");
	By OutletStateLayout = By.name("outletStatLayoutID:input:dropdowncomponent");
	By OutletSettState = By.name("outletStatMode:input:dropdowncomponent");
	By OutletSettDetailAdd = By.xpath("//span[@id = 'appOutletSetlAccount']/..//*[@class='addR']");
	By OutletSettDetCurrency = By.name("tables:1:rows:1:cols:colspanMarkup:inputField:input:dropdowncomponent");
	By OutletSettDetPaymentMode = By.name("tables:1:rows:1:cols:nextCol:colspanMarkup:inputField:input:dropdowncomponent");
	By OutletSettDetPayableTo = By.name("tables:1:rows:5:cols:nextCol:colspanMarkup:inputField:input:inputTextField");
	By OutletSettDet = By.xpath("//input[@value='Save']");
	By FeedbackError = By.className("feedbackPanelERROR");
	By Cancel = By.xpath("//*[@name='cancel']");
	By FeebackInfo = By.className("feedbackPanelINFO");
	
public CreateApp(WebDriver driver) {
	this.driver = driver;
}

public void ClickPlusButton () {
    //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='userid'][@name='username']")));
	WebDriverWait wait = new WebDriverWait(driver, 180);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='id30']")));
	driver.findElement(PlusButton).click();
}

public void ContactPerson() {
	driver.findElement(ContactPerson).sendKeys(new CharSequence[] { splitval[0]});
}

public void ApplicantName() {
	driver.findElement(ApplicantName).sendKeys(new CharSequence[] { splitval[1]});
	}

public void MerchantType() {
	Select elem1 = new Select(driver.findElement(MerchantType));  
    elem1.selectByVisibleText(splitval[2]);
	}

public void CreateOutletYes() {
	driver.findElement(CreateOutletYes).sendKeys(new CharSequence[] { splitval[3]});
}

public void CreateOutletNo() {
	driver.findElement(CreateOutletNo).sendKeys(new CharSequence[] { splitval[3]});
}

public void BusinessName() {
	driver.findElement(BusinessName).sendKeys(new CharSequence[] { splitval[4]});
}

public void RegdCompanyName() {
	driver.findElement(RegdCompanyName).sendKeys(new CharSequence[] { splitval[5]});
}

public void HubName() {
	 Select elem1 = new Select(driver.findElement(HubName));  
     elem1.selectByVisibleText(splitval[6]);
	}

public void ChainName() {
	new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElementLocated(ChainName)), splitval[7]);		          
    Select elem1 = new Select(driver.findElement(ChainName));  
    elem1.selectByVisibleText(splitval[7]);
	
}

public void MCC() {
	new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElementLocated(MCC)), splitval[8]));
    Select elem1 = new Select(driver.findElement(By.name("merMcc:input:dropdowncomponent")));
    elem1.selectByVisibleText(splitval[8]);
	driver.findElement(MCC).sendKeys(new CharSequence[] { splitval[8]});
}

public void OpeningDate() {
	driver.findElement(OpeningDate).sendKeys(new CharSequence[] { splitval[9]});
}

public void BusinessStatus() {
	driver.findElement(BusinessStatus).sendKeys(new CharSequence[] { splitval[10]});
}

public void PreferredLang() {
	driver.findElement(PreferredLang).sendKeys(new CharSequence[] { splitval[11]});
}

public void YearOfEstablishment() {
	driver.findElement(PreferredLang).sendKeys(new CharSequence[] { splitval[12]});
}

public void EstablishmentNumber() {
	driver.findElement(EstablishmentNumber).sendKeys(new CharSequence[] { splitval[13]});
}

public void EstablishmentType() {
	driver.findElement(EstablishmentType).sendKeys(new CharSequence[] { splitval[14]});
}

public void OfficeOwnership() {
	driver.findElement(OfficeOwnership).sendKeys(new CharSequence[] { splitval[15]});
}

public void OfficeTenure() {
	driver.findElement(OfficeTenure).sendKeys(new CharSequence[] { splitval[16]});
}

public void ResTenure() {
	driver.findElement(ResTenure).sendKeys(new CharSequence[] { splitval[17]});
}

public void MercResidenceType() {
	Select elem1 = new Select(driver.findElement(MercResidenceType)); 
    elem1.selectByVisibleText(splitval[18]);
	
}

public void BankName() {
	driver.findElement(BankName).sendKeys(new CharSequence[] { splitval[19]});
}

public void BranchName() {
	driver.findElement(BranchName).sendKeys(new CharSequence[] { splitval[20]});
}

public void BranchCode() {
	driver.findElement(BranchCode).sendKeys(new CharSequence[] { splitval[21]});
}

public void AccountTypeChecking() {
	driver.findElement(AccountTypeChecking).click();
}

public void MerAcctNumber() {
	driver.findElement(MerAcctNumber).sendKeys(new CharSequence[] { splitval[23]});
}

public void IbanNumber() {
	driver.findElement(IbanNumber).sendKeys(new CharSequence[] { splitval[24]});
}

public void remark() {
	driver.findElement(remark).sendKeys(new CharSequence[] { splitval[25]});
}


}

