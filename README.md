Using Sauce Labs with Geb-Spock and Junit
=========================================

## Setup:

* Install [maven](https://maven.apache.org/)
* Install a recent version of the [JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* Set environment variables:
    * SAUCE_USERNAME=[your user name]
    * SAUCE_ACCESS_KEY=[your access/api key]
    
## Runing Tests:

* Edit the **gen.saucelabs.capabilities.json** file with the configurations you'd like to run. Each line needs to be a valid json object and the file needs to end with a newline. As shown below. 
<pre>
{"browserName": "MicrosoftEdge", "platform": "Windows 10", "version": "20.10240"}<br>
{"browserName": "Firefox", "platform": "Windows 10", "version": "42"}<br>
</pre>
Please refer to the [Sauce Platform Configurator](https://wiki.saucelabs.com/display/DOCS/Platform+Configurator/) for more information.
* Use the command below from the root of the project.
<pre> > ./run.sh</pre>
It will run the tests in your project with each of the configurations listed concurrently by configuration. i.e. all configurations will run at the same time. 
Individual test outputs will be routed to a log file named after the configuration in the project root folder. 
<pre>==> {"browserName": "Firefox", "platform": "Windows 10", "version": "42"}.log</pre>
* For debug/local runs the test suite will default to Firefox. Use the command below form project root folder to run tests locally.
<pre>> mvn clean test</pre>

## Known Issues:
* Concurrency using maven is not available.
 