package Model.WorkShop;

import java.util.ArrayList;
import java.util.List;
import static java.lang.System.out;



/**
 * Workshop model where all vehicles can get in and out of. <p>
 * Workshops may specialize in specific kind of vehicle models.
 * @param <T> everything that is defined as a vehicle
 */
public class WorkShop <T extends Model.Vehicle.Vehicle>{
    /**
     * Maximum amount of vehicles this workshop store
     */
    private int maxCapacity;
    /**
     * Current amount of vehicles in this workshop
     */
    private int currentCapacity;
    /**
     * List of vehicles in this workshop
     */
    private final List<T> vehicleList;

    /**
     * <code>Model.WorkShop</code> class constructor
     * @param maxCapacity maximum amount of vehicles for this workshop
     */
    public WorkShop(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        vehicleList = new ArrayList<>();
        currentCapacity = 0;
    }
    /**
     * Returns this workshop's maximum vehicle capacity.
     * @return workshop's maximum vehicle capacity
     */
    public int getMaxCapacity() { return maxCapacity; }
    /**
     * Returns current amount of vehicles in this workshop.
     * @return workshop's current amount of vehicles
     */
    public int getCurrentCapacity() { return currentCapacity; }

    /**
     * Returns a list of current vehicles in this workshop.
     * @return list of current vehicles in this workshop
     */
    public List<T> getVehicleList() { return vehicleList; }

    /**
     * Changes maximum amount of vehicles this workshop can take in.
     * @param maxCapacity maximum amount of vehicles this workshop will take in
     */
    public void setMaxCapacity(int maxCapacity) { this.maxCapacity = maxCapacity; }


    public void addVehicle (T vehicle) {
    if (currentCapacity >= maxCapacity) {
        out.println("Couldn't let in: max capacity exceeded");
        return;
    }
        currentCapacity++;
        vehicleList.add(vehicle);
    }


    public T exitVehicle(T vehicle) {
        if (vehicleList.isEmpty()) {
            out.println("Model.WorkShop has no vehicles to be removed.");
        }
        else if (!vehicleList.contains(vehicle)) {
            out.println("No such vehicle found");
        }
        else {
            currentCapacity--;
            vehicleList.remove(vehicle);
        }
        return vehicle;
    }
}

