package DBTask.Automation;

import java.awt.AWTException;

import org.testng.annotations.Test;

public class profileTest extends BaseTest {
	
	@Test(priority=1)
	public void verifySearchResultsAccoundingToJobKeyword()throws InterruptedException
	{
		//searchPage.displaySearchResultsForTheJobKeyword("India");
	}
	@Test(priority=2)
	public void verifyApplyJobByRegisteringUserAndUploadingCv() throws InterruptedException, AWTException
	{
		//registerUser.RegisterProfileAndApllyJobViaJobApplication(filePath,"Decem1234567$","Germany","Internationally across the DB Group","No");
	}
	@Test(priority=3)
	public void verifyStatusOfJobApplication()
	{
		profilePage.LoginAndCheckApplicationStatus("renuka.sunkara73@gmail.com", "renukaDB123$", "Test Automation Engineer", "Retract");
	}
	@Test(priority=4)
	public void verifyApplyJobByExistingUser() throws InterruptedException, AWTException
	{
		applyJob.applyJobThroughExistingProfile("Africa");
	}

}
