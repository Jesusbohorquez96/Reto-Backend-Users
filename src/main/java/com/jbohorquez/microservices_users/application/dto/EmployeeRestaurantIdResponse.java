package com.jbohorquez.microservices_users.application.dto;


public class EmployeeRestaurantIdResponse {

    private Long restaurantId;

    public EmployeeRestaurantIdResponse(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public EmployeeRestaurantIdResponse() {
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Override
    public String toString() {
        return "EmployeeRestaurantIdResponse{" +
                "restaurantId=" + restaurantId +
                '}';
    }
}
