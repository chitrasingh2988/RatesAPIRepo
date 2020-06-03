package resource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utility {

	public static RequestSpecification reqSpec;
	PrintStream log;

	/**
	 * It is used to provide all the basic request details for an API
	 * 
	 * @return RequestSpecification reference
	 * @throws IOException
	 */
	public RequestSpecification requestSpecification() throws IOException {

		if (reqSpec == null) {
			log = new PrintStream(new FileOutputStream("logging.txt"));

			reqSpec = new RequestSpecBuilder().setBaseUri(setBaseURL("baseURI"))
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).build();
			return reqSpec;
		} else {
			return reqSpec;
		}
	}

	/**
	 * It is used to get the corresponding value from the property file for specific
	 * key
	 * 
	 * @param key It is used to get the corresponding value form the property file
	 *            for specific key
	 * @return String
	 * @throws IOException
	 */
	public String setBaseURL(String key) throws IOException {
		FileInputStream fis = new FileInputStream("src/test/java/resource/global.properties");
		Properties prop = new Properties();
		prop.load(fis);

		return prop.getProperty(key);

	}

	/**
	 * It is used to parse the json response and get a particular value
	 * 
	 * @param response
	 * @param keyValue
	 * @return
	 */
	public String getValueFromResponse(Response response, String keyValue) {
		JsonPath js = new JsonPath(response.asString());
		return js.get(keyValue).toString();

	}

	/**
	 * It is used to create current date
	 * 
	 * @return a String of date
	 */
	public String getCurrentDate() {

		Calendar now = Calendar.getInstance();
		String date = now.get(Calendar.YEAR) + "-" + (now.get(Calendar.MONTH) + 1) + "-" + now.get(Calendar.DATE);
		return date;
	}

}
