Feature: Validating ratesAPI functionality 

@Testcase1 
Scenario: Verify if ratesAPI is called and given 200 status code 
	Given GetRatesAPI is called for latest exchange rates 
	When  user calls "getRateAPI" with "GET" http request 
	Then the API call got success with status code 200 
	
	
@Testcase2 
Scenario: 
	Verify if ratesAPI is called then all the expected fields are present in response body 
	Given GetRatesAPI is called for latest exchange rates 
	When  user calls "getRateAPI" with "GET" http request 
	Then verify the response have "EUR" in "base" key field 
	
@Testcase3 
Scenario: 
	Verify user is getting error message when an incorrect or incomplete url is provided 
	Given GetRatesAPI is called for latest exchange rates 
	When  user calls "getWrongURL" with "GET" http request 
	Then verify the response have "time data 'wonrgEndPoint' does not match format '%Y-%m-%d'" in "error" key field 
	
	
@Testcase4 
Scenario: 
	Verify if  200 status code is found when ratesAPI is called for a Specific date Exchange rates 
	Given GetRatesAPI is called for latest exchange rates 
	When  user calls exchange rate for "2010-01-12" with "GET" http request 
	Then the API call got success with status code 200 
	
@Testcase5 
Scenario: 
	Verify if ratesAPI is called then all the expected fields are present in response body for a Specific date Exchange rates 
	Given GetRatesAPI is called for latest exchange rates 
	When  user calls exchange rate for "2010-01-12" with "GET" http request 
	Then verify the response have "EUR" in "base" key field 
	
@Testcase6 
Scenario: 
	Verify if date field matches for current date exchange rates with Future date exchange rates when ratesAPI is called for a Future date 
	Given GetRatesAPI is called for latest exchange rates 
	When  user calls exchange rate for "2021-01-15" with "GET" http request 
	Then verify the response matches for current date exchange rates with "2021-01-15" exchange rates 
	
	