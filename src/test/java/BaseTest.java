import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    protected WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.out.println("Initializing driver...");
        driver = new ChromeDriver();
    }
    @AfterTest
    public void tearDown() {
        System.out.println("Closing the driver...");
        driver.quit();
    }
}
