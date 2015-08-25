# TestResults
This Project is to automate Test Execution Reporting
Tech stack used:
1) Java Servlets
2) MySQL
Prerequisites:
1) An excel sheet for the Testets to update the test status. Has the below columns
TestID	TestName	Status	Keyword	TesterName	TestSuite	DeviceName	Device2	Device3
2) Save the file as a csv file (For easy breaking of data using Java)

What does the code do:
1)class PopulateData populates our test execution data in csv file to table in MySQL DB
2)class ResultForm queries the above MySQL data, creates a HTML based reporting, built by a servlet.

Note:
Due to time crunch, you won't find lot of developer best practices
