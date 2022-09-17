package user.servises;

import user.dto.User;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;


public class UserApi {
    public static final String BASE_URL = "https://petstore.swagger.io/v2";
    public static final String CREATEUSER = "/user";
    public static final String GETUSER = "/user/Testusername";
    private RequestSpecification rspec;

    public UserApi() {
        rspec = given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON);
    }

    public ValidatableResponse createUser(User user) {
        return given(rspec)
                .basePath(CREATEUSER)
                .body(user)
                .log().all()
                .when()
                .post()
                .then()
                .log().all();
    }

}


