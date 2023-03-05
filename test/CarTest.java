import Model.Vehicle.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class CarTest {
    private Saab95 saab;
    private Volvo240 volvo;

    @Before
    public void startCars() {
        saab = new Saab95();
        volvo = new Volvo240();
    }
    @After
    public void removeCars() {saab = null; volvo = null;}

    @Test
    public void testStartEngine() {
        saab.startEngine();
        assert(saab.getCurrentSpeed() == 0.1);
    }
    @Test
    public void testStopEngine() {
        saab.stopEngine();
        assert(saab.getCurrentSpeed() == 0);
    }

    @Test // 0.5 * 1.25 * 1.3 + 0.1 = 0.9125
    public void testSaabGas() {
        saab.startEngine();
        saab.setTurboOn();
        saab.gas(0.5);
        assertEquals(0.9125, saab.getCurrentSpeed(), 0.0);
    }
    @Test
    public void testSaabUnreasonableGas() {
        saab.startEngine();
        saab.setCurrentSpeed(saab.getCurrentSpeed()*5000);
        saab.gas(0.5);
        assertEquals(0.725, saab.getCurrentSpeed(), 0.0);
    }
    @Test //  100 - (100*0.01*1.25)
    public void testVolvoBrake() {
        volvo.setCurrentSpeed(volvo.getEnginePower());
        volvo.brake(1);
        assertEquals(98.75, volvo.getCurrentSpeed(), 0.0);
    }
    @Test //  100 - (100*0.01*1.25)
    public void testVolvoUnreasonableBrake() {
        volvo.startEngine();
        volvo.setCurrentSpeed(volvo.getEnginePower()*50);
        volvo.brake(1);
        assertEquals(0, volvo.getCurrentSpeed(), 0.0);
    }
    @Test
    public void testSpeedUpperLimits() {
       saab.setCurrentSpeed((300));
       assert(saab.getCurrentSpeed() == 0);
    }
    @Test
    public void testSpeedLowerLimits() {
        saab.setCurrentSpeed((-1));
        assert(saab.getCurrentSpeed() == 0);
    }

    @Test
    public void testValidAmountRange() {
        saab.setCurrentSpeed(40);
        saab.gas(666);
        assert(saab.getCurrentSpeed() == 40);
    }

    @Test
    public void testTurningLeft() { // { NORTH, EAST, SOUTH, WEST } Start at SOUTH

        saab.turnLeft();
        saab.turnLeft();
        saab.turnLeft();
        saab.turnLeft();
        volvo.setCurrentDirection(IMovable.Direction.SOUTH);
        assert(saab.getCurrentDirection().equals(volvo.getCurrentDirection()));
    }

    @Test
    public void testTurningRight() { // { NORTH, EAST, SOUTH, WEST } Start at SOUTH
        volvo.turnRight();
        volvo.turnRight();
        volvo.setCurrentDirection(IMovable.Direction.NORTH);
        assert(volvo.getCurrentDirection() == volvo.getCurrentDirection());
    }

    @Test
    public void testMoveSouth() { // { NORTH, EAST, SOUTH, WEST } Start at SOUTH
        saab.setCurrentSpeed(100);
        saab.move();
        assertEquals(100,saab.getY(),0.0);
    }
    @Test
    public void testMoveEAST() { // { NORTH, EAST, SOUTH, WEST } Start at SOUTH
        saab.setCurrentSpeed(100);
        saab.turnLeft();
        saab.move();
        assertEquals(100,saab.getX(),0.0);
    }

    @Test
    public void testGasWhileBeingTransported() {
        Carrier<Car> carrier = new Carrier<>();
        saab.prepareToBeLoaded();
        carrier.lowerRamp();
        carrier.loadCar(saab);
        saab.gas(1);
        assertEquals(0,saab.getCurrentSpeed(),0.0);
    }

    @Test
    public void testDirectionEqualsCarrier() {
        Carrier<Car> carrier = new Carrier<>();
        volvo.prepareToBeLoaded();
        carrier.lowerRamp();
        carrier.loadCar(volvo);
        carrier.turnLeft();
        assert(volvo.getCurrentDirection().equals(carrier.getCurrentDirection()));
    }

    @Test
    public void testTurningAfterBeingTransported() {
        Carrier<Car> carrier = new Carrier<>();
        volvo.prepareToBeLoaded();
        carrier.lowerRamp();
        carrier.loadCar(volvo);
        carrier.raiseRamp();
        carrier.gas(1);
        carrier.turnRight();
        carrier.stopEngine();
        carrier.lowerRamp();
        carrier.unloadCar();
        volvo.turnLeft();
        assert(volvo.getCurrentDirection() == IMovable.Direction.SOUTH);
    }

}
