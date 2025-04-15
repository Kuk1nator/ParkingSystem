package com.example.parkingsystemgui;

import java.util.UUID;

public class ParkingSpot {
    private final String id;
    private String zoneId = null;
    private int number = 0;
    private boolean isAvailable;

    public ParkingSpot() {
        this.id = UUID.randomUUID().toString();
        this.zoneId = null;
        this.number = 0;
        this.isAvailable = true;
    }

    public ParkingSpot(String zoneId, int number) {
        this.id = UUID.randomUUID().toString();
        this.zoneId = zoneId;
        this.number = number;
        this.isAvailable = true;
    }


    public String getId() {
        return id;
    }

    public String getZoneId() {
        return zoneId;
    }

    public int getNumber() {
        return number;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}