## Groovy-Geb-Spock-Selenium

This code is provided on an "AS-IS” basis without warranty of any kind, either express or implied, including without limitation any implied warranties of condition, uninterrupted use, merchantability, fitness for a particular purpose, or non-infringement. Your tests and testing environments may require you to modify this framework. Issues regarding this framework should be submitted through GitHub. For questions regarding Sauce Labs integration, please see the Sauce Labs documentation at https://wiki.saucelabs.com/. This framework is not maintained by Sauce Labs Support.

### Environment Setup

1. Global Dependencies
    * Install Maven
    	https://maven.apache.org/install.html
    * Or Install Maven with Homebrew
    	http://brew.sh/
    ```
    $ brew install maven
    ```
2. Sauce Credentials
    * In the terminal export your Sauce Labs Credentials as environmental variables:
    ```
    $ export SAUCE_USERNAME=<your Sauce Labs username>
	$ export SAUCE_ACCESS_KEY=<your Sauce Labs access key>
    ```
3. Project Dependencies
	* Check that Packages are available
	```
	$ cd Groovy-Geb-Spock-Selenium
	$ mvn test-compile
	```
	* You may also want to run the command below to check for outdated dependencies. Please be sure to verify and review updates before editing your pom.xml file. The updated packages may or may not be compatible with your code.
	```
	$ mvn versions:display-dependency-updates
	```
### Running Tests

Tests in Parallel:

* Edit the **gen.saucelabs.capabilities.json** file with the configurations you'd like to run. Each line needs to be a valid json object and the file needs to end with a newline. As shown below. 
```json
{"browserName": "MicrosoftEdge", "platform": "Windows 10", "version": "20.10240"}
{"browserName": "Firefox", "platform": "Windows 10", "version": "42"}
```
Please refer to the [Sauce Platform Configurator](https://wiki.saucelabs.com/display/DOCS/Platform+Configurator/) for more information.

* Use the command below from the root of the project.
```bash
./run.sh
```
It will run the tests in your project with each of the configurations listed concurrently by configuration. i.e. all configurations will run at the same time. 
Individual test outputs will be routed to a log file named after the configuration in the project root folder. 
```bash
==> {"browserName": "Firefox", "platform": "Windows 10", "version": "42"}.log
```

* For debug/local runs the test suite will default to Firefox. Use the command below form project root folder to run tests locally.
```bash 
mvn clean test
```
[Sauce Labs Dashboard](https://saucelabs.com/beta/dashboard/)

### Advice/Troubleshooting
1. It may be useful to use a Java IDE such as IntelliJ or Eclipse to help troubleshoot potential issues. 

### Resources
##### [Sauce Labs Documentation](https://wiki.saucelabs.com/)

##### [SeleniumHQ Documentation](http://www.seleniumhq.org/docs/)

##### [Groovy Documentation](http://www.groovy-lang.org/documentation.html)

##### [Spock Documentation](http://spockframework.github.io/spock/docs/1.0/index.html)

##### [Geb Documentation](http://www.gebish.org/manual/current/)

##### [Stack Overflow](http://stackoverflow.com/)
