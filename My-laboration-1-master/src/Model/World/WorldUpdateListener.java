package Model.World;

import Model.Vehicle.Vehicle;

import java.util.ArrayList;

public interface WorldUpdateListener {

    void actOnUpdate(ArrayList<Integer> xs,ArrayList<Integer> ys,ArrayList<String> strs);
    void actOnAdding(int x, int y, String name);
    void actOnRemoving();
}
