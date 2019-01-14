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

environments {}

// To run the tests with all browsers just run “./gradlew test”

baseUrl = "http://gebish.org"
reportsDir = 'target/geb-reports'