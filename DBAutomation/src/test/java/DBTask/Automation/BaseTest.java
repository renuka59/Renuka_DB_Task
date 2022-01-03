package DBTask.Automation;




import org.testng.annotations.BeforeTest;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {
	
	   WebDriver driver;
	    SearchPage searchPage;
	    RegisterUser registerUser;
	   
	    LoginPage loginPage;
	    ApplyJobs applyJob;
	    UserProfilePage profilePage;
	   private final String URL = "https://jobs.deutschebahngroup.careers/";
	   public final String filePath = "/Users/madhubabusunkara/Desktop/Resume/new_cv/Renuka_Resume_Python.pdf";
	   //private final String ChromeDriverPath = "/Users/madhubabusunkara/Downloads/chromedriver2";

	    
	    
		@BeforeTest
		public void setUp() {
	    	System.setProperty("webdriver.chrome.driver", "/Users/madhubabusunkara/Downloads/chromedriver2");
	        driver = new ChromeDriver();
	        driver.get(URL);
	        driver.manage().deleteAllCookies();
	        driver.manage().window().maximize();
	        searchPage = new SearchPage(driver);
	        registerUser = new RegisterUser(driver);
	       // manualPage = new ManualInputPage(driver);
	        loginPage = new LoginPage(driver);
	        applyJob = new ApplyJobs(driver);
	        profilePage = new UserProfilePage(driver);
	        
	    }


	    


}
