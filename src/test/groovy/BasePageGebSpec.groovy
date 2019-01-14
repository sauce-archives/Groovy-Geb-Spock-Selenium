import geb.driver.CachingDriverFactory
import geb.spock.GebSpec
import org.junit.Rule
import org.junit.rules.TestName
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.MutableCapabilities
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver

class BasePageGebSpec extends GebSpec {
    @Rule
    public TestName name = new TestName() {
        public String getMethodName() {
            return super.getMethodName()
        }
    }

    public String testDriver = System.getProperty('geb.env')

    public void setup() throws Exception {
        if (testDriver == "sauce") {
            ChromeOptions chromeOpts = new ChromeOptions();
            chromeOpts.setExperimentalOption("w3c", true);

            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability(ChromeOptions.CAPABILITY, chromeOpts);
            caps.setCapability("sauce:options", sauceCapabilities());
            caps.setCapability("browserName", "googlechrome");
            caps.setCapability("browserVersion", "71.0");
            caps.setCapability("platformName", "windows 10");

            URL url = new URL("https://ondemand.saucelabs.com:443/wd/hub");

            driver = new RemoteWebDriver(url, caps);
        } else {
            driver = new ChromeDriver();
        }
    }

    public MutableCapabilities sauceCapabilities() throws Exception {
        String username = System.getenv("SAUCE_USERNAME");
        String accessKey = System.getenv("SAUCE_ACCESS_KEY");
        String methodName = name.getMethodName()

        MutableCapabilities sauceOpts = new MutableCapabilities();
        sauceOpts.setCapability("seleniumVersion", "3.141.1");
        sauceOpts.setCapability("username", username);
        sauceOpts.setCapability("accessKey", accessKey);
        sauceOpts.setCapability("name", methodName);

        return sauceOpts;
    }

    @Override
    public void cleanup() throws Exception {
        driver.quit()
        CachingDriverFactory.clearCache()
    }
}