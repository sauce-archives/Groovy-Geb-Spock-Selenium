test_all:
	make -j test_win7_ie11 test_win8_firefox_44

test_win7_ie11:
	mvn -Dgeb.saucelabs.capabilities='{"browserName": "internet explorer", "platform": "Windows 7", "version": "11"}' clean tests

test_win8_firefox_44:
	mvn -Dgeb.saucelabs.capabilities='{"browserName": "Firefox", "platform": "Windows 8.1", "version": "44"}' clean test