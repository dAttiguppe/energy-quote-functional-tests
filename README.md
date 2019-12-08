= energycomparisonfunctionaltests

This project is dedicated to testing the CTM energy comparison journey via Selenium/Java/Cucumber-JVM facilities.

Java Maven Cucumber Selenium Setup	
Java + Junit + Cucumber + Selenium testing cases
This repo contains the Maven setup for Cucumber / Selenium, with tests around energy comparison journey on the CTM website, which can be run on local browsers. It helps to setup a BDD environment for User Acceptance tests.
This repo can be run on local Windows Platform, as long as you have Java/Maven setup correctly.
Setup
Install on Local Machine
Install Java & Maven on your local machine


[[quick]]
== Quick start

. Clone project
. Open in IDEA
. Create new *Junit* run/debug configuration
. Refer to the testConfig.png for details
. Start run/debug config +

Test suite will use the specified browser and use the following url - https://energy.comparethemarket.com/energy/v2/?AFFCLIE=TSTT
by default to run all features/scenarios marked with `@EnergyQuoteTests` annotation.

If you want to run test individual tests then use the -Dcucumber.options="--tags @TariffsUserCantSwitchTo for example to run the scenario 'To verify the available tariffs that the user can't switch to'


*Command-line to run:*

**`mvn clean test -Dbrowser=chrome  -Dcucumber.options="--tags @TariffsUserCantSwitchTo"`**

Various **tags** like **~@Ignore** to exclude some scenarios from the test suite can be
used like below from the command line

**`mvn test -Dbrowser="chrome"  -DcucumberOptions=" --tags ~@TariffsUserCantSwitchTo"`**


The reports generated will be available under the following path
\reports

The chrome and firefox driver can be changed under the below path if required
\src\test\resources\chromeDriver
\src\test\resources\firefoxDriver


Documentation

Selenium - https://selenium.dev/
JAVA - https://docs.oracle.com/javase/8/docs/api/
Cucumber - https://cucumber.io/docs
MAVEN - https://maven.apache.org/

