package com.example.parkingsystemgui;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class ParkingSystem {

    public static class User {
        private final String id;
        private String username;
        private String password;
        private String email;
        private Role role;

        public enum Role {
            ADMIN,
            USER
        }

        public User(String username, String password, String email, Role role) {
            this.id = UUID.randomUUID().toString();
            this.username = username;
            this.password = password;
            this.email = email;
            this.role = role;
        }

        public String getId() {
            return id;
        }

        public String getUsername() {
            return username;
        }

        public String getEmail() {
            return email;
        }

        public Role getRole() {
            return role;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setRole(Role role) {
            this.role = role;
        }

        public boolean checkPassword(String passwordToCheck) {
            return this.password.equals(passwordToCheck);
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class ParkingZone {
        private final String id;
        private String name;
        private String location;

        public ParkingZone(String name, String location) {
            this.id = UUID.randomUUID().toString();
            this.name = name;
            this.location = location;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }
    }

    public static class ParkingSpot {
        private final String id;
        private final String zoneId;
        private final int number;
        private boolean isAvailable;

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

    public static class Reservation {
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

    public static class DataPersistence {

        private static final String USERS_FILE = "users.json";
        private static final String ZONES_FILE = "parking_zones.json";
        private static final String SPOTS_FILE = "parking_spots.json";
        private static final String RESERVATIONS_FILE = "reservations.json";

        private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

        private <T> List<T> loadData(String fileName, TypeToken<List<T>> typeToken) {
            try {
                String json = new String(Files.readAllBytes(Paths.get(fileName)));
                return gson.fromJson(json, typeToken.getType());
            } catch (IOException e) {
                System.err.println("Error loading data from " + fileName + ": " + e.getMessage());
                return new ArrayList<>();
            }
        }

        private <T> void saveData(String fileName, List<T> data) {
            try (Writer writer = Files.newBufferedWriter(Paths.get(fileName))) {
                gson.toJson(data, writer);
            } catch (IOException e) {
                System.err.println("Error saving data to " + fileName + ": " + e.getMessage());
            }
        }

        public List<User> loadUsers() {
            return loadData(USERS_FILE, new TypeToken<List<User>>() {});
        }

        public void saveUsers(List<User> users) {
            saveData(USERS_FILE, users);
        }

        public List<ParkingZone> loadParkingZones() {
            return loadData(ZONES_FILE, new TypeToken<List<ParkingZone>>() {});
        }

        public void saveParkingZones(List<ParkingZone> parkingZones) {
            saveData(ZONES_FILE, parkingZones);
        }

        public List<ParkingSpot> loadParkingSpots() {
            return loadData(SPOTS_FILE, new TypeToken<List<ParkingSpot>>() {});
        }

        public void saveParkingSpots(List<ParkingSpot> parkingSpots) {
            saveData(SPOTS_FILE, parkingSpots);
        }

        public List<Reservation> loadReservations() {
            return loadData(RESERVATIONS_FILE, new TypeToken<List<Reservation>>() {});
        }

        public void saveReservations(List<Reservation> reservations) {
            saveData(RESERVATIONS_FILE, reservations);
        }
    }

    public static void main(String[] args) {
        DataPersistence dataPersistence = new DataPersistence();

        List<User> users = dataPersistence.loadUsers();
        if (users.isEmpty()) {
            System.out.println("No users found. Creating a default user.");
            User admin = new User("admin", "password", "admin@example.com", User.Role.ADMIN);
            users.add(admin);
            dataPersistence.saveUsers(users);
        } else {
            System.out.println("Loaded users: " + users.size());
        }
        for (User user : users) {
            System.out.println("User: " + user.getUsername());
        }

        List<ParkingZone> zones = dataPersistence.loadParkingZones();
        if (zones.isEmpty()) {
            System.out.println("No parking zones found. Creating a default zone.");
            ParkingZone zone1 = new ParkingZone("Zone A", "Location 1");
            zones.add(zone1);
            dataPersistence.saveParkingZones(zones);
        }
        for (ParkingZone zone : zones) {
            System.out.println("Zone: " + zone.getName());
        }

        List<ParkingSpot> spots = dataPersistence.loadParkingSpots();
        if (spots.isEmpty()) {
            System.out.println("No parking spots found. Creating some example spots.");
            if (!zones.isEmpty()) {
                String zoneId = zones.get(0).getId();
                ParkingSpot spot1 = new ParkingSpot(zoneId, 1);
                ParkingSpot spot2 = new ParkingSpot(zoneId, 2);
                spots.add(spot1);
                spots.add(spot2);
                dataPersistence.saveParkingSpots(spots);
            } else {
                System.out.println("No parking zones to create spots for.");
            }
        }
        for (ParkingSpot spot : spots) {
            System.out.println("Spot: " + spot.getNumber() + ", Available: " + spot.isAvailable());
        }

        List<Reservation> reservations = dataPersistence.loadReservations();
        if (!users.isEmpty() && !spots.isEmpty()) {
            User user = users.get(0);
            ParkingSpot spot = spots.get(0);
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime end = now.plusHours(1);
            Reservation reservation = new Reservation(user.getId(), spot.getId(), now, end);
            reservations.add(reservation);
            dataPersistence.saveReservations(reservations);
            System.out.println("Created a reservation.");
        } else {
            System.out.println("Cannot create a reservation: No users or spots.");
        }
    }
}
