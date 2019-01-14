/*
	This is the Geb configuration file.
	
	See: http://www.gebish.org/manual/current/#configuration
*/


import org.openqa.selenium.MutableCapabilities
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver

waiting {
    timeout = 2
}

environments {
    sauce {
        driver = {
            String username = System.getenv("SAUCE_USERNAME");
            String accessKey = System.getenv("SAUCE_ACCESS_KEY");

            ChromeOptions chromeOpts = new ChromeOptions();
            chromeOpts.setExperimentalOption("w3c", true);

            MutableCapabilities sauceOpts = new MutableCapabilities();
            sauceOpts.setCapability("seleniumVersion", "3.141.1");
            sauceOpts.setCapability("username", username);
            sauceOpts.setCapability("accessKey", accessKey);

            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability(ChromeOptions.CAPABILITY, chromeOpts);
            caps.setCapability("sauce:options", sauceOpts);
            caps.setCapability("browserName", "googlechrome");
            caps.setCapability("browserVersion", "71.0");
            caps.setCapability("platformName", "windows 10");

            URL url = new URL("https://ondemand.saucelabs.com:443/wd/hub")

            new RemoteWebDriver(url, caps)
        }
    }

    // run via “./gradlew chromeTest”
    // See: http://code.google.com/p/selenium/wiki/ChromeDriver
    chrome {
        driver = { new ChromeDriver() }
    }

    // run via “./gradlew chromeHeadlessTest”
    // See: http://code.google.com/p/selenium/wiki/ChromeDriver
    chromeHeadless {
        driver = {
            ChromeOptions o = new ChromeOptions()
            o.addArguments('headless')
            new ChromeDriver(o)
        }
    }

    // run via “./gradlew firefoxTest”
    // See: http://code.google.com/p/selenium/wiki/FirefoxDriver
    firefox {
        atCheckWaiting = 1

        driver = { new FirefoxDriver() }
    }

}

// To run the tests with all browsers just run “./gradlew test”

baseUrl = "http://gebish.org"
reportsDir = 'target/geb-reports'