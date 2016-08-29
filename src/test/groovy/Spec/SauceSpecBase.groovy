package Spec

import com.saucelabs.common.SauceOnDemandAuthentication
import com.saucelabs.common.SauceOnDemandSessionIdProvider
import com.saucelabs.junit.SauceOnDemandTestWatcher
import geb.spock.GebSpec
import groovy.json.JsonSlurper
import org.junit.Rule
import org.junit.rules.TestName
import org.openqa.selenium.WebDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxProfile
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver
import spock.lang.Stepwise
import spock.lang.Shared

/**
 * Created by mehmetgerceker on 11/18/15.
 */
class SauceSpecBase extends GebSpec implements SauceOnDemandSessionIdProvider{
    public String username = System.getenv("SAUCE_USERNAME")
    public String accesskey = System.getenv("SAUCE_ACCESS_KEY")

    /**
     * Represents the browser to be used as part of the test run.
     */
    private String browser
    /**
     * Represents the operating system to be used as part of the test run.
     */
    private String os
    /**
     * Represents the version of the browser to be used as part of the test run.
     */
    private String version
    /**
     * Represents the deviceName of mobile device
     */
    private String deviceName
    /**
     * Represents the device-orientation of mobile device
     */
    private String deviceOrientation
    /**
     * Instance variable which contains the Sauce Job Id.
     */
    private String sessionId

    private static int counter

    /**
     * The {@link WebDriver} instance which is used to perform browser interactions with.
     */
    //private WebDriver driver

    private isSpecStepwise() {
        this.class.getAnnotation(Stepwise) != null
    }

    public void setupSpec() throws Exception {
        counter = 0
    }

    public SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication(username, accesskey)

    @Rule
    public SauceOnDemandTestWatcher resultReportingTestWatcher = new SauceOnDemandTestWatcher(this, authentication)

    @Rule
    public TestName name = new TestName() {
        public String getMethodName() {
            return super.getMethodName();
            //return String.format("%s : (%s %s %s)", super.getMethodName(), os, browser, version)
        }
    }

    /**
    *
    * @return the value of the Sauce Job id.
    */
    @Override
    public String getSessionId() {
        return sessionId
    }

    public void setup() throws Exception {
        Map<String, String> capMap;
        String capabilityString = System.getProperty("geb.saucelabs.capabilities")

        // capabilityString = '{"browserName": "MicrosoftEdge", "platform": "Windows 10", "version": "20.10240"}'

        if (capabilityString) {
            capMap =
                    new JsonSlurper()
                            .parseText(capabilityString)



            DesiredCapabilities capabilities = new DesiredCapabilities(capMap)

            String methodName = name.getMethodName();
            String specName = this.class.getSimpleName();
            capabilities.setCapability("name", String.format("%s.%s", specName, methodName));


            driver = new RemoteWebDriver(
                    new URL("http://" + authentication.getUsername() + ":" + authentication.getAccessKey() +
                            "@ondemand.saucelabs.com:80/wd/hub"),
                    capabilities);

            this.sessionId = (((RemoteWebDriver) driver).getSessionId()).toString()
        } else {
            if ( counter == 0 | !isSpecStepwise()) {
                FirefoxProfile profile = new FirefoxProfile()
                driver = new FirefoxDriver(profile)
                counter ++
            }
        }
    }

    @Override
    public void cleanup() throws Exception {
        if(!isSpecStepwise()) {
            driver.quit()
        }
    }

}
