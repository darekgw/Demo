Feature: Validating Place API's

  @AddPlace @Regression
  Scenario Outline: Verify if Place is being Successfully added using AddPlaceAPI
    Given Add Place Payload "<name>" "<language>" "<address>"
    When User calls "AddPlaceAPI" with "Post" http request
    Then The API call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_Id created maps to "<name>" using "GetPlaceAPI"

    Examples:
    |name     |language | address              |
    |AAhouse |English  | World cross center |
#    |BBhouse |French  | Village cross center |
#    |CChouse |French  | Village cross center |

  @DeletePlace @Regression
  Scenario: Verify if Delete functionality is working
    Given Delete Place Payload
    When User calls "DeletePlaceAPI" with "post" http request
    Then The API call is success with status code 200
    And "status" in response body is "OK"

