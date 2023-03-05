package View;

import Model.Vehicle.Vehicle;
import Model.World.WorldModel;
import Model.World.WorldUpdateListener;

import java.awt.*;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represent the animated part of the view with the car images.

public class DrawPanel extends JPanel {

    // Just a single image, TODO: Generalize
    private BufferedImage volvoImage;
    private BufferedImage saabImage;
    private BufferedImage scaniaImage;



    // Initializes the panel and reads the images

    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.WHITE);
        boundImages();

        //setVehicles();
    }
    // TODO: Make this genereal for all cars --how point works?
//    public void moveit (String modelName, int x, int y){
//        for (RenderedVehicle vehicleR : renderedVehicles) {
//            if (vehicleR.getModelName().equals(modelName)) {
//                vehicleR.setVehiclePoint(x, y);
//            }
//        }
//    }


//    private void setVehicles() {
//        try{
//        renderedVehicles.add(new VehicleRenders(VehicleFactory.createVolvo240(),
//                new Point(),
//                ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg"))));
//
//        renderedVehicles.add(new VehicleRenders(VehicleFactory.createSaab95(),
//                new Point(),
//                ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg"))));
//        renderedVehicles.add(new VehicleRenders(VehicleFactory.createScania(),
//                new Point(),
//                ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg"))));
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//    }
//    public  void boundImage(Iterator<Vehicle> vehicles) {
//        setImages();
//        while (vehicles.hasNext()) {
//            Vehicle vehicle = vehicles.next();
//            switch (vehicle.getModelName()) {
//                case "Saab95" ->
//            }
//        }
//    }

    private void boundImages() {
        try {
            volvoImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg"));
            saabImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg"));
            scaniaImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private BufferedImage getImage(String vehicleModel) {
        switch (vehicleModel) {
            case "Saab95" -> {return saabImage;}
            case "Volvo240" -> {return volvoImage;}
            case "Scania" -> {return scaniaImage;}
        }
        return null;
    }
    
    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (RenderedVehicle vehicleR : RenderedVehicle.renderedVehicles) {
            drawVehicle(g, vehicleR);
        }
    }

    private void drawVehicle(Graphics g, RenderedVehicle vehicleR) {
        g.drawImage(getImage(vehicleR.getImageName()),
                vehicleR.getX(),
                vehicleR.getY(),
                null);
    }


}
