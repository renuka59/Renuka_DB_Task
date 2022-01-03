package DBTask.Automation;




import org.testng.annotations.BeforeTest;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {
	
	   WebDriver driver;
	    SearchPage searchPage;
	    RegisterUser registerUser;
	    LoginPage loginPage;
	    UserProfilePage profilePage;
	   private String driverPath ="/Users/madhubabusunkara/Downloads/chromedriver2";
	   private final String URL = "https://jobs.deutschebahngroup.careers/";
	   public final String filePath = "/Users/madhubabusunkara/Desktop/Resume/new_cv/Renuka_Resume_Python.pdf";
	    
	    
		@BeforeTest
		public void setUp() {
	    	System.setProperty("webdriver.chrome.driver", driverPath);
	        driver = new ChromeDriver();
	        driver.get(URL);
	        driver.manage().deleteAllCookies();
	        driver.manage().window().maximize();
	        searchPage = new SearchPage(driver);
	        registerUser = new RegisterUser(driver);
	        loginPage = new LoginPage(driver);
	        profilePage = new UserProfilePage(driver);
	        
	    }


	    


}
