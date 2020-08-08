package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import com.udacity.jwdnd.c1.review.SignupPage;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private SignupPage signupPage;
	private LoginPage loginPage;

	private WebDriver driver;
	private WebDriverWait wait;

	private final String username1          = "timmi";
	private final String url1               = "www.gmail.com";
	private final String password1          = "123";

	private final String username2          = "anton";
	private final String url2               = "www.xmail.com";
	private final String password2          = "321";

	private final String noteTitle1         = "Have many fun";
	private final String noteDescription1  	= "with udacity!";

	private final String noteTitle2         = "Have many super fun";
	private final String noteDescription2   = "with udacity and spring!";

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.firefoxdriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new FirefoxDriver();
		this.wait = new WebDriverWait(driver, 1000);
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	@Order(10)
	public void testSignupPage() {
		driver.get("http://localhost:" + this.port + "/signup");
		Assertions.assertEquals("Sign Up", driver.getTitle());

		SignupPage signupPage = new SignupPage(driver);
		signupPage.assignSignupFields("Ben", "Ten", "benni", "123");
		signupPage.submitForm();
		Assertions.assertEquals("You successfully signed up! Please continue to the login page.", signupPage.getSuccessMessage());
	}

/*	@Test
	@Order(11)
	public void testSignupPageUsernameExist() {
		driver.get("http://localhost:" + this.port + "/signup");
		Assertions.assertEquals("Sign Up", driver.getTitle());

		SignupPage signupPage = new SignupPage(driver);
		signupPage.assignSignupFields("Ben", "Ten", "benni", "123");
		signupPage.submitForm();
		Assertions.assertEquals("The username already exists.", signupPage.getErrorMessage());
	}

	@Test
	@Order(20)
	public void testLoginUsernameNotExist() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());

		LoginPage loginPage = new LoginPage(driver);
		loginPage.assignLoginFields("tommi", "123");
		loginPage.submitForm();
		Assertions.assertEquals("Invalid username or password",loginPage.getErrorMessage());
	}*/

	@Test
	@Order(21)
	public void testLoginValid() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());

		LoginPage loginPage = new LoginPage(driver);
		loginPage.assignLoginFields("benni", "123");
		loginPage.submitForm();

		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Home", driver.getTitle());
	}

