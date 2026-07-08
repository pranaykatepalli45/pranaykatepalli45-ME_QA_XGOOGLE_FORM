package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import java.time.Duration;
import java.util.logging.Level;
// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
    ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation.
     * Follow `testCase01` `testCase02`... format or what is provided in
     * instructions
     */

    @Test
    public void testCase01() throws InterruptedException {
        logStatus("Start TestCase", "testCase01: Submit google form", "DONE");
        Wrappers w = new Wrappers(driver);

        w.openUrl("https://forms.gle/wjPkzeSEk1CM7KgGA");
        
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

        w.texttobeEntered(By.xpath(
                "//input[@type='text']"),
                "Crio Learner");

        String epochString = w.getepochString();

        String textarea = "I want to be the best QA Engineer! " + epochString;

        w.texttobeEntered(
                By.xpath("//div[contains(., 'Automation')]//textarea"),
                textarea);

        String xpathExpression = String.format("//div[@data-value=\"%s\"]", "3 - 5");

        w.selectRadio(By.xpath(xpathExpression));

        String xpathExpression1 = String.format("//div[@data-answer-value=\"%s\"]", "Selenium");
        String xpathExpression2 = String.format("//div[@data-answer-value=\"%s\"]", "TestNG");

        w.selectCheckbox(By.xpath(xpathExpression1));
        w.selectCheckbox(By.xpath(xpathExpression2));

        w.selectDropdown(By.xpath("//div[@role='listbox']"), "Mr");

        w.date(By.xpath("//input[@type= 'date']"));

        w.texttobeEntered(
                By.xpath("//input[@role = 'combobox' and @aria-label='Hour']"),
                "7");

        w.texttobeEntered(
                By.xpath("//input[@role = 'combobox' and @aria-label='Minute']"),
                "30");

        w.click(By.xpath("//div[@role= 'button' and @aria-label = 'Submit']"));

        String Message = w.successMessage(By.xpath("//div[contains(text(), 'Thanks for your response')]"));

        boolean status = w.successMessageStatus(Message);

        assertTrue(status, "Failed to submit response");

        System.out.println(Message);
        
        logStatus("End TestCase", "testCase01: Submit google form : ", status ? "PASS" : "FAIL");
    }

    private void logStatus(String type, String message, String status) {
        System.out.println(String.format("%s |  %s  |  %s | %s", String.valueOf(java.time.LocalDateTime.now()), type,
                message, status));;
    }

    /*
     * Do not change the provided methods unless necessary, they will help in
     * automation and assessment
     */
    @BeforeTest
    public void startBrowser() {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log");

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        System.out.println("Browser Lauched");
    }

    @AfterTest
    public void endTest() {
        driver.close();
        driver.quit();

    }
}