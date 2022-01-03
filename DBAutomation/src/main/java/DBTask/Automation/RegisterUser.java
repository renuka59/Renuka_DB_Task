package DBTask.Automation;

import java.awt.AWTException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class RegisterUser extends SearchPage {

	private final By passWordFiledLocator = By.id("1176");
	private final By confirmPassWordLocator = By.id("1177");
	private final By selectCheckBox = By.id("751");
	private final By selectMandatoryOption = By.id("752");
	private final By matchPositionlocator = By.id("120930");
	private final By saveProfileButton = By.id("124-save");

	private final By resumeUploadLocator = By.xpath("//*[text()='Upload CV/resume to create a profile']");
	private final By uploadCVByComputerButton = By.id("methodButton--file");
	private final By CVContinueButton = By.id("uploadFileResume");
	private final By displayedResumeNameLocator = By.id("fileNameContainer");
	private final By workPermitGermanyLocator = By.id("162002");
	private final By nationalityField = By.xpath("//*[@class='select2-selection__rendered']");
	private final By indianNationalityFieldLocator = By.id("li55814");
	private final By saveAndContinueButton1 = By.id("162008-save");
	private final By salaryExpectationLocator = By.id("90-1");
	private final By universityLocator = By.id("select2-90-2-container");
	private final By andereUniversityLocator = By.id("li11776243");
	private final By saveAndContinueButton2 = By.id("91-save");
	private final By finalApplyButton = By.id("1410-save");
	private final By confirmationText1 = By.xpath("//*[@class='content-thank-title']/h2");
	private final By confirmationText2 = By.xpath("//*[@class='content-thank-title']/h3");
	private final By applyJob = By.xpath("//*[@class='jobDetailDetails']/div[2]/a");

	public String filePath;
	// resources
	private String passWordText;
	private String dataShareOptions;
	private String willingnessForOtherPositions;
	private String hasWorkPermit;
	private String expectedSalary;

	public RegisterUser(WebDriver driver) {
		super(driver);
	}

	/**************************************************************************
	 * Test Objective: Apply for jobs by registering an user and uploading CV
	 * Precondition : Jobs page is opened and User is not registered 
	 * Step 1 : select any displayed Job and apply 
	 * step 2 : upload CV from computer and ensure that uploaded file is correctly displayed 
	 * step 3 : fill the user mandatory details and check that the job is applied successfully.
	 * 
	 */

	/**************************************************************************
	 * Creates a new profile by uploading the CV and applies for a job via job
	 * application.
	 * 
	 * @param filePathStr
	 * @param passWordStr
	 * @param countryNameStr
	 * @param shareOptionsStr
	 * @param willingnessStr
	 * @throws InterruptedException
	 * @throws AWTException
	 */

	public void RegisterProfileAndApllyJobViaJobApplication(String filePathStr, String passWordStr,
			 String shareOptionsStr, String willingnessStr, String workPermitStr, String SalaryStr)
			throws InterruptedException, AWTException {

		// copy inputs
		this.passWordText = passWordStr;
		this.dataShareOptions = shareOptionsStr;
		this.willingnessForOtherPositions = willingnessStr;
		this.filePath = filePathStr;
		this.hasWorkPermit = workPermitStr;
		this.expectedSalary = SalaryStr;

		// Click on the job that is displayed on the first row.
		clickDisplayedJobsInFirstRow();
		
		click(applyJob);
		
		// Uploads the CV file from computer and verifies the name of the uploaded file
		UploadResumefromComputer();
		setWait(10);

		// Fills the mandatory input details for creating a user profile.
		EnterUserProfileDetails();
	}

	/* Create a new profile by uploading the CV and applies for a job via Search Page */
	public void RegisterProfileAndApllyJobViaSearchPage(String filePathStr, String passWordStr,
			String shareOptionsStr, String willingnessStr) throws InterruptedException, AWTException {

		// copy inputs
		this.passWordText = passWordStr;
		this.dataShareOptions = shareOptionsStr;
		this.willingnessForOtherPositions = willingnessStr;
		this.filePath = filePathStr;
		
		// Click Upload CV/resume to create a profile
		click(resumeUploadLocator);

		setWait(10);
		
		// Uploads the CV file from computer and verifies the name of the uploaded file
		UploadResumefromComputer();

		// Fills the mandatory input details for creating a user profile.
		EnterUserProfileDetails();
		
		// verifies whether user profile created
		CheckIsUserProfileCreated();
	}

	// Uploads resume from computer
	// verifies the name of the uploaded file
	private void UploadResumefromComputer() throws InterruptedException, AWTException {

		// Click Upload from computer button
		click(uploadCVByComputerButton);
		setWait(10);

		// Uploads file from the given file path
		fileUploadFromComputer(filePath);
		setWait(10);
		// Verifies the name of the uploaded file.
		CheckUploadedFilename();

		// Click Continue button
		click(CVContinueButton);
		setWait(10);

	}

	// Verifies the name of the uploaded the file
	private void CheckUploadedFilename() {

		String actualFileName = find(displayedResumeNameLocator).getText();
		// Asserts the actual file name versus displayed file name
		Assert.assertEquals(actualFileName, extractFileNameFromFilePath());
	}

	// Method for converting Input DataSharedataShare text to dropdown index
	private int convertDataShareTextToIndex(String inputStr) {
		int ret = 0;
		if (inputStr.equals("Internationally across the DB Group")) {
			ret = 1;
		} else if (inputStr.equals("Nationally within the advertising Business")) {
			ret = 2;
		}
		return ret;
	}

	// Method for converting Input SuitablePosition text to drop down index
	private int convertYesOrNoTextToIndex(String inputStr) {
		int ret = 0;
		if (inputStr.equals("No")) {
			ret = 1;
		} else if (inputStr.equals("Yes")) {
			ret = 2;
		}
		return ret;
	}
	
	// method for extracting filename from the path
	private String extractFileNameFromFilePath() {
		String Text[] = filePath.split("/");
		String fileName = Text[Text.length - 1];
		return fileName;
	}

	// Method to fill the mandatory input details for creating a user profile.
	private void EnterUserProfileDetails() throws InterruptedException {

		// fill the password text field
		find(passWordFiledLocator).sendKeys(passWordText);

		// Confirm Password Text Field
		find(confirmPassWordLocator).sendKeys(passWordText);
		setWait(10);

		click(selectCheckBox);

		// converts input text to index and selects the Datashare
		SelectFilterInputFromDropdown(convertDataShareTextToIndex(dataShareOptions), selectMandatoryOption);
		setWait(5);
		// converts input text to index and selects the suitablePosition
		SelectFilterInputFromDropdown(convertYesOrNoTextToIndex(willingnessForOtherPositions), matchPositionlocator);
		setWait(5);

		// Click save profile button.
		click(saveProfileButton);

		// Nationality
		click(nationalityField);
		// nationalityField).sendKeys("Indian");
		click(indianNationalityFieldLocator);

		// WorkPermit
		SelectFilterInputFromDropdown(convertYesOrNoTextToIndex(hasWorkPermit), workPermitGermanyLocator);

		setWait(5);

		click(saveAndContinueButton1);
		
		// SalaryExpectation
		sendKeys(expectedSalary, salaryExpectationLocator);

		// University
		click(universityLocator);
		sendKeys("andere", universityLocator);
		// SRH Berlin University of Applied Sciences
		click(andereUniversityLocator);

		click(saveAndContinueButton2);

		// apply
		click(finalApplyButton);
	}
	
	// Method for verifying the job application
	private void CheckIsUserProfileCreated() throws InterruptedException {
		
		String finalConfirmationText1 = find(confirmationText1).getText();
		String finalConfirmationText2 = find(confirmationText2).getText();

		Assert.assertEquals("Thank you", finalConfirmationText1);
		Assert.assertEquals("for your application!", finalConfirmationText2);
		
	}

}