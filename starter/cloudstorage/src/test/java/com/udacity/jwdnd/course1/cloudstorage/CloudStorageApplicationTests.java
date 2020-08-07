package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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

	private final String username1          = "timmi";
	private final String url1               = "www.gmail.com";
	private final String password1          = "123";

	private final String username2          = "anton";
	private final String url2               = "www.xmail.com";
	private final String password2          = "321";

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.firefoxdriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new FirefoxDriver();
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

	@Test
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
	}

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

	@Test
	@Order(30)
	public void testHomeLogout() {
		testLoginValid();

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
	}

	@Test
	@Order(50)
	public void testHomeCredentialAdd() throws InterruptedException {
		testLoginValid();

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
		testLoginValid();

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
	}
}
