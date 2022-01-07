package resources;

import pojo.Location;
import pojo.Place;

import java.util.List;

public class TestDataBuild {

	public Place addPlacePayLoad(String name, String language, String address) {
		Place newPlace = new Place();
		Location newLocation = new Location();
		newLocation.setLat(-38.383494);
		newLocation.setLng(33.247545);
		newPlace.setLocation(newLocation);
		newPlace.setAccuracy(40);
		newPlace.setName(name);
		newPlace.setPhone_number("+48123123");
		newPlace.setTypes(List.of("mieszkanie", "nora"));
		newPlace.setAddress(address);
		newPlace.setWebsite("www.nora.pl");
		newPlace.setLanguage(language);
		return newPlace;
	}

	public String deletePlacePayload(String place_id) {
		return "{\n" +
				"    \"place_id\": \""+place_id+"\"\n" +
				"}";
	}

}
