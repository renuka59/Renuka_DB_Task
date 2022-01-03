package DBTask.Automation;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class UserProfilePage extends LoginPage {

	public UserProfilePage(WebDriver driver) {
		super(driver);
	}

	private final String rowPrefix = "//*[@class='mainContent']/div[2]/table/tbody/tr[";
	private final By AppliedJobslist = By.xpath("//*[@class='mainContent']/div[2]/table/tbody/tr");

	
	
	/************************************************************
	 * Objective: Verify that the application displays all the jobs/positions the user had applied for.
	 * Precondition: User already applied for some Jobs.
	 * Test Description: Login to the Application and check that all the list of jobs that the user had already applied
	 * are displayed along with the applied date and status of the application.
	 *
	 */
	// Perform the login and checks for the given job position and application status
	
	public void LoginAndCheckApplicationStatus(String eMailStr, String passWordStr, String jobNameStr, String ApplicationStatusStr)
	{	
		//Logs in with the given parameters
		LoginwithDetails(eMailStr,passWordStr);
		
		//Checks for the position name and application status in the Applied job list.
		CheckApplications(jobNameStr, ApplicationStatusStr);
	}
	
	
	// method for concatenating the row locator
	private String ApplicationsByIndex(int position) {
		return driver.findElement(By.xpath(rowPrefix + position + "]")).getText();
	}
	

	
	
	// Checks for the position name and application status in the Applied job list.
	public void CheckApplications(String PositionName, String ApplicationStatus) {
		
		boolean found = false;
		int Displayedlist = findElements(AppliedJobslist).size();
		System.out.println(Displayedlist);
		int counter = 1;
		// loops through the entire list of displayed Applications
		while(counter <= Displayedlist) {
			// If the displayed row has both the expected job position name 
			//and the expected application status
			if(ApplicationsByIndex(counter).contains(PositionName) && 
					ApplicationsByIndex(counter).contains(ApplicationStatus)) 
			{
				// set to true only if the row contains the expected job details.
				found = true;
			}
			counter++;	
		}
		// Assert returns true when the expected job details are found.
		Assert.assertTrue(found);
		
	}
	
	


}
