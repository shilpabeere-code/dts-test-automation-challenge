package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginSteps {

    private WebDriver driver;
    private LoginPage loginPage;

    @Before
    public void setUp() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        // Initialize loginPage AFTER driver is ready
        loginPage = new LoginPage(driver);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("User is on the login page")
    public void userIsOnLoginPage() {
        driver.get("https://www.saucedemo.com/"); // Replace with your app URL
    }

    @When("User clicks on Login button")
    public void userClicksLoginButton() throws InterruptedException {
        loginPage.clickLogin();
    }

    @When("User enters username {string} and password {string}")
    public void userEntersUsernameAndPassword(String username, String password) throws InterruptedException {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @Then("User should see {string}")
    public void userShouldSee(String result) throws InterruptedException {
        if (result.equalsIgnoreCase("Dashboard")) {
            String expectedUrl = "https://www.saucedemo.com/inventory.html"; // replace with your URL
            assertEquals(expectedUrl, driver.getCurrentUrl());
        } else {
            System.out.println("Unexpected result: " + result);
        }
    }

    @Then("User should see error message {string}")
    public void userShouldSeeErrorMessage(String expectedText) throws InterruptedException {
        String actualText = loginPage.getErrorMessage();
        assertEquals(expectedText, actualText);
    }
}
