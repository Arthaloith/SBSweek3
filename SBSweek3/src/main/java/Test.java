import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class Test {

    public WebDriver edriver;
    public WebDriverWait ewait;
    @FindBy(how = How.XPATH, using = "//*[@id=\"login2\"]")
    public WebElement loginButton;
    @FindBy(how = How.XPATH, using = "//*[@id=\"loginusername\"]")
    public WebElement username;
    @FindBy(how = How.XPATH, using = "//*[@id=\"loginpassword\"]")
    public WebElement password;
    @FindBy(how = How.XPATH, using = "//button[text()=\"Log in\"]")
    public WebElement loginButton2;

    public Test() {
        edriver = new ChromeDriver();
        PageFactory.initElements(edriver, this);
        ewait = new WebDriverWait(edriver, Duration.ofSeconds(5));;

    }
    @Before
    public void setUp(){
        edriver.get("https://www.demoblaze.com/#");
    }
    @org.junit.Test
    public void Test() throws Exception {

        waitUntilElementVisible(loginButton);
        loginButton.click();
        waitUntilElementVisible(username);
        sendKeys(username,"anmyson");
        sendKeys(password, "an24122002");
        waitUntilElementVisible(password);
        loginButton2.click();
    }
    @After
    public void tearDown() throws InterruptedException{

    }
    public void waitUntilElementVisible(WebElement element){
        int tryTimes = 0;
        while (tryTimes < 2) {
            try {
                ewait.until(ExpectedConditions.visibilityOf(element));
                break;
            } catch (StaleElementReferenceException se) {
                tryTimes++;
                if (tryTimes == 2)
                    throw se;
            }
        }
    }
    public void waitUntilElementVisible(String path) throws Exception{
        int tryTimes = 0;
        while (tryTimes < 2){
            try {
                WebElement element = edriver.findElement(By.xpath(path));
                ewait.until(ExpectedConditions.visibilityOf(element));
                break;
            }
            catch (StaleElementReferenceException se){
                tryTimes ++;
                if (tryTimes == 2)
                    throw se;
            }
        }
    }
    public void sendKeys(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

}
