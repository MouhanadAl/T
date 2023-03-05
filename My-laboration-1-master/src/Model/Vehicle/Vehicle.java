package Model.Vehicle;

import java.awt.*;

import static java.lang.System.out;


/**
 * Generalizes different type of vehicles' attributes and methods.
 * @author Bisher Suhail
 * @author Mouhanad Alcharara
 * @author Yahya Mohamed
 */
public abstract class Vehicle {
    /**
     * Number of doors in int
     */
    private final int nrDoors; // Number of doors on the car
    /**
     * vehicle's engine power in double
     */
    private final double enginePower; // Engine power of the car
    /**
     * vehicle's speed in double
     */
    private double currentSpeed; // The current speed of the car
    /**
     * vehicles color as an enum Color
     */
    private Color color; // Color of the car
    /**
     * vehicle's model name as a String
     */
    private final String modelName; // The car model name

    /**
     * length of vehicle
     */
    private final double length;

    private boolean isTrasported;

    private IMovable movable;




    private Vehicle(int nrDoors, double enginePower, double length, Color color, String modelName, IMovable movable) {
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.length = length;
        this.color = color;
        this.modelName = modelName;
        this.movable = movable;
    }

    public Vehicle(int nrDoors, double enginePower, double length, Color color, String modelName, double x, double y) {
        this(nrDoors,enginePower,length,color,modelName,new Movable(x,y, IMovable.Direction.SOUTH));
        stopEngine();
        isTrasported = false;
    }


    /**
     * Returns the engine power of this vehicle.
     * @return the engine power of this vehicle.
     */
    public double getEnginePower() { return enginePower; }
    /**
     * Returns the length of this vehicle
     * @return the length of this vehicle
     */
    public double getLength() { return length; }
    /**
     * Returns the current speed of this vehicle.
     * @return the current speed of this vehicle.
     */
    public double getCurrentSpeed() { return currentSpeed; }
    /**
     * Returns the model name of this vehicle.
     * @return the model name of this vehicle.
     */
    public String getModelName() { return modelName; }
    /**
     * Changes this vehicle's speed to the giving argument. <p>
     * Does not apply changes if the giving argument value
     * is lower than 0 or higher than this vehicle's engine power. <p>
     * Does not apply changes if this vehicle is currently being transported.
     * @param speed the speed that this vehicle's speed will be set into.
     */
    public void setCurrentSpeed(double speed) {
        if (checkIfTransported()) return;
        if (speed > enginePower || speed < 0) {
            out.print("Invalid speed value: valid range between [0, vehicle's enginePower]");
        }
        else currentSpeed = speed;
    }
    /**
     * Returns current direction of this vehicle. <p>
     * Returns current direction of this vehicle's transporter if it is currently being transported.
     * @return current direction of this vehicle.
     */
    public IMovable.Direction getCurrentDirection() {
        if (isTrasported) return movable.getCurrentDirection();
        return movable.getCurrentDirection();
    }
    /**
     * Returns the x coordinates to this vehicle. <p>
     * Returns current x of this vehicle's transporter if this vehicle is currently being transported.
     * @return the x coordinates to this vehicle in double
     */
    public double getX() {
        if (isTrasported) return movable.getX();
        return movable.getX();
    }
    /**
     * Returns the y coordinates to this vehicle. <p>
     * Returns current y of this vehicle's transporter if this vehicle is currently being transported.
     * @return the y coordinates to this vehicle in double
     */
    public double getY() {
        if (isTrasported) return movable.getY();
        return movable.getY();
    }

    /**
     * Changes this vehicle's current Direction to the giving argument. <p>
     * Does not apply changes if this vehicle has transporter.
     * @param direction the direction this vehicle Direction will change into
     */
    public void setCurrentDirection(IMovable.Direction direction) {
        if (checkIfTransported()) return;
        movable.setCurrentDirection(direction);
    }
    /**
     * Changes this vehicle's x coordinate to the giving argument. <p>
     * Does not apply changes if this vehicle is currently being transported.
     * @param x the coordinate this vehicle's x coordinate will be set to.
     */
    public void setX(double x) {
        if (checkIfTransported()) return;
        movable.setX(x);
    }
    /**
     * Changes this vehicle's y coordinate to the giving argument. <p>
     * Does not apply changes if this vehicle is currently being transported.
     * @param y the coordinate this vehicle's x coordinate will be set to.
     */
    public void setY(double y) {
        if (checkIfTransported()) return;
        movable.setY(y);
    }
    /**
     * Returns true if this vehicle is currently being transported. <p>
     * Prints a warning if it returns true.
     * @return true if this vehicle is currently being transported
     */
    public boolean checkIfTransported() {
        if (isTrasported) {
            out.println("Vehicle is currently being transported");
            return true;
        }
        return false;
    }
    public void setTransportStatus(boolean status) {
        isTrasported = status;
    }

