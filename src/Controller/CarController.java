package Controller;// 500 700
/*
* This class represents the Controller part in the MVC pattern.
* Its responsibilities are to listen to the View and responds in an appropriate manner by
* modifying the model state and the updating the view.
 */

import Model.World.VehicleSet;

public class CarController {

    private final VehicleSet vehicleSet;
    public CarController(VehicleSet vehicleSet) {
        this.vehicleSet = vehicleSet;
    }
    public void gas(int amount) {
        vehicleSet.gas(amount);
    }

    public void brake(int amount) {
        vehicleSet.brake(amount);
    }
    public void startVehicles() {
        vehicleSet.startVehicles();
    }
    public void stopVehicles() {
        vehicleSet.stopVehicles();
    }
    public void turboOn() {
        vehicleSet.turboOn();
    }
    public void turboOff() {
        vehicleSet.turboOff();
    }
    public void wagonRaise() {
        vehicleSet.wagonRaise();
    }
    public void wagonLower() {
        vehicleSet.wagonLower();
    }

    public void addRandomVehicle() {
        vehicleSet.addRandomVehicle();
    }
    public void removeLastAddedVehicle() {
        vehicleSet.removeLastAddedVehicle();
    }
}

