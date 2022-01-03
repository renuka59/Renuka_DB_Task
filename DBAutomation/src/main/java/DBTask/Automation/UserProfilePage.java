package DBTask.Automation;

import java.awt.AWTException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class UserProfilePage extends LoginPage {

	public UserProfilePage(WebDriver driver) {
		super(driver);
	}

	private final String rowPrefix = "//*[@class='mainContent']/div[2]/table/tbody/tr[";
	private final By AppliedJobslist = By.xpath("//*[@class='mainContent']/div[2]/table/tbody/tr");
	private final By applyJobButton = By.xpath("//*[@class='jobDetailDetails']/div[2]/a");
	private final By searchForJobsButton = By.xpath("//*[@class='mainContent']/div[2]/div/a");
	private final By applyJobWithExistingProfile = By.xpath("//*[@class='mainContent']/div/div[2]/a[2]");
	private final By selectCheckBox = By.id("1133");
	private final By saveProfile = By.id("1137-save");
	private final By appliedJobPositionLocator = By.xpath("//*[@class='col-right-detail']/div/div[1]/div[1]/div");

	private SearchPage searchPage;

	/************************************************************
	 * Objective: Verify that the application displays all the jobs/positions the
	 * user had applied for. Precondition: User already applied for some Jobs. Test
	 * Description: Login to the Application and check that all the list of jobs
	 * that the user had already applied are displayed along with the applied date
	 * and status of the application.
	 *
	 */
	// Perform the login and checks for the given job position and application
	// status

	public void LoginAndCheckApplicationStatus(String eMailStr, String passWordStr, String jobNameStr,
			String ApplicationStatusStr) {
		// Logs in with the given parameters
		LoginwithDetails(eMailStr, passWordStr);

		// Checks for the position name and application status in the Applied job list.
		CheckApplicationsStatus(jobNameStr, ApplicationStatusStr);
	}

	// method for concatenating the row locator
	private String ApplicationsByIndex(int position) {
		return driver.findElement(By.xpath(rowPrefix + position + "]")).getText();
	}

	// Checks for the position name and application status in the Applied job list.
	protected void CheckApplicationsStatus(String PositionName, String ApplicationStatus) {

		boolean found = false;
		int Displayedlist = findElements(AppliedJobslist).size();

		int counter = 1;
		// loops through the entire list of displayed Applications
		while (counter <= Displayedlist) {
			// If the displayed row has both the expected job position name
			// and the expected application status
			if (ApplicationsByIndex(counter).contains(PositionName)
					&& ApplicationsByIndex(counter).contains(ApplicationStatus)) {
				// set to true only if the row contains the expected job details.
				found = true;
			}
			counter++;
		}
		// Assert returns true when the expected job details are found.
		Assert.assertTrue(found);

	}

	/************************************************************
	 * Objective: To check weather the user can apply for a new job with the
	 * existing profile and CV. Precondition: Log in as existing user Step 1: Select
	 * any job using the search key word from the search page. Step 2: Apply the
	 * selected job Step 3: Click on Save profile button and check that the selected
	 * job is applied with the already existing user profile.
	 *
	 */

	// Method for applying the Job through existing profile
	public void applyJobThroughExistingProfile(String keyWord) throws InterruptedException, AWTException {
		searchPage = new SearchPage(driver);

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

		CheckApplicationsStatus(appliedJobName, "Retract");

	}

}