    /**
     * Set's the speed of this vehicle to 0.1. <p>
     * Follows setCurrentSpeed() restrictions.
     */
    public void startEngine(){
        setCurrentSpeed(0.1);
    }
    /**
     * Set's the speed of this vehicle to 0.
     */
    public void stopEngine(){ currentSpeed = 0; }
    /**
     * Returns the multiplication of this vehicle's engine power and 0.01.
     * @return vehicle's speed in double.
     */
    public double speedFactor() { return enginePower * 0.01; }
    /**
     * Changes this vehicle's coordinates according to its speed and direction. <p>
     * Does not apply changes if this vehicle is currently being transported.
     */

    public void move() {
        if (checkIfTransported()) return;
        movable.move(currentSpeed);
    }
    /**
     * Changes this vehicle's coordinates in relation
     * to a different vehicle coordinates and direction. <p>
     * Sets this vehicle's direction the same as the other vehicle's direction,<p>
     * snaps this vehicle to the same coordinates of the other vehicle,<p>
     * and add further increase the distance depending
     * on this vehicle's length and the extra distance. <p>
     * @param other vehicle object
     * @param extraDistance additional coordinates modification
     */
    public void moveNearTo(Vehicle other, double extraDistance) {
        movable.moveNearTo(
                other.getCurrentDirection(),
                other.getX(), other.getY(),
                extraDistance + length);
    }
    /**
     * Turns vehicle 90 degrees counterclockwise <p>
     * Turning solely affects how this vehicle's coordinates
     * change when this vehicle moves.
     */
    public void turnLeft() {
        if (checkIfTransported()) return;
        movable.turnLeft();
    }
    /**
     * Turns car 90 degrees clockwise <p>
     * Turning solely affects how this vehicle's coordinates
     * change when this vehicle moves.
     */
    public void turnRight() {
        if (checkIfTransported()) return;
        movable.turnRight();
    }


    public void horizontalTurningPoint(double lowerBound, double upperBound) {
        if (getY() >= lowerBound) {
            setCurrentDirection(IMovable.Direction.NORTH);
        }
        if (getY() < upperBound) {
            setCurrentDirection(IMovable.Direction.SOUTH);
        }
    }
    public void verticalTurningPoint(double rightmostBound, double leftmostBound) {
        if (getX() >= rightmostBound) {
            setCurrentDirection(IMovable.Direction.WEST);
        }
        if (getX() < leftmostBound) {
            setCurrentDirection(IMovable.Direction.EAST);
        }
    }
    /**
     * Increases this vehicle's speed. <p>
     * Does not apply changes if the given argument is not within
     * the desired range specified in amountCheck. <p>
     * Does not apply changes if the resulting speed is
     * lesser than this vehicle's previous speed. <p>
     * Does not apply changes if this vehicle is currently being transported.
     * @param amount this car speed's modifier
     * @see private void amountCheck
     */




    public void gas(double amount) {
        if (checkIfTransported()) return;
        if (amountCheck(amount)) {
            double previousSpeed = currentSpeed;
            incrementSpeed(amount);
            setCurrentSpeed(Math.max(getCurrentSpeed(),previousSpeed));
        }
    }


    /**
     * Decreases this vehicle's speed. <p>
     * Does not apply changes if the given argument is not within
     * the desired range specified in amountCheck. <p>
     * Does not apply changes if the resulting speed is
     * higher than this vehicle's previous speed.
     * @param amount this car speed's modifier
     * @see private void amountCheck
     */
    public void brake(double amount) {
        if (amountCheck(amount)) {
            double previousSpeed = currentSpeed;
            decrementSpeed(amount);
            setCurrentSpeed(Math.min(getCurrentSpeed(), previousSpeed));
        }
    }
    /**
     * Increases this vehicle's speed. <p>
     * Multiplies this vehicle's speed factor, the given argument and add
     * them to this vehicle's current speed. <p>
     * The resulting speed will not be applied if it is higher than this vehicle's
     * engine power and will return said engine power instead.
     * @param amount this car speed's modifier
     */
    private void incrementSpeed(double amount) {
        currentSpeed = Math.min(currentSpeed + speedFactor() * amount, enginePower);
    }
    /**
     * Decreases this vehicle's speed. <p>
     * Multiplies this vehicle's speed factor, the given argument and subtracts
     * them to this vehicle's current speed. <p>
     * The resulting speed will not be applied if it is lower than 0
     * and will return 0 instead.
     * @param amount this car speed's modifier
     */
    private void decrementSpeed(double amount) {
        currentSpeed = Math.max(currentSpeed - speedFactor() * amount, 0);
    }

    /**
     * Returns true if argument's value is within range. <p>
     * Valid argument range is between 1 and 10. <p>
     * Prints a string indicating that the inputted amount is invalid.
     * @param amount this vehicle's speed modifier
     * @return false if argument's value is invalid
     */
    private boolean amountCheck(double amount) {
        if (amount < 1 || 10 < amount) {
            out.println("Invalid amount; Valid amount range is [1, 10]");
            return false;
        }
        return true;
    }

}
