package stepDefinitions;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {

	MyStepdefs myStepdefs;

	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		//write a code that will give you place_id
		//execute this code only when place_id is null
		if(MyStepdefs.place_id == null) {
			String placeName = "DDhouse";
			myStepdefs = new MyStepdefs();
			myStepdefs.add_place_payload(placeName, "Suahily", "Tazania, Dar-er-salam, ul. Murarska 5");
			myStepdefs.userCallsWithHttpRequest("AddPlaceAPI", "post");
			myStepdefs.verifyPlace_IdCreatedMapsToUsing(placeName, "GetPlaceAPI");
			System.out.println("delete hook");
		}

	}
}
