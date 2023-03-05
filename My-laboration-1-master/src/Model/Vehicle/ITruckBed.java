package Model.Vehicle;

/**
 * Abstraction of different types of truck extensions' behavior: wagons and ramps
 */
public interface ITruckBed {
    /**
     * Truck's Ramp states
     */
    enum Ramp {Up, Down}

    double getMaxTailDegree();
    double getTailDegree();
    void setTailDegree(double degree);

    Ramp getTailState();
    void setTailState(Ramp ramp);

    void raise();
    void lower();
}
