package Model.Vehicle;

public interface IMovable {
    enum Direction { NORTH, EAST, SOUTH, WEST }
    void move(double increment) ;
    void moveNearTo(Direction dir, double x, double y, double extraDistance);
    void turnLeft();
    void turnRight();

    double getX();
    double getY();
    Direction getCurrentDirection();
    void setX(double x);
    void setY(double y);
    void setCurrentDirection(Direction d);
}
