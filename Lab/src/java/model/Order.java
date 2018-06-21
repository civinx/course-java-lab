package model;

import java.sql.Timestamp;

public class Order {
    private int orderId;
    private int userId;
    private int labId;
    private int computerId;
    private Timestamp orderStartTime;
    private Timestamp orderEndTime;
    private Integer orderState;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLabId() {
        return labId;
    }

    public void setLabId(int labId) {
        this.labId = labId;
    }

    public int getComputerId() {
        return computerId;
    }

    public void setComputerId(int computerId) {
        this.computerId = computerId;
    }

    public Timestamp getOrderStartTime() {
        return orderStartTime;
    }

    public void setOrderStartTime(Timestamp orderStartTime) {
        this.orderStartTime = orderStartTime;
    }

    public Timestamp getOrderEndTime() {
        return orderEndTime;
    }

    public void setOrderEndTime(Timestamp orderEndTime) {
        this.orderEndTime = orderEndTime;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (orderId != order.orderId) return false;
        if (userId != order.userId) return false;
        if (labId != order.labId) return false;
        if (computerId != order.computerId) return false;
        if (orderStartTime != null ? !orderStartTime.equals(order.orderStartTime) : order.orderStartTime != null)
            return false;
        if (orderEndTime != null ? !orderEndTime.equals(order.orderEndTime) : order.orderEndTime != null) return false;
        if (orderState != null ? !orderState.equals(order.orderState) : order.orderState != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + userId;
        result = 31 * result + labId;
        result = 31 * result + computerId;
        result = 31 * result + (orderStartTime != null ? orderStartTime.hashCode() : 0);
        result = 31 * result + (orderEndTime != null ? orderEndTime.hashCode() : 0);
        result = 31 * result + (orderState != null ? orderState.hashCode() : 0);
        return result;
    }
}
