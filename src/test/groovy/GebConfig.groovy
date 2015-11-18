import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxProfile

driver = {
    FirefoxProfile profile = new FirefoxProfile()
    new FirefoxDriver(profile)
}