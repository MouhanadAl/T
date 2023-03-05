import Model.Vehicle.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.assertEquals;
public class TruckTest {
    private Scania scania;
    private Carrier<Car> carrier;

    @Before
    public void startTrucks() {
        scania = new Scania();
        carrier = new Carrier<>();
    }

    @After
    public void stopTrucks() {
        scania = null; carrier = null;
    }


    @Test
    public void testInvalidLowerWagon() {
        scania.startEngine();
        scania.fullyRaiseWagon();
        assert(scania.getWagonDegree() == 0);
    }
    @Test
    public void testGasWhileWagonNotReady() {
        scania.fullyRaiseWagon();
        scania.gas(0.7);
        assertEquals(0,scania.getCurrentSpeed(),0.0);
    }

    @Test
    public void testGetLoadedCarsInStackOrder() {
        Saab95 saab = new Saab95();
        Saab95 saab2 = new Saab95();
        Volvo240 volvo3 = new Volvo240();
        saab.prepareToBeLoaded();
        saab2.prepareToBeLoaded();
        volvo3.prepareToBeLoaded();
        carrier.lowerRamp();
        carrier.loadCar(saab);
        carrier.loadCar(saab2);
        carrier.loadCar(volvo3);
        List<String> modelNames = carrier.getCarsNameModel();
        StringBuilder strB = new StringBuilder();
        for (String str : modelNames) {
            strB.append(str);
        }
        assert(strB.toString().contains("Volvo240Saab95Saab95"));
    }



}
