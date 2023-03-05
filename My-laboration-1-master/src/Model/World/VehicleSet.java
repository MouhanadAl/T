package Model.World;

import Model.Vehicle.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static Model.World.VehicleFactory.*;
import static java.lang.System.out;

/**
 * Handles the list of Vehicles and their components in the world model.
 */
public class VehicleSet {

    private final ArrayList<Vehicle> vehicles = new ArrayList<>();
    public void addVehicle(Vehicle v) {
        vehicles.add(v);
    }

    private final ArrayList<ITurbo> turbos = new ArrayList<>();
    public void addTurbo(Vehicle turboEmbeddedVehicle) {
        turbos.add((ITurbo) turboEmbeddedVehicle);
    }


    private final ArrayList<IWagon> wagons = new ArrayList<>();
    public void addWagon(Vehicle wagonEmbeddedVehicle) {
        wagons.add((IWagon) wagonEmbeddedVehicle);
    }




    public void move() {
        for (Vehicle vehicle : vehicles) {
            vehicle.move();
        }
    }

    public void gas(int amount) {
        for (Vehicle vehicle : vehicles)
            vehicle.gas(amount);
    }

    public void brake(int amount) {
        for (Vehicle vehicle : vehicles)
            vehicle.brake(amount);
    }
    public void startVehicles() {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getCurrentSpeed() > 0) continue;
            vehicle.startEngine();
        }
    }
    public void stopVehicles() {
        for (Vehicle vehicle : vehicles)
            vehicle.stopEngine();
    }
    public void turboOn() {
        for (ITurbo turbo : turbos) {
            turbo.setTurboOn();
        }
    }
    public void turboOff() {
        for (ITurbo turbos : turbos) {
            turbos.setTurboOff();
        }
    }
    public void wagonRaise() {
        for (IWagon truckBed : wagons) {
            truckBed.raise();
        }
    }
    public void wagonLower() {
        for (IWagon truckBed : wagons) {
            truckBed.lower();
        }
    }

    public void addRandomVehicle() {
        if (isListLimit()) return;
        Vehicle vehicle = createRandomVehicle();
        addVehicle(vehicle);
        notifyAboutAdding(vehicle);
    }

    private boolean isListLimit() {
        return vehicles.size() >= 10;
    }

    public void removeLastAddedVehicle() {
        if (vehicles.isEmpty()) return;
        Vehicle v = vehicles.remove(vehicles.size()-1);

        if (v.getModelName().equals("Saab95")) turbos.remove(turbos.size()-1);
        else if (v.getModelName().equals("Scania")) wagons.remove(wagons.size()-1);

        notifyAboutRemoving();
    }

    private Vehicle createRandomVehicle() {
        Random rand = new Random();
        int randInt = rand.nextInt(1,100);
        double randDouble = rand.nextDouble(0,500);

        return chooseRandomly(randInt, randDouble);
    }

    private Vehicle chooseRandomly(int randInt, double randDouble) {
        Vehicle vehicle;
        if (randInt <= 33) {
            vehicle = createSaab95(randDouble + randInt, randDouble);
            addTurbo(vehicle);
            return vehicle;
        }
        if (randInt <= 66) {
            vehicle = createVolvo240(randDouble, randDouble + randInt);
            return vehicle;
        }
        vehicle = createScania(randDouble, randDouble + randInt);
        addWagon(vehicle);
        return vehicle;
    }


    void horizontalTurningPoint(int lowerBound, int upperBound) {
        for (Vehicle vehicle : vehicles) {
            vehicle.horizontalTurningPoint(lowerBound, upperBound);
        }
    }
    ArrayList<Integer> getVehiclesXs() {
        ArrayList<Integer> Xs = new ArrayList<>();
        for (Vehicle v : vehicles) {
            Xs.add((int) v.getX());
        }
        return Xs;
    }
    ArrayList<Integer> getVehiclesYs() {
        ArrayList<Integer> Ys = new ArrayList<>();
        for (Vehicle v : vehicles) {
            Ys.add((int) v.getY());
        }
        return Ys;
    }
    ArrayList<String> getVehiclesNames() {
        ArrayList<String> names = new ArrayList<>();
        for (Vehicle v : vehicles) {
            names.add(v.getModelName());
        }
        return names;
    }

//    ------------------------------------------------ Observer handling ---------------------------------------------
    private final List<WorldUpdateListener> listeners = new ArrayList<>();
    public void addListener(WorldUpdateListener l){
        listeners.add(l);
    }
    void notifyAboutAdding(Vehicle v){
        for (WorldUpdateListener l : listeners)
            l.actOnAdding((int) v.getX(), (int) v.getY(), v.getModelName());
    }

    void notifyAboutRemoving() {
        for (WorldUpdateListener l : listeners) l.actOnRemoving();
    }
}
