package Model.World;

import Model.Vehicle.ITurbo;
import Model.Vehicle.IWagon;
import Model.Vehicle.Vehicle;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;


public class WorldModel implements WorldUpdateListener {


    private final VehicleSet vehicleSet;
    private final ArrayList<WorldUpdateListener> listeners = new ArrayList<>();



    public WorldModel() {
        vehicleSet = new VehicleSet();
        vehicleSet.addListener(this);


        // The timer is started with a listener (see below) that executes the statements
        // each step between delays.
        // The delay (ms) corresponds to 20 updates a sec (hz)
        int delay = 50;
        Timer timer = new Timer(delay, new TimerListener());
        timer.start();
    }

    public void addVehicle(Vehicle v) {
        vehicleSet.addVehicle(v);
        actOnAdding((int) v.getX(), (int) v.getY(), v.getModelName());
    }
    public VehicleSet getVehicleMethods() {
        return vehicleSet;
    }

    public void addTurbo(Vehicle turboEmbeddedVehicle) {
        vehicleSet.addTurbo(turboEmbeddedVehicle);
    }

    public void addWagon(Vehicle wagonEmbeddedVehicle) {
        vehicleSet.addWagon(wagonEmbeddedVehicle);
    }


    public void addListeners(WorldUpdateListener l) {
        listeners.add(l);
    }

    @Override
    public void actOnUpdate(ArrayList<Integer> xs, ArrayList<Integer> ys, ArrayList<String> strs) {
        for (WorldUpdateListener l : listeners) {
            l.actOnUpdate(xs,ys,strs);
        }
    }

    @Override
    public void actOnAdding(int x, int y, String name) {
        for (WorldUpdateListener l : listeners) {
            l.actOnAdding(x,y,name);
        }
    }

    @Override
    public void actOnRemoving() {
        for (WorldUpdateListener l : listeners) {
            l.actOnRemoving();
        }
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
     * view to update its images. Change this method to your needs.
     * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            horizontalTurningPoint(500, 0);
            move();
            actOnUpdate(getVehiclesXs(),getVehiclesYs(),getVehiclesNames());
        }
    }
//    ------------------------------------ delegated methods for innerClass -------------------------------------
    private ArrayList<Integer> getVehiclesXs() {
        return vehicleSet.getVehiclesXs();
    }

    private ArrayList<Integer> getVehiclesYs() {
        return vehicleSet.getVehiclesYs();
    }
    private ArrayList<String> getVehiclesNames() {
        return vehicleSet.getVehiclesNames();
    }
    private void horizontalTurningPoint(int lowerBound, int upperBound) {
        vehicleSet.horizontalTurningPoint(lowerBound,upperBound);
    }
    private void move() { vehicleSet.move(); }

}
