package com.example.parkingsystemgui;

import java.time.LocalDateTime;
import java.util.UUID;

public class Reservation {
    private final String id;
    private final String userId;
    private final String spotId;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    public Reservation(String userId, String spotId, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.spotId = spotId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getSpotId() {
        return spotId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

}