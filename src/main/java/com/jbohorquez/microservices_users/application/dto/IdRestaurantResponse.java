package com.jbohorquez.microservices_users.application.dto;

public class IdRestaurantResponse {

    private Long restaurantId;
    private Long ownerId;

    public IdRestaurantResponse(Long restaurantId, Long ownerId) {
        this.restaurantId = restaurantId;
        this.ownerId = ownerId;
    }

    public IdRestaurantResponse(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public String toString() {
        return "IdRestaurantResponse{" +
                "restaurantId=" + restaurantId +
                ", ownerId=" + ownerId +
                '}';
    }
}
