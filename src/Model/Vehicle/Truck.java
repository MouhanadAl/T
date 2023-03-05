package Model.Vehicle;

import java.awt.*;

/**
 * Generalizes different type of trucks' attributes and methods.
 */
public abstract class Truck extends Vehicle {





    /**
     * Truck's class constructor <p>
     * @param nrDoors number of doors on truck
     * @param enginePower of this truck
     * @param length of this truck in meters
     * @param color of this truck
     * @param modelName of this truck
     */
    public Truck(int nrDoors, double enginePower, double length, Color color, String modelName, double x, double y) {
        super(nrDoors, enginePower, length, color, modelName, x, y);
    }




    //TODO how to comment "then calls the overriding method"
    /**
     * Changes this truck's current speed <p>
     * Does not apply changes if this truck does not pass certain conditions.
     * @param speed the speed that this vehicle's speed will be set into.
     * @see "<code>public boolean readToGo()</code>"
     */
    @Override
    public void setCurrentSpeed(double speed) {
        if (!readyToGo());
        else { super.setCurrentSpeed(speed); }
    }

    /**
     * Returns true if this truck passes certain conditions unique to this truck's model.
     * @return true when truck is ready to change its speed
     */
    public abstract boolean readyToGo();


}
