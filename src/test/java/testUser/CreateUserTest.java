package testUser;

import api.OrderData;
import api.Specifications;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import user.dto.SpecificationsUser;
import user.dto.User;
import org.junit.jupiter.api.Test;

import user.servises.UserApi;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static user.servises.UserApi.*;

public class CreateUserTest extends UserBaseTest {

    //Проверить, что при создании Юзера в поле message записывается id нового пользователя
    //Проверить, что при создании Юзера в поле type записывается значение "unknown"

    @Test
    public void checkCreateUser() {
        User user = User.builder()
                .firstName("TestFirst")
                .lastName("TestLast")
                .username("Testusername")
                .password("Test")
                .id(10)
                .userStatus(1)
                .email("test@mail.ru")
                .phone("555555")
                .build();
        UserApi userApi = new UserApi();
        userApi.createUser(user)
                .statusCode(200)
                .body("type", equalTo("unknown"));
    }


  @Test
    public void checkUser() {
      SpecificationsUser.installSpecification(Specifications.requestSpecification(BASE_URL),
              SpecificationsUser.responseSpecification200());
      List<User> userList = given()
              .when()
              .contentType(ContentType.JSON)
              .get(BASE_URL + GETUSER)
              .then().log().all()
              .extract().body().jsonPath().getList("User");
  }

  @Test
    public void checkEmail() {
      SpecificationsUser.installSpecification(SpecificationsUser.requestSpecification(BASE_URL), SpecificationsUser.responseSpecification200());
      String actualEmail = "test@mail.ru";
      String expectedEmail =
          given()
                  .when()
                  .contentType(ContentType.JSON)
                  .get(BASE_URL + GETUSER)
                  .then().log().all()
        .extract().body().as(User.class).getEmail();
        Assertions.assertEquals(expectedEmail, actualEmail,String.format( "expected %s", expectedEmail));
      }
}
