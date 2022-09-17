package api;


import io.restassured.http.ContentType;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class SwaggerTest {
    private final static String URL = "https://petstore.swagger.io/v2";

    private int getID() {
        //For valid response try integer IDs with value >= 1 and <= 10.
        // Other values will generated exceptions
        int a = 1;
        int b = 10;
        return a + (int) (Math.random() * ((b - a) + 1));
    }

    private int CODEERR = 239898;

    @Test
    public void checkCode200() {
        Specifications.installSpecification(Specifications.requestSpecification(URL), Specifications.responseSpecification200());
        List<OrderData> orderData = given()
                .when()
                .get(URL + "/store/order/" + getID())
                .then().log().all()
                .extract().body().jsonPath().getList("order", OrderData.class);

    }

    @Test
    public void checkCode404() {
        Specifications.installSpecification(Specifications.requestSpecification(URL), Specifications.responseSpecification404());
        List<OrderData> orderData = given()
                .when()
                .contentType(ContentType.JSON)
                .get(URL + "/store/order/" + CODEERR)
                .then().log().all()
                .extract().body().jsonPath().getList("order", OrderData.class);

    }

}
