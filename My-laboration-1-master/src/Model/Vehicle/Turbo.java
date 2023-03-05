package Model.Vehicle;

public class Turbo implements ITurbo {
    private boolean turboState;

    public Turbo() {
        turboState = false;
    }

    /**
     * Sets this Saab95 turbo value to true
     */
    @Override
    public void setTurboOn() {
        turboState = true;
    }

    /**
     * Sets this Saab95 turbo value to false
     */
    @Override
    public void setTurboOff() {
        turboState = false;
    }
    @Override
    public boolean checkTurbo() {return turboState;}
//    @Override
//    public ITurbo getTurbo() {return this;}

}
