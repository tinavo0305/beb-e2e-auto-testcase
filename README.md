# beb
Bird Eats Bug automation tests

# Project structure

### src/main/java

Contains functions and objects to build the test classes

**commons**

- _AbstractTest.java_: Contains methods to instantiate web driver, go to base url of the website and another method to quit the browser when finishing the tests. 
This class will be the parent of all test classes, the test classes will inherit AbstactTest class to re-use the setup and close browser methods
- _Selenium.java_: Abstract class contains the methods provided by Selenium library. The methods are general and will be called by the functions of page objects in pages package

**objects**

Contains objects to store the test data such as account, session details

**pages**

- Each page object represents each page of the application. Each page contains elements and methods to interact with elements of the page
- Page objects will extend Selenium abstract class to re-use all its functions

### src/test/java

Contains all test classes. Each test class have 3 sections:
 - Before class: To instantiate drivers and page objects
 - Tests: test steps
 - After class: quit the browsers

### src/test/resources

Contains resources to run the testt such as chrome driver

### pom.xml

Contains library imported from Maven repository

# How to run the tests

- From Eclipse IDE, right click on each test under _src/test/java_ and select Run as TestNG Test
- Or create an .xml file that is similar to Signin.xml under _src/test/java_ to spefify which tests you want to run on which browser. You can run the tests in parallel using this .xml file
