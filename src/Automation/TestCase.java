	package Automation;

	import org.testng.Assert;
	import org.testng.annotations.AfterTest;
	import org.testng.annotations.BeforeTest;
	import org.testng.annotations.Test;
        import static org.testng.Assert.assertEquals;
    	import java.awt.AWTException;
    	import java.awt.Robot;
    	import java.awt.Toolkit;
    	import java.awt.datatransfer.StringSelection;
    	import java.awt.event.KeyEvent;
    	import java.io.File;
   	import java.io.IOException;
   	import java.time.Duration;
    	import java.util.concurrent.TimeUnit;
	import org.openqa.selenium.By;
	import org.openqa.selenium.ElementClickInterceptedException;
	import org.openqa.selenium.JavascriptExecutor;
        import org.openqa.selenium.Keys;
        import org.openqa.selenium.OutputType;
    	import org.openqa.selenium.TakesScreenshot;
    	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.io.FileHandler;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.Select;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.openqa.selenium.NoSuchElementException;
	
	public class TestCase extends Utility{


	WebDriver driver;	

	@BeforeTest
	public void initialize()
	{
	    System.setProperty("webdriver.chrome.driver", "C:\\Selenium Temp\\ChromeDriver\\chromedriver.exe");
	    driver = new ChromeDriver();
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    driver.manage().window().maximize();
	    driver.get("https://elearning.excelr.com/");
	}  
	//Enter credentials and click on login button
	@Test(priority=0)
	public void login() throws InterruptedException, AWTException, IOException
	{
	  
	  WebElement SignIn = driver.findElement(By.linkText("Sign In"));
	  Thread.sleep(1000);
	  SignIn.click();
	  
	  WebElement Email = driver.findElement(By.xpath("//input[@id='login-email']"));
	  Email.clear();
	  Email.sendKeys("abc@gmail.com");
	  
	  WebElement Password = driver.findElement(By.xpath("//input[@id='login-password']"));
          Password.clear();
	  Password.sendKeys("GauravExcelr");
	     
	  WebElement Button = driver.findElement(By.xpath("//button[text()='Sign in']"));
	  Thread.sleep(1000);
	  Button.click();
	  
	  String actualtitle = driver.getTitle();
	  String expectedtitle = "Sign in or Register | ExcelR Solutions";
	  Assert.assertEquals(actualtitle, expectedtitle);
	}
	//Click on update profile button, upload button, upload file using robot class, take screenshot, enter details like phone number, headline and validate 
	@Test(priority=1)
	public void functional() throws IOException, InterruptedException, AWTException
	{
          
          By button = By.xpath("//div[@class='setting_section']/ul/li/a/i[@class='fa fa-pencil-square-o fa-2x']");
          WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
          wait.until(ExpectedConditions.presenceOfElementLocated(button));
          driver.findElement(button).click();
          
          WebElement upload = driver.findElement(By.xpath("//form[@id='profile_form']/div/div/div/div/div/div/div/div/div[@class='file-default-preview clickable']"));
          Thread.sleep(1000);
          upload.click();
          
          try {
        	  Thread.sleep(1000);	  
        	  uploadfile();
          }
          catch(AWTException e)
          {
        	  throw e;
          }
          
          int flag=0;	
     	  if(flag==0)
     	 {
     	    WebElement Phone_Number = driver.findElement(By.xpath("//*[@id='phone_number']"));
     	    Phone_Number.clear();
            Phone_Number.sendKeys("8919876100");
            WebElement Headline = driver.findElement(By.xpath("//*[@id='headline']"));
            Headline.clear();
     	    Headline.sendKeys("Hi. My name is Gaurav!");
     	 }
     	  else
     	 {
     		System.out.println("The phone number you entered is not valid. Please enter the correct number along with your area code.");
     	 }
          
          WebElement UpdateProfile = driver.findElement(By.xpath("//div[@class='modal-footer']/button[@id='user_profile_save']"));
          Thread.sleep(1000);
          UpdateProfile.click();
        
          try {
        	  Thread.sleep(5000);	  
        	  TakesScreenshot srceenshot = ((TakesScreenshot)driver);
        	  File srcfile = srceenshot.getScreenshotAs(OutputType.FILE);
        	  File Destination = new File("C:\\Users\\Gaurav\\Documents\\Srceenshot\\srceenshot.png");
        	  FileHandler.copy(srcfile, Destination);	 
          }
          catch(InterruptedException e)
          {
        	  throw e;
          }
      }	 
        //Regression Testing
	@Test(priority=2)
	public void regression() throws InterruptedException, IOException
	{
	  WebElement logout = driver.findElement(By.xpath("//div[@id='contain-all']/header/div/div[3]/nav/a[3]"));
    	try {
	        logout.click();
	        }
    	catch(ElementClickInterceptedException e)
    		{
    		throw e;
    		}
	 
      	  driver.navigate().refresh();
	 
	  WebElement SignIn = driver.findElement(By.linkText("Sign In"));
	  Thread.sleep(1000);
	  SignIn.click();
	  
	  WebElement Email = driver.findElement(By.xpath("//input[@id='login-email']"));
	  Thread.sleep(1000);
	  Email.sendKeys("gaurav.pathangej@gmail.com");
	  
	  WebElement Password = driver.findElement(By.xpath("//input[@id='login-password']"));
	  Thread.sleep(1000);
	  Password.sendKeys("GauravExcelr");
	     
	  WebElement Button = driver.findElement(By.xpath("//button[text()='Sign in']"));
	  Thread.sleep(1000);
	  Button.click();

	  try {
	  Thread.sleep(5000);
	  TakesScreenshot srceenshot = ((TakesScreenshot)driver);
	  File srcfile = srceenshot.getScreenshotAs(OutputType.FILE);
      	  File Destination = new File("C:\\Users\\Gaurav\\Documents\\Srceenshot\\regression.png");
          FileHandler.copy(srcfile, Destination);	 
	  }
	  catch(InterruptedException e)
	  {
            throw e;
	  }
	  
	
    }
	
}

	

