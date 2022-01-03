package DBTask.Automation;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class SearchPage extends BasePage {

	private final By DisplayedDataLocator = By.xpath("//li[@class='listSingleColumnItem']");
	public final By searchTabLocator = By.id("155704");
	public final By enterSearchButton = By.id("155705-submit");
	private final By locationFiledlocator = By.xpath("//*[text()='Location']");
	private final By countryBoxFiled = By.id("select2-3_387_3-container");
	private final By selectCountryFieldLocator = By.xpath("//*[@class='select2-dropdown bigdrop select2-dropdown--above']/span/input");
	private final By selectStateFieldLocator = By.id("3_171_3-search__field");
	private final By selectCityFieldLocator = By.xpath("//*[@class='select2-selection select2-selection--multiple select2Container3_172_3']");
	private final By companyBoxFieldLocator = By.xpath("//*[text()='Company']");
	private final By selectCompanyFieldLocator = By.id("3_388_3");
	private final By entryLevel = By.xpath("//*[text()='Entry Level']");
	private final By selectEntryLevel = By.id("3_57_3_55253");
	private final By typeOfContractLocator = By.xpath("//*[text()='Type of Contract']");
	private final By selectTypeOfContract = By.xpath("//*[text()='Company']");
	private final By selectContactType = By.id("3_68_3");
	private final By filterButton = By.xpath("//*[@class='saveButton']");
	private final By selectJobGroupLocator = By.xpath("//*[text()='Job Group']");
	private final By selectJobGroupField =By.id("3_116_3-search__field");
	private final By setJobGroupField =By.id("li2353300");
	private final By FirstRowLocator = By.xpath("//*[@class='listSingleColumn']/li[1]/a[1]/h3");
	
	private String TextCountry;
	private String TextState;
	private String TextCity;
	private String TextCompany;
	private String TextJobGroup;
	private String TextContractType;
	
	public String SearchKeyWord;

	public SearchPage(WebDriver driver) {
		super(driver);
	}
	/**************************************************************************
	 * Test Objective: Display search results for job keyword
	 * Precondition : Jobs page is opened and User is not registered
	 * Step 1 : Enter any keyword in the search field 
	 * step 2 : check the displayed results are in relation with a keyword.
	 * 
	 */
	
	// Displays all the jobs that are related with the given keyword
	public void displaySearchResultsForTheJobKeyword(String keyWord) throws InterruptedException {
		SearchKeyWord = keyWord;
		find(searchTabLocator).sendKeys(SearchKeyWord);
		click(enterSearchButton);
		setWait(10);
		// Checks weather the keyword is present in the displayed Jobs list.
		CheckDisplayedElements();

	}
	protected void searchJobsWithJobKeyword(String keyWord)
	{
		SearchKeyWord = keyWord;
		find(searchTabLocator).sendKeys(SearchKeyWord);
		click(enterSearchButton);
	}
	
	
	// method to check and assert the presence of filter or keywords.
	private void CheckEachDisplayedJob(String ActualString) {
		
		if(TextCountry!= null) {
			Assert.assertTrue(ActualString.contains(TextCountry));
		}
		
		if(TextState!= null) {
			Assert.assertTrue(ActualString.contains(TextState));
		}
		
		if(TextCity!= null) {
			Assert.assertTrue(ActualString.contains(TextCity));
		}
		
		if(SearchKeyWord != null) {
			Assert.assertTrue(ActualString.contains(SearchKeyWord));
			
		}
	}
		

	//Checks all the displayed jobs list
	private void CheckDisplayedElements() {
		
		List<WebElement> rows = findElements(DisplayedDataLocator);
		for (WebElement row : rows) {
			String ActualString = row.getText();
			// Checks and asserts for the keyword
			CheckEachDisplayedJob(ActualString);
		}	
		// To be extended for all the pages.
	}
	
	// clicks the corresponding job displayed in the first row.
	protected void clickDisplayedJobsInFirstRow()
	{
		click(FirstRowLocator);
	}
	
	
	// Method to feed the given text input for the given locator
	private void SetFilterInput(String text, By locator)throws InterruptedException{
		
		if(!text.isEmpty()) {
		
		WebElement TextField = find(locator);
		TextField.click();
		TextField.sendKeys(text);
		setWait(10);
		TextField.sendKeys(Keys.ENTER);
		}
		
	}
	
	// Method to feed the given text input for the given locator
	private void SelectFilterJobGroup(String text) throws InterruptedException{
		
		if(!text.isEmpty()) {
			
		WebElement JobGroup = find(selectJobGroupField);
		JobGroup.click();
		setWait(5);
		click(setJobGroupField);
	
		JobGroup.sendKeys(Keys.ENTER);
		}
	
	}
	
	// method to convert the company text input to index
	// Sets the inputs from drop down.
	private void SelectFilterCompany(String text, By locator)throws InterruptedException{
		
		if(!text.isEmpty()) {
			int dropBoxIndex = 0;
			
			if(text.equals("DB")) {
				dropBoxIndex = 2;
			}
			else if(text.equals("Arriva")){
				dropBoxIndex = 1;
			}
			
			SelectFilterInputFromDropdown(dropBoxIndex, locator);
			
		}
		
	}
	
	// method to convert the Contract text input to index
	// Sets the inputs from drop down.
	private void SelectFilterContractType(String text, By locator)throws InterruptedException{
		
		if(!text.isEmpty()) {
			int dropBoxIndex = 0;
			
			if(text.equals("Full Time")) {
				dropBoxIndex = 1;
			}
			else if(text.equals("Part Time")){
				dropBoxIndex = 2;
			}
			else if(text.equals("Any")){
				dropBoxIndex = 3;
			}
			
			SelectFilterInputFromDropdown(dropBoxIndex, locator);
			
		}
		
	}
	
	
	/***************************************************************/
	/***************The below methods are not used******************/
	/***************************************************************/
	
	// method to filter jobs by location
	public void FilterRefinementByLocation(String country, String state, String city) throws InterruptedException {
		String Company = ""; String EntryLevel= ""; String JobGroup= ""; String contractType= "";
		
		FilterbyRefinement(country, state, city, Company, EntryLevel, JobGroup, contractType);
	}
	// method to filter jobs by all parameters
	 public void FilterbyRefinement(String country, String state, String city, String Company, String EntryLevel,
	 String JobGroup, String contractType) throws InterruptedException {
		 
		TextCountry = country;
		TextState = state;
		TextCity = city;
		TextCompany = Company;
		TextJobGroup = JobGroup;
		TextContractType = contractType;
		 
		 
		// Location
		click(locationFiledlocator);
		click(countryBoxFiled);
		setWait(5);
		WebElement countryField = find(selectCountryFieldLocator);
		countryField.sendKeys(TextCountry);
		setWait(10);
		countryField.sendKeys(Keys.ENTER);

		SetFilterInput(TextState, selectStateFieldLocator);
		SetFilterInput(TextCity, selectCityFieldLocator);
			
		
		//Company
		click(companyBoxFieldLocator);
		SelectFilterCompany(TextCompany, selectCompanyFieldLocator);
		 
		//Entry Level
		click(entryLevel);
		click(selectEntryLevel);
		
		//JOB GROUP
		click(selectJobGroupLocator);
		SelectFilterJobGroup(TextJobGroup);
		
		// Type of Contract
		click(typeOfContractLocator );
		
		click(selectTypeOfContract);
		SelectFilterContractType(TextContractType, selectContactType);
		
		
		//FILTERBUTTON
		
		click(filterButton);
		
		
		// Checks the displayed elements
		CheckDisplayedElements();

	}
}
