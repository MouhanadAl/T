package Model.Vehicle;

import java.awt.*;

/**
 * Saab95 car model
 */
public class Saab95 extends Car implements ITurbo {
    private ITurbo turbo;
    /**
     * Saab95 class constructor
     * turbo is set to false on instantiation
     */
    public Saab95(){
        super(2,125,1.5,Color.red,"Saab95",0,0);
        turbo = new Turbo();
    }
    public Saab95(double x, double y){
        super(2,125,1.5,Color.red,"Saab95",x,y);
        turbo = new Turbo();
    }

    // bad?
//    public ITurbo getTurbo() {
//        return this;
//    }


    /**
     * Sets this Saab95 turbo value to true
     */
    @Override
    public void setTurboOn(){
        turbo.setTurboOn();
    }
    /**
     * Sets this Saab95 turbo value to false
     */
    @Override
    public void setTurboOff(){
        turbo.setTurboOff();
    }

    @Override
    public boolean checkTurbo() {
        return turbo.checkTurbo();
    }


    /**
     * Returns this Saab95's unique modifier depending on turbo state
     * @return either 1.3 or 1 in double
     */
    @Override
    public double carSpecialModifier(){
        if (checkTurbo()) return 1.3;
        return 1;
    }

}
