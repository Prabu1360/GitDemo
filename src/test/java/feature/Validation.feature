Feature: Validating Place API's

Scenario Outline: Verify id place is being verified Successfully added using AddPlaceAPI
	Given Place Payload with input details "<data input>"
	When user calls AddPlaceAPI with POST http request
	Then the API call us success with status code 200
	And status in response body is "OK"
	
Examples:

|data input |
| addPlaceAPI.ADDPOSTReq |
#| Raj   | +91 5678     |

Scenario: Verify newly added Place Id availablity using GETPlace_API

	Given Here we are going to View the details of the recently created API.
	When user calls GETPlaceAPI using the GET http request
	Then Verify the Response status code is 200
	And Verify the Name Phone Number in response body
	
#	Scenario: Verify newly delete Place Id availablity using DELETEPlace_API

#	Given Here we are going to delete the recently created API.
#	When user calls DeleteAPI request with DELETE http request
#	Then Verify Response status code is 200
#	And status in response body is OK