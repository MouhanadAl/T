import Controller.CarController;
import Model.Vehicle.Vehicle;
import Model.World.WorldModel;
import Model.World.VehicleFactory;
import View.WorldView;

public class Application {


    public static void main(String[] args) {
        WorldModel world = new WorldModel();
        CarController cc = new CarController(world.getVehicleMethods());
        WorldView worldView = new WorldView("CarSim 1.0", cc);
        world.addListeners(worldView);
        initWorld(world);
    }

    private static void initWorld (WorldModel worldModel) {
        Vehicle v = VehicleFactory.createSaab95(0,100);
        worldModel.addVehicle(v);
        worldModel.addTurbo(v);
        worldModel.addVehicle(VehicleFactory.createVolvo240());
        v = VehicleFactory.createScania(0,200,70);
        worldModel.addVehicle(v);
        worldModel.addWagon(v);

    }

}
