import Model.Vehicle.IMovable;
import Model.Vehicle.Scania;
import Model.Vehicle.Vehicle;
import Model.Vehicle.Volvo240;
import Model.WorkShop.WorkShop;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class WorkShopTest {

    private Scania scania;
    private Volvo240 volvo;
    private WorkShop<Vehicle> workShop;

    @Before
    public void startWorkShop() {
        workShop = new WorkShop<>(10);
        volvo = new Volvo240();
        scania = new Scania();
    }

    @After
    public void stopWorkShop() {
        workShop = null; scania = null; volvo = null;
    }
    @Test
    public void testNamingAllVehiclesInWorkShop() {
        workShop.addVehicle(scania);
        workShop.addVehicle(volvo);
        StringBuilder strB = new StringBuilder();
        for (Vehicle vehicle : workShop.getVehicleList()) {
            strB.append(vehicle.getModelName());
        }
        assert(strB.toString().equals("ScaniaVolvo240"));
    }
    //TODO [] är inte null?????
    @Test
    public void testAddingOverMaxCapacity() {
        WorkShop<Vehicle> workShop2 = new WorkShop<>(10);
        workShop2.setMaxCapacity(workShop2.getMaxCapacity() - workShop2.getMaxCapacity());
        workShop2.addVehicle(scania);
        assert(workShop2.getVehicleList().isEmpty());
    }

    @Test
    public void testTurningExitedTruck() {
        WorkShop<Scania> scaniaWorkShop = new WorkShop<>(workShop.getMaxCapacity());
        scaniaWorkShop.addVehicle(scania);
        Scania exitedScania = scaniaWorkShop.exitVehicle(scania);
        exitedScania.turnRight();
        assert(exitedScania.getCurrentDirection() == IMovable.Direction.WEST);
    }
}
