package testapi;

import com.jayway.restassured.response.ResponseBodyExtractionOptions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class Weather {
    private String baseurl = "https://samples.openweathermap.org/data/2.5/forecast?q=München,DE&appid=b6907d289e10d714a6e88b30761fae22";

    @Test
    public void callrest() {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8080/spring-rest/foos";
        ResponseEntity<String> response = restTemplate.getForEntity(baseurl, String.class);
        String body = response.getBody();
        System.out.println("body ->" + body);
        assertThat("Check code 200", response.getStatusCode().is2xxSuccessful());
        System.out.println(response);


    }

    @Test
    public void callRestByAssured() {
        ResponseBodyExtractionOptions response =
                given().
                        when().
                        get(baseurl).
                        then().
                        assertThat().statusCode(200).
                        and().extract().body();
        Integer id = response.jsonPath().get("city.id");
        assertThat(id, equalTo(6940463));
    }

}
