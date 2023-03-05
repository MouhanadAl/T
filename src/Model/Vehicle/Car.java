package Model.Vehicle;

import java.awt.*;
import static java.lang.System.out;


/**
 * Generalizes different type of cars' attributes and methods.
 */
public abstract class Car extends Vehicle {
    /**
     * indicator to whether the car is ready to be loaded or not.
     */
    private boolean readyToBeLoaded;

    /**
     * Car's class constructor <p>
     * Car is set to not be ready to be loaded on default upon instantiation.
     * @param nrDoors number of doors on car
     * @param enginePower of this car
     * @param length of this car in meters
     * @param color of this car
     * @param modelName of this car
     */
    public Car(int nrDoors, double enginePower, double length, Color color, String modelName, double x, double y) {
        super(nrDoors, enginePower, length, color, modelName, x, y);
        readyToBeLoaded = false;
    }

    /**
     * Returns true if car is ready to be loaded.
     * @return true if car is ready to be loaded
     */
    public boolean isReadyToBeLoaded() {return readyToBeLoaded;}

    /**
     * Changes the value of this car's boolean readyToBeLoaded
     * @param b either true or false
     */
    public void setReadyToBeLoaded(boolean b) {this.readyToBeLoaded = b;}


    public void prepareToBeLoaded() {
        if (checkIfTransported()) return;
        stopEngine();
        readyToBeLoaded = true;
    }

    /**
     * Returns the multiplication of this vehicle's engine power,
     * this car's special modifier and 0.01.
     * @return vehicle's speed in double
     */
    @Override
    public double speedFactor() {return super.speedFactor() * carSpecialModifier(); }

    /**
     * Return this car's unique speed modifier. <P>
     * @return this car's unique modifier in double
     */
    public abstract double carSpecialModifier();

}
