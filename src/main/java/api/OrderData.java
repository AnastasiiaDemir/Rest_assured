package api;

import java.util.Date;

public class OrderData {
    private Integer id;
    private Integer petId;
    private Integer quantity;
    private Date shipDate;
    private String status;
    private Boolean complete;

    public OrderData(Integer id, Integer petId, Integer quantity, Date shipDate, String status, Boolean complete) {
        this.id = id;
        this.petId = petId;
        this.quantity = quantity;
        this.shipDate = shipDate;
        this.status = status;
        this.complete = complete;
    }

    public Integer getId() {
        return id;
    }

    public Integer getPetId() {
        return petId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Date getShipDate() {
        return shipDate;
    }

    public String getStatus() {
        return status;
    }

    public Boolean getComplete() {
        return complete;
    }
}
