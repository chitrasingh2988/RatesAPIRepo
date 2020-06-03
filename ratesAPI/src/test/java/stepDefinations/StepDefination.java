package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.runner.RunWith;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resource.APIResources;
import resource.Utility;

@RunWith(Cucumber.class)
public class StepDefination extends Utility {

	RequestSpecification reqSpec;
	Response responseAPI;

	@Given("GetRatesAPI is called for latest exchange rates")
	public void getratesapi_is_called_for_latest_exchange_rates() throws IOException {

		reqSpec = given().relaxedHTTPSValidation().spec(requestSpecification());

	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String httpMethod) {
		APIResources endpoint = APIResources.valueOf(resource);

		if (httpMethod.equalsIgnoreCase("GET")) {

			responseAPI = reqSpec.when().get(endpoint.getResource()).then().extract().response();
		}
	}

	@Then("the API call got success with status code 200$")
	public void the_API_call_got_success_with_status_code_200() {

		assertEquals(responseAPI.getStatusCode(), 200);
	}

	@Then("verify the response have {string} in {string} key field")
	public void verify_the_response_have_in_key_field(String expectedVlaue, String key) {

		assertEquals(getValueFromResponse(responseAPI, key), expectedVlaue);
	}

	@When("user calls exchange rate for {string} with {string} http request")
	public void user_calls_exchange_rate_for_with_http_request(String date, String httpMethod) {

		if (httpMethod.equalsIgnoreCase("GET")) {

			responseAPI = reqSpec.when().get(date).then().extract().response();
		}

	}

	@Then("verify the response matches for current date exchange rates with {string} exchange rates")
	public void verify_the_response_matches_for_current_date_exchange_rates_with_exchange_rates(String futureDate) {

		Response futureDateResponse = responseAPI;

		user_calls_exchange_rate_for_with_http_request(getCurrentDate(), "GET");
		Response currentDateResponse = responseAPI;

		assertEquals(futureDateResponse.asString(), currentDateResponse.asString());

	}

}
