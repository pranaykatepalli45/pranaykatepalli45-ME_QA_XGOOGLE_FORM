package demo.wrappers;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.interactions.Actions;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */
    WebDriver driver;
    WebDriverWait wait;

    public Wrappers(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openUrl(String url) {
        try {
            driver.get(url);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public static void clickonElement(ChromeDriver driver, WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void texttobeEntered(By locator, String text) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            element.click();
            element.sendKeys(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selectRadio(By locator) {
        try {
            click(locator);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selectCheckbox(By locator) {
        try {
            click(locator);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selectDropdown(By locator, String visibleText) {
        try {
            Actions actions = new Actions(driver);
            WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

            dropdown.click();

            WebElement option = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//div[@role='option' and @data-value='" + visibleText + "']")));
            option.click();
            actions.moveToElement(option).click().perform();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void date(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            String dateValue = LocalDate.now().minusDays(7).format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            // System.out.println(dateValue);
            element.sendKeys(dateValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String successMessage(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return element.getText();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean successMessageStatus(String message){
        try{
            if(message!=null){
                return true;
            }else{
                return false;
            }
        }catch(Exception e){
            return false;
        }
    }

    public String getepochString(){
        String epochString = String.valueOf(System.currentTimeMillis() / 1000L);
        return epochString;
    }

}
