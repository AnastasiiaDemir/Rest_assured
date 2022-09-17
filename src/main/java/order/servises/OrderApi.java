package order.servises;
import order.dto.Order;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;


public class OrderApi {
    public static final String BASE_URL = "https://petstore.swagger.io/v2";
    public static final String ORDER = "/store/order";
    private RequestSpecification rspec;

    public OrderApi() {
        rspec = given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON);
    }

    public ValidatableResponse createOrder(Order order) {
        return given(rspec)
                .basePath(ORDER)
                .body(order)
                .log().all()
        .when()
                .post()
        .then()
                .log().all();
    }

}
