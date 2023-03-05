package Model.Vehicle;

public class Movable implements IMovable {
    /**
     * x coordinate of vehicle
     */
    private double x;
    /**
     * y coordinate of vehicle
     */
    private double y;
    /**
     * vehicle's direction as an enum Direction
     */
    private Direction currentDir;
    /**
     * Direction enum's values as an array
     */
    private static final Direction[] dirs = Direction.values();


    public Movable(double x, double y, Direction currentDir) {
        this.x = x;
        this.y = y;
        this.currentDir = currentDir;
    }



    /**
     * Returns current direction of this vehicle. <p>
     * Returns current direction of this vehicle's transporter if it is currently being transported.
     * @return current direction of this vehicle.
     */
    @Override
    public Direction getCurrentDirection() {
        return currentDir;
    }
    /**
     * Returns the x coordinates to this vehicle. <p>
     * Returns current x of this vehicle's transporter if this vehicle is currently being transported.
     * @return the x coordinates to this vehicle in double
     */
    @Override
    public double getX() {
        return x;
    }
    /**
     * Returns the y coordinates to this vehicle. <p>
     * Returns current y of this vehicle's transporter if this vehicle is currently being transported.
     * @return the y coordinates to this vehicle in double
     */
    @Override
    public double getY() {
        return y;
    }

    /**
     * Changes this vehicle's current Direction to the giving argument. <p>
     * Does not apply changes if this vehicle has transporter.
     * @param direction the direction this vehicle Direction will change into
     */
    @Override
    public void setCurrentDirection(Direction direction) {
        this.currentDir = direction;
    }
    /**
     * Changes this vehicle's x coordinate to the giving argument. <p>
     * Does not apply changes if this vehicle is currently being transported.
     * @param x the coordinate this vehicle's x coordinate will be set to.
     */
    @Override
    public void setX(double x) {
        this.x = x;
    }
    /**
     * Changes this vehicle's y coordinate to the giving argument. <p>
     * Does not apply changes if this vehicle is currently being transported.
     * @param y the coordinate this vehicle's x coordinate will be set to.
     */
    @Override
    public void setY(double y) {
        this.y = y;
    }
    /**
     * Changes this vehicle's coordinates according to its speed and direction. <p>
     * Does not apply changes if this vehicle is currently being transported.
     */
    @Override
    public void move(double increment) {
        switch (currentDir) {
            case SOUTH -> y += increment;
            case WEST -> x -= increment;
            case NORTH -> y -= increment;
            case EAST -> x += increment;
        }
    }
    @Override
    public void moveNearTo(Direction otherDir, double otherX,
                           double otherY, double extraDistance) {
        currentDir = otherDir;
        switch (otherDir) {
            case SOUTH -> y = otherY + extraDistance;
            case WEST -> x = otherX - extraDistance;
            case NORTH -> y = otherY - extraDistance;
            case EAST -> x = otherX + extraDistance;
        }
    }
    @Override
    public void turnLeft() {
        setNextDirection((-1));
    }
    /**
     * Turns car 90 degrees clockwise <p>
     * Turning solely affects how this vehicle's coordinates
     * change when this vehicle moves.
     */
    @Override
    public void turnRight() {
        setNextDirection(1);
    }


    /**
     * Sets this vehicle's direction depending on the calling method.
     * Cycles between north, east, south and west in order.
     * @param k indicates what direction will be picked
     */

    private void setNextDirection(int k) {
        int current = currentDir.ordinal();

        if (current == 0 && k == -1) {
            currentDir = dirs[3];
        }
        else if (current == 3 && k == 1) {
            currentDir = dirs[0];
        }
        else { currentDir = dirs[current+k]; }
    }


}
