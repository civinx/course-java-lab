package model;

import java.sql.Timestamp;
import java.util.Objects;

public class Order {
    private int orderId;
    private int userId;
    private int labId;
    private int computerId;
    private Timestamp orderStartTime;
    private Timestamp orderEndTime;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId == order.orderId &&
                userId == order.userId &&
                labId == order.labId &&
                computerId == order.computerId &&
                Objects.equals(orderStartTime, order.orderStartTime) &&
                Objects.equals(orderEndTime, order.orderEndTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(orderId, userId, labId, computerId, orderStartTime, orderEndTime);
    }
}
