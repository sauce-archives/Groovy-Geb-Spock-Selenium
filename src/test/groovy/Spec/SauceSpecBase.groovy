package Spec

import com.saucelabs.common.SauceOnDemandAuthentication
import com.saucelabs.common.SauceOnDemandSessionIdProvider
import com.saucelabs.common.Utils
import CustomUtils.CustomSauceOnDemandTestWatcher
import geb.driver.CachingDriverFactory
import geb.spock.GebSpec
import groovy.json.JsonSlurper
import org.junit.Rule
import org.junit.rules.TestName
import org.junit.runner.Description
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxProfile
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver
import spock.lang.Shared
import spock.lang.Stepwise


class SauceSpecBase extends GebSpec implements SauceOnDemandSessionIdProvider {
    public String username = System.getenv("SAUCE_USERNAME")
    public String accessKey = System.getenv("SAUCE_ACCESS_KEY")

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

    private static boolean driverCreated

    public SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication(username, accessKey)

    @Rule
    public CustomSauceOnDemandTestWatcher resultReportingTestWatcher = new CustomSauceOnDemandTestWatcher(this, username, accessKey, true)

    @Rule
    public TestName name = new TestName() {
        public String getMethodName() {
            return super.getMethodName()
        }
    }

    private isSpecStepwise() {
        this.class.getAnnotation(Stepwise) != null
    }

    /**
    *
    * @return the value of the Sauce Job id.
    */
    @Override
    public String getSessionId() {
        return sessionId
    }

    public void setupSpec() throws Exception {
        driverCreated = false
    }

    public void setup() throws Exception {
        Map<String, String> capMap
        String capabilityString = System.getProperty("geb.saucelabs.capabilities")

        if (!driverCreated || !isSpecStepwise()) {
            if (capabilityString) {
                capMap = new JsonSlurper().parseText(capabilityString)
                DesiredCapabilities capabilities = new DesiredCapabilities(capMap)
                String methodName = name.getMethodName()
                String specName = this.class.getSimpleName()
                if(isSpecStepwise()){
                    methodName = "All tests in " + specName
                }
                capabilities.setCapability("name", String.format("%s.%s", specName, methodName))
                capabilities.setCapability("newCommandTimeout", 180)
                driver = new RemoteWebDriver(
                        new URL("https://" + authentication.getUsername() + ":" + authentication.getAccessKey() +
                                "@ondemand.saucelabs.com:443/wd/hub"), capabilities)

                this.sessionId = (((RemoteWebDriver) driver).getSessionId()).toString()
            } else {
                FirefoxProfile profile = new FirefoxProfile()
                driver = new FirefoxDriver(profile)
            }
            driverCreated = true
        }
    }

    @Override
    public void cleanup() throws Exception {
        if(!isSpecStepwise()){
            driver.quit()
        }
    }
}