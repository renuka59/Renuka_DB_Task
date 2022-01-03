package DBTask.Automation;

import java.awt.AWTException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ApplyJobs extends BasePage {

	private final By applyJobButton = By.xpath("//*[@class='jobDetailDetails']/div[2]/a");
	private final By searchForJobsButton = By.xpath("//*[@class='mainContent']/div[2]/div/a");
	private final By applyJobWithExistingProfile = By.xpath("//*[@class='mainContent']/div/div[2]/a[2]");
	private final By selectCheckBox = By.id("1133");
	private final By saveProfile = By.id("1137-save");
	private final By appliedJobPositionLocator = By.xpath("//*[@class='col-right-detail']/div/div[1]/div[1]/div");

	public ApplyJobs(WebDriver driver) {
		super(driver);
	}
	
	private SearchPage searchPage;
	
	private UserProfilePage userProfilePage;

	/************************************************************
	 * Objective: To check weather the user can apply for a new job with the existing profile and CV.
	 * Precondition: Log in as existing user 
	 * Step 1: Select any job using the search key word from the search page. 
	 * Step 2: Apply the selected job 
	 * Step 3: Click on Save profile button and check that the selected job is applied with
	 * the already existing user profile.
	 *
	 */
	
	// Method for applying the Job through existing profile
	public void applyJobThroughExistingProfile(String keyWord) throws InterruptedException, AWTException {
		searchPage = new SearchPage(driver);
		userProfilePage = new UserProfilePage(driver);
		
		
		// Clicks the search button
		click(searchForJobsButton);
		// Displays the jobs with given keyword
		searchPage.searchJobsWithJobKeyword(keyWord);
		// Select the job displayed in the first row
		searchPage.clickDisplayedJobsInFirstRow();
		String appliedJobName = find(appliedJobPositionLocator).getText();
		// Apply the selected job
		click(applyJobButton);

		// Apply for the selected job with existing profile.
		click(applyJobWithExistingProfile);

		click(selectCheckBox);

		// Commented for avoiding disturbance to the real time application
		click(saveProfile);
		
		userProfilePage.CheckApplications(appliedJobName, "Retract");
	
		
		
	}

}
