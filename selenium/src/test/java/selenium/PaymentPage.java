package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PaymentPage {
	WebDriver driver;

//
	@BeforeClass(groups="login")
	public  void login () 
	{
		System.setProperty("webdriver.chrome.driver","D:\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.get("http://10.0.10.172:8037/home.aspx");
		String i = driver.getCurrentUrl();
		driver.manage().window().maximize();
     	driver.findElement(By.id("ctl00_ContentPlaceHolder1_txtusername")).sendKeys("VADMIN"); 
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_txtpassword")).sendKeys("VENMTS");
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_cmdLogin")).click();
		System.out.println(i); 
		System.out.println("Login sucessfull");

		//Click on dashboard page
		driver.findElement(By.xpath(".//*[@id='nav']/li[1]/a")).click();

		//  multiservice() 
		driver.findElement(By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_asingle']/img")).click();

		System.out.println("multiservices opened");
	}

	@BeforeMethod(groups="multiservice")
	public void multiservice() throws Exception{					   
		Thread.sleep(3000);

		driver.findElement(By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_lsvService_ctrl6_imgCart']")).click();
		driver.findElement(By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_ibtCheckout']")).click();
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_txtsearchFnamePayment")).sendKeys("Alekhya");
		driver.findElement(By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_txtsearchLnamePayment']")).sendKeys("S");
		driver.findElement(By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_cmdStatus']")).click();
	}
	// In kind
	@Test(groups="Inkind")
	public void paymentInKind() throws InterruptedException{			   
		Thread.sleep(3000);
		driver.findElement(By.xpath(".//*[@id='radlPaymentTypeAdmin_6']")).click();
		Thread.sleep(3000);
		//negative validation
		driver.findElement(By.xpath(".//*[@id='txtNotes']")).sendKeys("!@#$%^&*()_+?><");
		driver.findElement(By.xpath("//td[@id='holder1']/img")).click();
		Thread.sleep(3000);
		driver.findElement(By.linkText("28")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(".//*[@id='lnkPayNow']/img")).click();
		String inkind = driver.findElement(By.xpath(".//*[@id='lblpaywar']/span[1]")).getText();
		System.out.println("Expected result is 1. Please enter valid Notes, Should not contain the characters ('@~%^and double quotes) Actual result is ;" +inkind);
		driver.findElement(By.xpath(".//*[@id='lnkPayNow']/img")).click();
		String inkind1 = driver.findElement(By.xpath(".//*[@id='lblpaywar']/span[2]")).getText();
		System.out.println("Expected result is 2. Please enter a Current/Past date, in the format (MM/DD/YYYY) Actual result is ;" +inkind1);
		Thread.sleep(3000);
		//postive
		driver.findElement(By.xpath("//td[@id='holder1']/img")).click();
		Thread.sleep(3000);		
		driver.findElement(By.linkText("6")).click();
		driver.findElement(By.xpath(".//*[@id='txtNotes']")).clear();
		driver.findElement(By.xpath(".//*[@id='txtNotes']")).sendKeys("Paid by inkind");
		driver.findElement(By.xpath(".//*[@id='lnkPayNow']/img")).click();
		//  cancel  payment -  driver.findElement(By.xpath(".//*[@id='ibtcancel1']/img")).click();

		driver.findElement(By.id("ctl00_ContentPlaceHolder1_cmdDonationOK")).click();

	}

	// Credit Card / Debit Card Manual


	@Test(groups="CCDC", dependsOnGroups="Inkind")
	public void PaymentCCDCManual() throws Exception{

		Thread.sleep(3000);
		driver.findElement(By.xpath(".//*[@id='radlPaymentTypeAdmin_5']")).click();
		//negative validation 

		driver.findElement(By.xpath(".//*[@id='txtNotes']")).sendKeys("!@#$%^&*()_+?><");
		driver.findElement(By.xpath(".//*[@id='holder1']/img")).click();
		Thread.sleep(3000);
		driver.findElement(By.linkText("28")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(".//*[@id='lnkPayNow']/img")).click();

		String inkind = driver.findElement(By.xpath(".//*[@id='lblpaywar']/span[1]")).getText();
		System.out.println("Expected result is 1. Please enter valid Notes, Should not contain the characters ('@~%^and double quotes) Actual result is ;" +inkind);
		driver.findElement(By.xpath(".//*[@id='lnkPayNow']/img")).click();
		String inkind1 = driver.findElement(By.xpath(".//*[@id='lblpaywar']/span[2]")).getText();
		System.out.println("Expected result is 2. Please enter a Current/Past date, in the format (MM/DD/YYYY) Actual result is ;" +inkind1);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='holder1']/img")).click();
		Thread.sleep(3000);		
		driver.findElement(By.linkText("6")).click();

		driver.findElement(By.xpath(".//*[@id='txtcheckNumber']")).sendKeys("AHb6783j7");	
		driver.findElement(By.xpath(".//*[@id='txtNotes']")).clear();
		driver.findElement(By.xpath(".//*[@id='txtNotes']")).sendKeys("Paid by CC/DC Manual");
		driver.findElement(By.xpath(".//*[@id='lnkPayNow']/img")).click();

		//cancel  payment -  driver.findElement(By.xpath(".//*[@id='ibtcancel1']/img")).click();

		driver.findElement(By.id("ctl00_ContentPlaceHolder1_cmdDonationOK")).click();
	} 


//Debit card

	@Test(groups="DebitCard", dependsOnGroups="CCDC")
	public void paymentdebitcard() throws Exception{
		Thread.sleep(3000);
		driver.findElement(By.xpath(".//*[@id='radlPaymentTypeAdmin_1']")).click();
		Thread.sleep(3000);
		//Negative 
		driver.findElement(By.xpath(".//*[@id='lnkPayNow']/img")).click																																																				();
		Thread.sleep(3000);
		String Debit = driver.findElement(By.xpath(".//*[@id='lblpaywar']")).getText();
		System.out.println("Expected result is Please Enter DebitCard Number 2.Please Enter CCV Number 3.Please Enter Month 4.Please Enter Year Actual result is ;" +Debit);
		driver.findElement(By.xpath(".//*[@id='txtCard']")).clear();
		
		driver.findElement(By.xpath(".//*[@id='txtCard']")).sendKeys("123333");
		Select dropdown4 = new Select(driver.findElement(By.xpath(".//*[@id='ddExpMonth']")));
		// dropdown.selectByVisibleText("All");
		dropdown4.selectByIndex(12);
		//  dropdown.selectByValue("10393");
		Thread.sleep(3000);
		Select dropdown5 = new Select(driver.findElement(By.xpath(".//*[@id='ddExpYear']")));
		dropdown5.selectByVisibleText("2028");
		Thread.sleep(3000);
		driver.findElement(By.xpath(".//*[@id='txtCVV']")).sendKeys("234");
		Thread.sleep(3000);
		driver.findElement(By.xpath(".//*[@id='lnkPayNow']/img")).click();
		driver.findElement(By.xpath(".//*[@id='txtcardnotes']")).sendKeys("!@#$%^&*()_+?><");
		driver.findElement(By.xpath(".//*[@id='txtcardnotes']")).clear();
		driver.findElement(By.xpath(".//*[@id='txtcardnotes']")).sendKeys("Paid by debit card");
		driver.findElement(By.xpath(".//*[@id='lnkPayNow']/img")).click();
		Thread.sleep(3000);
		String DebitCard = driver.findElement(By.xpath(".//*[@id='errormessage']")).getText();
		System.out.println("Expected result is Sorry! Your transaction was declined due to The credit card number is invalid. Actual result is ;" +DebitCard);
		driver.findElement(By.xpath(".//*[@id='txtCard']")).clear();
		
		driver.findElement(By.xpath(".//*[@id='txtCard']")).sendKeys("370000000000002");
		Select dropdown6 = new Select(driver.findElement(By.xpath(".//*[@id='ddExpMonth']")));
		// dropdown.selectByVisibleText("All");
		dropdown6.selectByIndex(12);
		//  dropdown.selectByValue("10393");
		Thread.sleep(3000);
		Select dropdown7 = new Select(driver.findElement(By.xpath(".//*[@id='ddExpYear']")));
		dropdown7.selectByVisibleText("2028");
		Thread.sleep(3000);
		driver.findElement(By.xpath(".//*[@id='txtCVV']")).clear();
		driver.findElement(By.xpath(".//*[@id='txtCVV']")).sendKeys("AS456");
		Thread.sleep(3000);
		driver.findElement(By.xpath(".//*[@id='lnkPayNow']/img")).click();
		
		String CVV = driver.findElement(By.xpath(".//*[@id='errormessage']")).getText();
		System.out.println("Expected result is Sorry! Your transaction was declined due to The card code is invalid. Actual result is ;" +CVV);
		
		driver.findElement(By.xpath(".//*[@id='txtCVV']")).clear();
		driver.findElement(By.xpath(".//*[@id='txtCVV']")).sendKeys("234");
		Thread.sleep(3000);
		
		driver.findElement(By.xpath(".//*[@id='txtcardnotes']")).sendKeys("!@#$%^&*()_+?><");
		driver.findElement(By.xpath(".//*[@id='txtcardnotes']")).clear();
		driver.findElement(By.xpath(".//*[@id='txtcardnotes']")).sendKeys("Paid by debit card");
		driver.findElement(By.xpath(".//*[@id='lnkPayNow']/img")).click();
		//  cancel  payment -  driver.findElement(By.xpath(".//*[@id='ibtcancel1']/img")).click();
		System.out.println("Payment done");
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_cmdDonationOK")).click();
		System.out.println("Payment page redirected to check");
	}

	@Test(groups="Check", dependsOnGroups="DebitCard")
	public void paymentcheck() throws Exception{

		Thread.sleep(3000);
		driver.findElement(By.xpath(".//*[@id='radlPaymentTypeAdmin_2']")).click();
		

		//negative validation 

		driver.findElement(By.xpath(".//*[@id='txtNotes']")).sendKeys("!@#$%^&*()_+?><");
		driver.findElement(By.xpath(".//*[@id='holder1']/img")).click();
		Thread.sleep(3000);
		driver.findElement(By.linkText("28")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(".//*[@id='holder2']/img")).click();
		Thread.sleep(3000);
		driver.findElement(By.linkText("28")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(".//*[@id='lnkPayNow']/img")).click();

		String check = driver.findElement(By.xpath(".//*[@id='lblpaywar']")).getText();
		System.out.println("Expected result is 1. Please enter a Current/Past date, in the format (MM/DD/YYYY) 2. Please enter the Check Number 3. Please enter valid Notes, Should not contain the characters ('@~%^ and double quotes) Actual result is ;" +check);
		driver.findElement(By.xpath(".//*[@id='lnkPayNow']/img")).click();
		Thread.sleep(3000);	
		driver.findElement(By.xpath("//*[@id='holder1']/img")).click();
		Thread.sleep(3000);		
		driver.findElement(By.linkText("6")).click();
		Thread.sleep(3000);		
		driver.findElement(By.xpath(".//*[@id='holder2']/img")).click();	
		Thread.sleep(3000);		
		driver.findElement(By.linkText("6")).click();
		Thread.sleep(3000);		
		driver.findElement(By.xpath(".//*[@id='txtcheckNumber']")).sendKeys("1GH78");	
		Thread.sleep(3000);	
		String checkno = driver.findElement(By.xpath(".//*[@id='lblpaywar']")).getText();
		System.out.println("Expected result is 1. Please enter a valid Check Number Format (9999999999) Actual result is ;" +checkno);
		driver.findElement(By.xpath(".//*[@id='txtcheckNumber']")).clear();
		driver.findElement(By.xpath(".//*[@id='txtcheckNumber']")).sendKeys("34567");		
		driver.findElement(By.xpath(".//*[@id='txtBankName']")).sendKeys("HDFC");
		driver.findElement(By.xpath(".//*[@id='txtNotes']")).clear();
		driver.findElement(By.xpath(".//*[@id='txtNotes']")).sendKeys("Paid by check");
		driver.findElement(By.xpath(".//*[@id='lnkPayNow']/img")).click();
		//cancel  payment -  driver.findElement(By.xpath(".//*[@id='ibtcancel1']/img")).click();

		driver.findElement(By.id("ctl00_ContentPlaceHolder1_cmdDonationOK")).click();
	}
	

	@Test(groups="cash", dependsOnGroups="Check")
	public void paymentpagecash() throws Exception{

		Thread.sleep(3000);
		driver.findElement(By.xpath(".//*[@id='radlPaymentTypeAdmin_3']")).click();
		//negative validation
		driver.findElement(By.xpath(".//*[@id='txtNotes']")).sendKeys("!@#$%^&*()_+?><");
		driver.findElement(By.xpath("//td[@id='holder1']/img")).click();
		Thread.sleep(3000);
		driver.findElement(By.linkText("28")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(".//*[@id='lnkPayNow']/img")).click();
		String cash = driver.findElement(By.xpath(".//*[@id='lblpaywar']/span[1]")).getText();
		System.out.println("Expected result is 1. Please enter valid Notes, Should not contain the characters ('@~%^and double quotes) Actual result is ;" +cash);
		driver.findElement(By.xpath(".//*[@id='lnkPayNow']/img")).click();
		String cashdate = driver.findElement(By.xpath(".//*[@id='lblpaywar']/span[2]")).getText();
		System.out.println("Expected result is 2. Please enter a Current/Past date, in the format (MM/DD/YYYY) Actual result is ;" +cashdate);
		Thread.sleep(3000);
		//postive
		driver.findElement(By.xpath("//td[@id='holder1']/img")).click();
		Thread.sleep(3000);		
		driver.findElement(By.linkText("6")).click();
		driver.findElement(By.xpath(".//*[@id='txtNotes']")).clear();
		driver.findElement(By.xpath(".//*[@id='txtNotes']")).sendKeys("Paid by Cash");
		driver.findElement(By.xpath(".//*[@id='lnkPayNow']/img")).click();
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_cmdDonationOK")).click();

	}

	@Test(groups="creditcard", dependsOnGroups="cash")
	public void paymentCreditcard() throws Exception{

			Thread.sleep(3000);
			driver.findElement(By.xpath(".//*[@id='radlPaymentTypeAdmin_1']")).click();
			Thread.sleep(3000);
			//Negative 
			driver.findElement(By.xpath(".//*[@id='lnkPayNow']/img")).click																																																				();
			Thread.sleep(3000);
			String Debit = driver.findElement(By.xpath(".//*[@id='lblpaywar']")).getText();
			System.out.println("Expected result is Please Enter DebitCard Number 2.Please Enter CCV Number 3.Please Enter Month 4.Please Enter Year Actual result is ;" +Debit);
			driver.findElement(By.xpath(".//*[@id='txtCard']")).clear();
			
			driver.findElement(By.xpath(".//*[@id='txtCard']")).sendKeys("123333");
			Select dropdown8 = new Select(driver.findElement(By.xpath(".//*[@id='ddExpMonth']")));
			// dropdown.selectByVisibleText("All");
			dropdown8.selectByIndex(12);
			//  dropdown.selectByValue("10393");
			Thread.sleep(3000);
			Select dropdown9 = new Select(driver.findElement(By.xpath(".//*[@id='ddExpYear']")));
			dropdown9.selectByVisibleText("2028");
			Thread.sleep(3000);
			driver.findElement(By.xpath(".//*[@id='txtCVV']")).sendKeys("234");
			Thread.sleep(3000);
			driver.findElement(By.xpath(".//*[@id='lnkPayNow']/img")).click();
			driver.findElement(By.xpath(".//*[@id='txtcardnotes']")).sendKeys("!@#$%^&*()_+?><");
			driver.findElement(By.xpath(".//*[@id='txtcardnotes']")).clear();
			driver.findElement(By.xpath(".//*[@id='txtcardnotes']")).sendKeys("Paid by credit card");
			driver.findElement(By.xpath(".//*[@id='lnkPayNow']/img")).click();
			Thread.sleep(3000);
			String CreditCard = driver.findElement(By.xpath(".//*[@id='errormessage']")).getText();
			System.out.println("Expected result is Sorry! Your transaction was declined due to The credit card number is invalid. Actual result is ;" +CreditCard);
			driver.findElement(By.xpath(".//*[@id='txtCard']")).clear();
			
			driver.findElement(By.xpath(".//*[@id='txtCard']")).sendKeys("370000000000002");
			Select dropdown10 = new Select(driver.findElement(By.xpath(".//*[@id='ddExpMonth']")));
			// dropdown.selectByVisibleText("All");
			dropdown10.selectByIndex(12);
			//  dropdown.selectByValue("10393");
			Thread.sleep(3000);
			Select dropdown11 = new Select(driver.findElement(By.xpath(".//*[@id='ddExpYear']")));
			dropdown11.selectByVisibleText("2028");
			Thread.sleep(3000);
			driver.findElement(By.xpath(".//*[@id='txtCVV']")).clear();
			driver.findElement(By.xpath(".//*[@id='txtCVV']")).sendKeys("AS456");
			Thread.sleep(3000);
			driver.findElement(By.xpath(".//*[@id='lnkPayNow']/img")).click();
			
			String CVV = driver.findElement(By.xpath(".//*[@id='errormessage']")).getText();
			System.out.println("Expected result is Sorry! Your transaction was declined due to The card code is invalid. Actual result is ;" +CVV);
			
			driver.findElement(By.xpath(".//*[@id='txtCVV']")).clear();
			driver.findElement(By.xpath(".//*[@id='txtCVV']")).sendKeys("234");
			Thread.sleep(3000);
			
			driver.findElement(By.xpath(".//*[@id='txtcardnotes']")).sendKeys("!@#$%^&*()_+?><");
			driver.findElement(By.xpath(".//*[@id='txtcardnotes']")).clear();
			driver.findElement(By.xpath(".//*[@id='txtcardnotes']")).sendKeys("Paid by credit card");
			driver.findElement(By.xpath(".//*[@id='lnkPayNow']/img")).click();
			//  cancel  payment -  driver.findElement(By.xpath(".//*[@id='ibtcancel1']/img")).click();
			System.out.println("Payment done");
			driver.findElement(By.id("ctl00_ContentPlaceHolder1_cmdDonationOK")).click();
			System.out.println("Payment page redirected to VPOS");
	}

	@Test(groups="VPOS", dependsOnGroups="creditcard")
	public void paymentVPOS() throws Exception{

		Thread.sleep(3000);
		driver.findElement(By.xpath(".//*[@id='radlPaymentTypeAdmin_4']")).click();
		Thread.sleep(6000);
		driver.findElement(By.xpath(".//*[@id='lnkPayNow']/img")).click();
		//driver.findElement(By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_cmddonation']")).click();
		//String VPOS = driver.findElement(By.xpath(".//*[@id='lblpaywar']")).getText();
		//System.out.println("Expected result is  Cardnumber cannot be empty. Actual result is ;" +VPOS);
		//Thread.sleep(3000);
		//driver.findElement(By.xpath(".//*[@id='lnkPayNow']/img")).click();	
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_cmdDonationOK")).click();
		
		
	}

	@AfterTest
	public void afterTest() {
		driver.quit();

	}

}



