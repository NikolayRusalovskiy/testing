package testapi;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.ResponseBodyExtractionOptions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.Test;
import testapi.model.User;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class Repository {
    @Test
    public void getProjects() {
        String base = "https://api.github.com/users/NikolayRusalovskiy";
        RestTemplate restTemplate = new RestTemplate();
        long start = System.currentTimeMillis();
        ResponseEntity<String> response = restTemplate.getForEntity(base + "", String.class);

        String body = response.getBody();
        long end = System.currentTimeMillis();
        long result = (end - start) / 1000;
        System.out.println("Result " + result + " " + "body ->" + body);
        System.out.println(response);

        long start1 = System.currentTimeMillis();

        User user = restTemplate.getForObject(base, User.class);

        long end1 = System.currentTimeMillis();
        long result1 = (end1 - start1) / 1000;
        System.out.println("Result " + result1 + " " + user.getLogin());
    }

    @Test
    public void getProjectsrestassured() {
        RestAssured.baseURI = "https://api.github.com/users/NikolayRusalovskiy";
        ResponseBodyExtractionOptions response =
                given().
                        when().
                        get().
                        then().
                        assertThat().statusCode(200).
                        and().extract().body();
        String login = response.jsonPath().get("login");
        assertThat(login, equalTo("NikolayRusalovskiy"));
    }
}
