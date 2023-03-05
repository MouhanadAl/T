package Model.Vehicle;

import java.awt.*;
import java.util.*;
import static java.lang.System.out;

/**
 * Truck model Carrier
 * @param <C> car vehicles
 */
public class Carrier <C extends Car> extends Truck {
    /**
     * Interface's <code>ITruckTail</code> class helper's object
     */
    private final ITruckBed ramp;
    /**
     * Carrier's load capacity quantified by cars' length in meters
     */
    private double loadCapacity;
    /**
     * List of the currently carried cars
     */
    private final Deque<C> loadedVehicles = new ArrayDeque<>();

    public Carrier(ITruckBed tt) {
        super(2, 60, 6, Color.gray, "Carrier",0,0);
        this.ramp = tt;
        loadCapacity = getLength();
    }
    /**
     * Carrier class constructor <p>
     * Carrier's truck extension is set as a ramp upon instantiation. <p>
     * Carrier's load capacity is set to its length upon instantiation.
     */
    public Carrier() {
        //this(new TruckTail());
        super(2, 60, 6, Color.gray, "Carrier",0,0);
        ramp = new TruckBed();
        loadCapacity = getLength();
    }
    public Carrier(double x, double y) {
        super(2, 60, 6, Color.gray, "Carrier",x,y);
        ramp = new TruckBed();
        loadCapacity = getLength();
    }

    /**
     * Returns Carrier's current ramp state
     * @return carrier's ramp state
     */
    public ITruckBed.Ramp getRampState() { return ramp.getTailState(); } // TODO is it always better to delegate?

    /**
     * Returns the currently carried cars
     * @return carried cars
     */
    public Deque<C> getLoadedCars() {return loadedVehicles;}

    /**
     * Returns current carried cars' model name
     * @return carried cars model name as an <code>ArrayList</code>
     */
    public ArrayList<String> getCarsNameModel() {
        ArrayList<String> names = new ArrayList<>();
        for (C car : loadedVehicles) {
            names.add(car.getModelName());
        }
        return names;
    }

    /**
     * Sets this Carrier's ramp state to <code>DOWN</code> if allowed. <p>
     * Allowed if this Carrier's current speed is 0. Else prints a warning.
     */
    public void lowerRamp() {
        if (0 != getCurrentSpeed()) {
            out.println("Invalid truck state: truck should be fully stopped");
        } else ramp.lower();
    }
    /**
     * Sets this Carrier's ramp state to <code>UP</code>. <p>
     */
    public void raiseRamp() { ramp.raise(); }

    /**
     * Returns true if this truck's wagon degree angle is 0. <p>
     * Returns false and prints a warning otherwise.
     * @return true when Scania is ready to change its speed
     */
    @Override
    public boolean readyToGo() {
        if (rampIsDown()) {
            out.println("Truck's speed can't increase while ramp isn't fully raised.");
            return false;
        }
        return true;
    }

    /**
     * Carries specified car if allowed. <p>
     * Allowed if Carrier's ramp is down, specified car is
     * ready to be loaded, and there is enough space in this Carrier.<p>
     * Calculates remaining capacity after loading,<p>
     * sets the specified car's coordinates the same as this Carrier,<p>
     * sets this Carrier as the transporter of the specified car,<p>
     * sets the specified car's <code>readyToBeLoaded</code> state to false,<p>
     * and adds the specified car to this carrier's loaded cars list.
     * @param car specified car that this Carrier will carry
     */
    public void loadCar(C car) {
        if (!rampIsDown()) out.println("Ramp must be lowered first");
        else if (!car.isReadyToBeLoaded()) {
            out.println("Vehicle must be ready to be loaded by being near the transporter.");
        } else if (loadCapacity - car.getLength() < 0) {
            out.println("Couldn't load: load capacity exceeded");

        } else {
            loadCapacity -= car.getLength();
            commenceLoading(car);
            loadedVehicles.push(car);
        }
    }

    private void commenceLoading(C car) {
        car.setX(getX());
        car.setY(getY());
        car.setTransportStatus(true);
        car.setReadyToBeLoaded(false);
    }

    /**
     * Removes the last loaded car from this Carrier's capacity list and puts it
     * near this Carrier if allowed.<p>
     * Allowed if this Carrier's ramp state is <code>DOWN</code>. <p>
     * Prints a warning if this Carrier is empty. <p>
     * </p>
     * Calculates remaining capacity after unloading,<p>
     * Removes transport status from car,<p>
     * sets unloaded car's coordinates to an adjacent near the Carrier with
     * the remaining loaded cars' length as extra distance, <p>
     * and removes the unloaded car from this Carrier's loaded cars list.
     */
    public void unloadCar() {
        if (!rampIsDown()) out.println("Ramp must be lowered first");
        else if (loadedVehicles.isEmpty()) out.println("Transporter is empty");

        else {
            loadCapacity += loadedVehicles.peek().getLength();
            loadedVehicles.peek().setTransportStatus(false);
            loadedVehicles.peek().moveNearTo(this, getLength() - loadCapacity);
            loadedVehicles.pop();
        }
    }

    /**
     * Returns true if this carrier's ramp state is <code>DOWN</code>
     * @return true if this carrier's ramp state is <code>DOWN</code>
     */
    private boolean rampIsDown() { return ramp.getTailState() == ITruckBed.Ramp.Down; }


                                                // TODO better way than
    //--------------------------------------- ANNOYING SIMULATION CHECKERS --------------------------------------
    @Override
    public void setX(double x) {
        super.setX(x);
            for (C car : loadedVehicles) {
                car.setTransportStatus(false);
                car.setX(getX());
                car.setTransportStatus(true);
            }
        }

    @Override
    public void setY(double y) {
        super.setY(y);
        if (!getLoadedCars().isEmpty()) {
            for (C car : loadedVehicles) {
                car.setTransportStatus(false);
                car.setY(getY());
                car.setTransportStatus(true);
            }
        }
    }
    @Override
    public void turnLeft() {
        super.turnLeft();
        if (!getLoadedCars().isEmpty()) {
            for (C car : loadedVehicles) {
                car.setTransportStatus(false);
                car.turnLeft();
                car.setTransportStatus(true);
            }
        }
    }
    @Override
    public void turnRight() {
        super.turnRight();
        if (!getLoadedCars().isEmpty()) {
            for (C car : loadedVehicles) {
                car.setTransportStatus(false);
                car.turnRight();
                car.setTransportStatus(true);
            }
        }
    }
    @Override
    public void move() {
        super.move();
        if (!getLoadedCars().isEmpty()) {
            for (C car : loadedVehicles) {
                car.setTransportStatus(false);
                car.setX(getX());
                car.setY(getY());
                car.setTransportStatus(true);
            }
        }
    }
}