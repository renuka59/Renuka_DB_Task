package DBTask.Automation;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{
	private final By loginClick = By.xpath("//*[@class='navigationList']/li[3]/a[1]");
	private final By userButton = By.id("tpt_loginUsername");
	private final By pwdButton = By.id("tpt_loginPassword");
	private final By loginButton =By.xpath("//*[@name='login']");
	//private final By loginBySearch = By.xpath("//*[@class='mainContent']/div[2]/div/a");

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	//Authenticate with the given details
	public void LoginwithDetails(String usernameStr, String PasswordStr)
	{
		click(loginClick);
		//Fill the username
		find(userButton).sendKeys(usernameStr);
		//Fill the password
		find(pwdButton).sendKeys(PasswordStr);
	
		click(loginButton);
	}

}
