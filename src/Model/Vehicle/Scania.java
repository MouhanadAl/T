package Model.Vehicle;

import java.awt.*;
import static java.lang.System.out;

/**
 * Truck model Scania
 */
public class Scania extends Truck implements IWagon{
    // TODO how to comment this?
    /**
     * Interface's <code>ITruckTail</code> class helper's object
     */
    private ITruckBed wagon;
    private final int maxWagonDegree = 70;

    /**
     * Scania's class constructor <p>
     * <code>wagon</code>: sets Scania's truck extension as a non-ramp
     * with 70 degrees as the maximum allowed degree angle.
     */
    public Scania() {
        super(2,75,5,Color.black,"Scania",0,0);
        wagon = new TruckBed(maxWagonDegree);
    }

    public Scania(double x, double y) {
        super(2,75,5,Color.black,"Scania", x ,y);
        wagon = new TruckBed(70);
    }
    public Scania(double x, double y, double maxWagonDegree) {
        super(2,75,5,Color.black,"Scania", x ,y);
        wagon = new TruckBed(maxWagonDegree);
    }

    public double getWagonDegree() {
        return wagon.getTailDegree();
    }

    /**
     * Changes Scania's wagon degree to the giving argument if allowed. <p>
     * Allowed if this scania's current speed is 0 and giving augment is not
     * higher than this scania's maximum wagon's degree angle.
     * @param degree degree angle this Scania's wagon will be set as
     */
    public void setWagonDegree(double degree) {
        if (0 != getCurrentSpeed()) {
            out.println("Invalid truck state; Truck should be fully stopped");
        }
        else if (degree < 0 || wagon.getMaxTailDegree() < degree) {
            out.println("Invalid wagon degree; Must be between 0 and " + wagon.getMaxTailDegree());
        }
        else wagon.setTailDegree(degree);
    }

    /**
     * Returns true if this truck's wagon degree angle is 0. <p>
     * Returns false and prints a warning otherwise.
     * @return true when Scania is ready to change its speed
     */
    @Override
    public boolean readyToGo() {
        if (wagon.getTailDegree() != 0) {
            out.println("Truck's speed can't increase while wagon isn't fully lowered");
            return false;
        }
        return true;
    }
    // TODO how to talk about readyToGo() here?
    /**
     * Does not apply changes if this Scania is not ready to go
     * @param amount this car speed's modifier
     */
    @Override
    public void gas(double amount) {
        if (!readyToGo()) return;
        super.gas(amount);
    }

    /**
     * Sets this Scania's degree angle to 0
     */
    public void fullyLowerWagon() { // TODO check me on monday
        wagon.lower();
    }
    /**
     * Sets this Scania's degree angle to its maximum allowed degree angle if allowed. <p>
     * Allowed if this Scania's current speed is 0. Else prints a warning.
     */
    public void fullyRaiseWagon() {
        if (getCurrentSpeed() != 0) {
            out.println("Truck is currently in motion");
            return;
        }
        wagon.raise();
    }

    @Override
    public void raise() {
        fullyRaiseWagon();
    }

    @Override
    public void lower() {
        fullyLowerWagon();
    }
}
