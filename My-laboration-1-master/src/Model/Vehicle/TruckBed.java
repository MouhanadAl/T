package Model.Vehicle;

/**
 * Class helper to <code>ITruckTail</code> interface
 */
public class TruckBed implements ITruckBed {

    /**
     * Extensions state described as angle degrees
     */
    private double tailDegree;
    /**
     * Maximum angle degree this truck's extension can reach
     */
    private double maxTailDegree;
    /**
     * Ramp's current state
     */
    private Ramp ramp;
    /**
     * identifier marking an extension as a ramp
     */
    private final boolean isRamp; // TODO secret attribute

    /**
     * <code>TruckTail</code> class constructor <p>
     * Instantiates TruckTail as a ramp.
     */
    public TruckBed() {
        ramp = Ramp.Up;
        isRamp = true;
    }

    /**
     * <code>TruckTail</code> class constructor <p>
     * Instantiates TruckTail as a non-ramp.
     */
    public TruckBed(double maxTailDegree) {
        this.maxTailDegree = maxTailDegree;
        tailDegree = 0;
        isRamp = false;
    }




    /**
     * Returns this truck extension's current degree angle.
     * @return truck's degree angle in <code>double</code>
     */
    @Override
    public double getTailDegree() { return tailDegree; }

    /**
     * Returns this truck extension's current ramp state.
     * @return truck's ramp state
     */
    @Override
    public Ramp getTailState() { return ramp; }

    /**
     * Returns this truck extension's highest degree angle allowed.
     * @return truck's highest degree angle allowed in <code>double</code>
     */
    @Override
    public double getMaxTailDegree() {return maxTailDegree;}

    /**
     * Changes current degree angle of this truck's extension.
     * @param degree degree angle this truck's extension will be set as
     */
    @Override
    public void setTailDegree(double degree) { this.tailDegree = degree;}
    /**
     * Changes current ramp state of this truck's extension.
     * @param ramp ramp state this truck's extension will be set as
     */
    @Override
    public void setTailState(Ramp ramp) {this.ramp = ramp;}

    /**
     * Raises Truck's extension according to this extension's type <p>
     * If extension is a ramp: sets its state as <code>UP</code>.
     * If it is a non-ramp, sets its degree angle to the maximum
     * allowed degree angle.
     */
    @Override
    public void raise() {
        if (isRamp) setTailState(Ramp.Up);
        else setTailDegree(maxTailDegree); }

    /**
     * Lowers Truck's extension according to this extension's type <p>
     * If extension is a ramp: sets its state as <code>Down</code>.
     * If it is a non-ramp, sets its degree angle to 0.
     */
    @Override
    public void lower() {
        if (isRamp) setTailState(Ramp.Down);
        else setTailDegree(0); }
}
