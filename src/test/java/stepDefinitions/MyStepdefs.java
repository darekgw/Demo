package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import pojo.Location;
import pojo.Place;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class MyStepdefs extends Utils {

	TestDataBuild data = new TestDataBuild();
	RequestSpecification res;
	Response response;
	static String place_id;

	@Given("Add Place Payload {string} {string} {string}")
	public void add_place_payload(String name, String language, String address) throws IOException {

		res = given()
				.spec(requestSpecification())
				.body(data.addPlacePayLoad(name, language, address));
	}

	@When("User calls {string} with {string} http request")
	public void userCallsWithHttpRequest(String resource, String method) {
		String resourceUrl = APIResources.valueOf(resource).getResource();

		if(method.equalsIgnoreCase("post"))
			response = res.when().post(resourceUrl);
		else if(method.equalsIgnoreCase("get"))
			response = res.when().get(resourceUrl);

		System.out.println(resourceUrl);
	}

	@Then("The API call is success with status code {int}")
	public void theAPICallIsSuccessWithStatusCode(int statusCode) {
		assertEquals(statusCode, response.getStatusCode());
	}

	@And("{string} in response body is {string}")
	public void inResponseBodyIs(String responseKey, String expectedValue) {
		String value = getJsonPath(response, responseKey);
		assertEquals(expectedValue, value);

		System.out.println(value);
	}

	@And("verify place_Id created maps to {string} using {string}")
	public void verifyPlace_IdCreatedMapsToUsing(String expectedName, String resource) throws IOException {
		place_id = getJsonPath(response, "place_id");

		res = given().spec(requestSpecification())
				.queryParam("place_id", place_id);

		userCallsWithHttpRequest(resource, "GET");

		String acctualName = getJsonPath(response, "name");

		assertEquals(expectedName, acctualName);

	}

	@Given("Delete Place Payload")
	public void deletePlacePayload() throws IOException {
		res = given()
				.spec(requestSpecification())
				.body(data.deletePlacePayload(place_id));
	}
}
