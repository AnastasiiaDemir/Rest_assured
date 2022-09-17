package testOrder;

import order.dto.Order;
import order.dto.OrderOut;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

public class CreateOrderTest extends OrderBaseTest {

    //Проверка статус кода и содержимого заказа
    @Test
    public void checkOrder() {

        Order order = Order.builder()
                .id(9225555)
                .petId(10)
                .quantity(4)
                .shipDate("2022-07-21T14:29:13.543Z")
                .status("placed")
                .complete(true)
                .build();

        orderApi.createOrder(order)
                .statusCode(200)
                .body("id", equalTo(9225555))
                .body("petId", equalTo(10))
                .body("quantity", equalTo(4))
                .body("shipDate", equalTo("2022-07-21T14:29:13.543+0000"))
                .body("status", equalTo("placed"))
                .body("complete", equalTo(true));

    }

    //Проверка даты заказа
    @Test
    void checkOrderDate() {
        Order order = Order.builder()
                .id(9225555)
                .petId(0)
                .quantity(0)
                .shipDate("2022-07-21T14:29:13.543Z")
                .status("placed")
                .complete(true)
                .build();

        ValidatableResponse response = orderApi.createOrder(order);
        OrderOut orderOut = response.extract().body().as(OrderOut.class);
        Assertions.assertEquals("2022-07-21T14:29:13.543+0000", orderOut.getShipDate());
    }

}