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
	public void testHomeCredentialAdd() {
		testLoginValid();

		HomeCredential homeCredential = new HomeCredential(driver);
		homeCredential.assignCredentialData("www.gmail.com", "timmi", "321");
		homeCredential.submitCredential();

		Assertions.assertEquals("Result", driver.getTitle());

		ResultPage resultPage = new ResultPage(driver);
		Assertions.assertEquals("Your changes were successfully saved. Click here to continue.", resultPage.getSuccessMessage());
		resultPage.linkHomeClick();

		Assertions.assertEquals("Home", driver.getTitle());
	}
}
