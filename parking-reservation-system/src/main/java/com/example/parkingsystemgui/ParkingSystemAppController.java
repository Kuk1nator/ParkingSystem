package com.example.parkingsystemgui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class ParkingSystemAppController {

    @FXML
    private TextField carNameTextField;

    @FXML
    private TextField registrationNumberTextField;

    @FXML
    private Label messageLabel;

    private ParkingSpot[] parkingSpots;

    @FXML
    public void initialize() {
        parkingSpots = new ParkingSpot[6];
        for (int i = 0; i < 6; i++) {
            parkingSpots[i] = new ParkingSpot(); 
            parkingSpots[i].setAvailable(true);
        }
    }

    @FXML
    protected void handleBookClick(ActionEvent event) {
        String carName = carNameTextField.getText();
        String registrationNumber = registrationNumberTextField.getText();

        if (carName == null || carName.isEmpty()) {
            messageLabel.setText("Введите название автомобиля!");
            messageLabel.setTextFill(Color.RED);
            return;
        }

        if (registrationNumber == null || registrationNumber.isEmpty()) {
            messageLabel.setText("Введите номер автомобиля!");
            messageLabel.setTextFill(Color.RED);
            return;
        }

        for (int i = 0; i < parkingSpots.length; i++) {
            if (parkingSpots[i].isAvailable()) {
                parkingSpots[i].setAvailable(false);
                messageLabel.setText("Место " + (i + 1) + " забронировано для " + carName + " (" + registrationNumber + ")");
                messageLabel.setTextFill(Color.GREEN);
                return;
            }
        }

        messageLabel.setText("Нет свободных мест!");
        messageLabel.setTextFill(Color.RED);
    }
}