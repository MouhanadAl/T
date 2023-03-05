package Model.Vehicle;

import java.awt.*;

/**
 * Volvo240 car model
 */
public class Volvo240 extends Car {

    /**
     * Model Volvo240's special modifier
     */
    private final double trimFactor;

    /**
     * Volvo240 class constructor
     * sets trimFactor value on instantiation
     */
    public Volvo240(){
        super(4,100,2,Color.black,"Volvo240",0,0);
        this.trimFactor = 1.25;
    }
    public Volvo240(double x, double y){
        super(4,100,2,Color.black,"Volvo240",x,y);
        this.trimFactor = 1.25;
    }

    /**
     * Returns this Volvo240's unique modifier depending in double
     * @return Volvo240 trimFactor
     */
    @Override
    public double carSpecialModifier() {
        return trimFactor;
    }

}
