package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {
	private static RequestSpecification reqSpec;
	private ResponseSpecification resSpec;

	public RequestSpecification requestSpecification() throws IOException {
		if(reqSpec == null) {
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
			reqSpec = new RequestSpecBuilder()
					.setBaseUri(getGlobalValue("baseUrl"))
					.setContentType(ContentType.JSON)
					.addQueryParam("key", "qaclick123")
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.build();
		}
		return reqSpec;
	}

	public ResponseSpecification responseSpecification() {
		resSpec = new ResponseSpecBuilder()
				.expectStatusCode(200)
				.expectContentType(ContentType.JSON)
				.build();
		return resSpec;

	}

	public static String getGlobalValue(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("src/test/java/resources/global.properties");
		prop.load(fis);
		return prop.getProperty(key);
	}

	public String getJsonPath(Response response, String key) {
		String res = response.asString();
		JsonPath jp = new JsonPath(res);
		return jp.get(key);
	}

}
