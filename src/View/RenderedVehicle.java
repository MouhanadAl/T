package View;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import Model.Vehicle.Vehicle;

import javax.imageio.ImageIO;

import static java.lang.System.out;

public class RenderedVehicle {
    private int x;
    private int y;
    private String imageName;
    static ArrayList<RenderedVehicle> renderedVehicles = new ArrayList<>();

    RenderedVehicle(int x, int y, String imageName) {
        this.x = x;
        this.y = y;
        this.imageName = imageName;
    }

    RenderedVehicle updateRenderedVehicle(int x, int y , String imageName) {
        this.x = x;
        this.y = y;
        this.imageName = imageName;
        return this;
    }

    int getX() { return x;}
    int getY() { return y;}
    String getImageName() { return imageName;}


    static void addRenderedVehicle( RenderedVehicle vehicleR) {
        renderedVehicles.add(vehicleR);
    }
    static void removeLastRenderedVehicle() {
        renderedVehicles.remove(renderedVehicles.size()-1);
    }























//    private String vehicleModel;
//    private Point vehiclePoint;
//    private BufferedImage vehicleImage;
//    //private final VehicleSet vehicleSet;
//
//    private RenderedVehicle(String vehicleModel, Point vehiclePoint, BufferedImage vehicleImage) {
//        this.vehicleModel = vehicleModel;
//        this.vehiclePoint = vehiclePoint;
//        this.vehicleImage = vehicleImage;
//    }
//    public RenderedVehicle(Vehicle v) {
//        this("", new Point(), null);
//        setUpVehicleRender(v);
//    }
//
//    void setUpVehicleRender(Vehicle vehicle) {
//        setVehiclePoint((int) vehicle.getX(), (int) vehicle.getY());
//        vehicleModel = vehicle.getModelName();
//        try {
//            vehicleImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg"));
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    private BufferedImage boundImage(String vehicleModel) {
//        try {
//            switch (vehicleModel) {
//                case "Saab95" -> {
//                    return ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg"));
//                }
//                case "Volvo240" -> {
//                    return ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg"));
//                }
//                case "Scania" -> {
//                    return ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg"));
//                }
//            }
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//        out.print("check!!!!!!!!");
//        return null;
//    }
//
//
//    public void setVehiclePoint(int x, int y) {
//        vehiclePoint.x = x;
//        vehiclePoint.y = y;
//    }
//
//    public int getVehiclePointX() {
//        return vehiclePoint.x;
//    }
//    public int getVehiclePointY() {
//        return vehiclePoint.y;
//    }
//    public BufferedImage getVehicleImage() {
//        return vehicleImage;
//    }
//
//    public String getModelName() {
//        return vehicleModel;
//    }

}
