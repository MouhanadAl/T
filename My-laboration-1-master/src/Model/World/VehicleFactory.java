package Model.World;

import Model.Vehicle.*;

public class VehicleFactory {

    public static Vehicle createSaab95() {
        return new Saab95();
    }
    public static Vehicle createSaab95(double x, double y) {
        return new Saab95(x,y);
    }


    public static Vehicle createVolvo240() {
        return new Volvo240();
    }
    public static Vehicle createVolvo240(double x, double y) {
        return new Volvo240(x,y);
    }
    public static Vehicle createScania() {
        return new Scania();
    }



    public static Vehicle createScania(double x, double y) {
        return new Scania(x,y);
    }
    public static Vehicle createScania(double x, double y, double maxWagonDegree) {
        return new Scania(x,y,maxWagonDegree);
    }

    public static Vehicle createCarrier() {
        return new Carrier<>(); // TODO what happens now that Car isn't in <>
    }
}
