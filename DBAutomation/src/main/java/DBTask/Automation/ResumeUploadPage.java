package DBTask.Automation;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class ResumeUploadPage extends BasePage{

	public ResumeUploadPage(WebDriver driver) {
		super(driver);
	}

	public void Uploadafile() throws InterruptedException, AWTException {

		// Upload CV/resume to create a profile link click
		driver.findElement(By.xpath("//*[text()='Upload CV/resume to create a profile']")).click();
		Thread.sleep(2000);
		// Upload from computer button click
		UploadfilefromComputer();
	}
		
		public void UploadfilefromComputer() throws InterruptedException, AWTException
		{
		
		driver.findElement(By.id("methodButton--file")).click();
		Thread.sleep(2000);
	
		File file = new File("/Users/madhubabusunkara/Desktop/Resume/new_cv/Renuka_Resume_Python.pdf");
		
		StringSelection stringSelection= new StringSelection(file.getAbsolutePath());
		
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_META);
		 
		robot.keyPress(KeyEvent.VK_TAB);
		 
		robot.keyRelease(KeyEvent.VK_META);
		 
		robot.keyRelease(KeyEvent.VK_TAB);
		 
		robot.delay(500);
		 
		//Open Goto window
		 
		robot.keyPress(KeyEvent.VK_META);
		 
		robot.keyPress(KeyEvent.VK_SHIFT);
		 
		robot.keyPress(KeyEvent.VK_G);
		 
		robot.keyRelease(KeyEvent.VK_META);
		 
		robot.keyRelease(KeyEvent.VK_SHIFT);
		 
		robot.keyRelease(KeyEvent.VK_G);
		 
		//Paste the clipboard value
		 
		robot.keyPress(KeyEvent.VK_META);
		 
		robot.keyPress(KeyEvent.VK_V);
		 
		robot.keyRelease(KeyEvent.VK_META);
		 
		robot.keyRelease(KeyEvent.VK_V);
		 
		//Press Enter key to close the Goto window and Upload window
		 

		robot.keyPress(KeyEvent.VK_ENTER);
		 
		robot.keyRelease(KeyEvent.VK_ENTER);
		 
		robot.delay(500);

		
		robot.keyPress(KeyEvent.VK_ENTER);
		 
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		Thread.sleep(2000);
		
		//Continue button Click
		
		driver.findElement(By.id("uploadFileResume")).click();
			
	//	ResumeDetailsPage ResumeDetails = new ResumeDetailsPage(driver);
	//	ResumeDetails.DetailsPageForResumeUpload();
	
	}

}