/*	@Test
	@Order(30)
	public void testHomeLogout() {
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Home", driver.getTitle());

		HomePage homePage = new HomePage(driver);
		homePage.logoutClick();
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	@Order(31)
	public void testHomeAccess() {
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Login", driver.getTitle());
	}*/

	public void testResultPage() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert")));
		Assertions.assertEquals("Result", driver.getTitle());
		ResultPage resultPage = new ResultPage(driver);
		Assertions.assertEquals("Success", resultPage.getSuccessMessage());
		resultPage.linkHomeClick();
	}

	// Note
	public void testHomeNoteAdd() {
		HomePage homePage = new HomePage(driver);
		homePage.navNoteSectionClick();

		this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-notes-tab")));
		HomeNote homeNote = new HomeNote(driver);
		homeNote.noteAddClick();
		this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("btn-primary")));
		homeNote.assignNoteData(noteTitle1, noteDescription1);
		Assertions.assertEquals(noteTitle1, homeNote.getNoteTitle());
		Assertions.assertEquals(noteDescription1, homeNote.getNoteDescription());
		homeNote.submitNote();

		testResultPage();
	}

	public void testHomeNoteFromList(String title, String description) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-notes-tab")));
		HomePage homePage = new HomePage(driver);
		homePage.navNoteSectionClick();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("list-notes-title")));
		HomeNote homeNote = new HomeNote(driver);
		Assertions.assertEquals(title, homeNote.getRecentNoteTitle());
		Assertions.assertEquals(description, homeNote.getRecentNoteDescription());
	}

	@Test
	@Order(40)
	public void testHomeNoteAddAndCheck() throws InterruptedException {
		testLoginValid();
		driver.get("http://localhost:" + this.port + "/home");
		testHomeNoteAdd();
		testHomeNoteFromList(noteTitle1,noteDescription1);
	}

	public void testHomeNoteEditUpdateNote() {
		HomePage homePage = new HomePage(driver);
		homePage.navNoteSectionClick();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("list-notes-edit")));
		HomeNote homeNote = new HomeNote(driver);
		homeNote.noteEditClick();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("note-title")));
		homeNote.assignNoteData(noteTitle2,noteDescription2);
		Assertions.assertEquals(noteTitle2, homeNote.getNoteTitle());
		Assertions.assertEquals(noteDescription2, homeNote.getNoteDescription());
		homeNote.submitNote();

		testResultPage();
	}

	@Test
	@Order(41)
	public void testHomeNoteEditAndCheck() throws InterruptedException {
		testLoginValid();
		driver.get("http://localhost:" + this.port + "/home");
		testHomeNoteEditUpdateNote();
		testHomeNoteFromList(noteTitle2,noteDescription2);
	}

	public void testHomeNoteDeleteNote() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-notes-tab")));
		HomePage homePage = new HomePage(driver);
		homePage.navNoteSectionClick();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("list-notes-delete")));
		HomeNote homeNote = new HomeNote(driver);
		homeNote.noteDeleteClick();

		testResultPage();
	}

	public void testHomeNoteIsNoteDeleted() {
		this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-notes-tab")));
		HomePage homePage = new HomePage(driver);
		homePage.navNoteSectionClick();
		this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userTable")));
		Assertions.assertFalse(driver.getPageSource().contains("list-notes-delete"));
	}

	@Test
	@Order(42)
	public void testHomeNoteDeleteAndCheck() throws InterruptedException {
		testLoginValid();
		driver.get("http://localhost:" + this.port + "/home");
		testHomeNoteDeleteNote();
		testHomeNoteIsNoteDeleted();
	}

	/*@Test
	@Order(50)
	public void testHomeCredentialAdd() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.navCredentialSectionClick();

		HomeCredential homeCredential = new HomeCredential(driver);
		homeCredential.credentialAddClick();
		homeCredential.assignCredentialData(url1, username1, password1);
		homeCredential.submitCredential();

		Thread.sleep(1000);
		Assertions.assertEquals("Result", driver.getTitle());

		ResultPage resultPage = new ResultPage(driver);
		Assertions.assertEquals("Success", resultPage.getSuccessMessage());
		resultPage.linkHomeClick();

		Thread.sleep(1000);
		Assertions.assertEquals("Home", driver.getTitle());
	}

	public void testHomeCredentialFromList(String url, String username, String password) {
		HomePage homePage = new HomePage(driver);
		homePage.navCredentialSectionClick();

		HomeCredential homeCredential = new HomeCredential(driver);
		Assertions.assertEquals(url, homeCredential.getRecentCredentialURL());
		Assertions.assertEquals(username, homeCredential.getRecentCredentialUsername());
		Assertions.assertNotEquals(password, homeCredential.getRecentCredentialPassword());
	}

	@Test
	@Order(51)
	public void testHomeCredentialFromListAfterAdd() {
		testHomeCredentialFromList(url1,username1,password1);
	}

	@Test
	@Order(52)
	public void testHomeCredentialCheckEditFieldsInModalDialog() {
		testLoginValid();

		HomePage homePage = new HomePage(driver);
		homePage.navCredentialSectionClick();

		HomeCredential homeCredential = new HomeCredential(driver);
		homeCredential.credentialEditClick();
		Assertions.assertEquals(url1, homeCredential.getModalURL());
		Assertions.assertEquals(username1, homeCredential.getModalUsername());
		Assertions.assertEquals(password1, homeCredential.getModalPassword());
	}

	@Test
	@Order(53)
	public void testHomeCredentialEditUpdateCredential() throws InterruptedException {
		testLoginValid();

		HomePage homePage = new HomePage(driver);
		homePage.navCredentialSectionClick();

		HomeCredential homeCredential = new HomeCredential(driver);
		homeCredential.credentialEditClick();
		homeCredential.assignCredentialData(url2, username2, password2);
		homeCredential.submitCredential();

		Thread.sleep(1000);
		Assertions.assertEquals("Result", driver.getTitle());

		ResultPage resultPage = new ResultPage(driver);
		Assertions.assertEquals("Success", resultPage.getSuccessMessage());
		resultPage.linkHomeClick();

		Thread.sleep(1000);
		Assertions.assertEquals("Home", driver.getTitle());
	}

	@Test
	@Order(54)
	public void testHomeCredentialFromListAfterUpdate() {
		testHomeCredentialFromList(url2,username2,password2);
	}*/
}
