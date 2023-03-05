package View;

import Model.Vehicle.Vehicle;
import Model.World.VehicleSet;
import Model.World.WorldModel;
import Model.World.WorldUpdateListener;
import Controller.CarController;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import static View.RenderedVehicle.*;


/**
 * This class represents the full view of the MVC pattern of your car simulator.
 * It initializes with being center on the screen and attaching it's controller in it's state.
 * It communicates with the Controller by calling methods of it when an action fires of in
 * each of it's components.
 * TODO: Write more actionListeners and wire the rest of the buttons
 **/

public class WorldView extends JFrame implements WorldUpdateListener {
    private static final int X = 800;
    private static final int Y = 800;
    // The controller member
    private CarController carC;
    private final DrawPanel drawPanel;
    private final JPanel controlPanel = new JPanel();
    private final JPanel gasPanel = new JPanel();
    private JSpinner gasSpinner = new JSpinner();
    private int gasAmount = 0;
    private final JLabel gasLabel = new JLabel("Amount of gas");
    private final JButton gasButton = new JButton("Gas");
    private final JButton brakeButton = new JButton("Brake");
    private final JButton turboOnButton = new JButton("Saab Turbo on");
    private final JButton turboOffButton = new JButton("Saab Turbo off");
    private final JButton liftBedButton = new JButton("Raise Lift Bed");
    private final JButton lowerBedButton = new JButton("Lower Lift Bed");
    private final JButton startButton = new JButton("Start all cars");
    private final JButton stopButton = new JButton("Stop all cars");
    private final JButton addRandomCar = new JButton ("Add Car");
    private final JButton removeLastAddedCar = new JButton("Remove Car");


    // Constructor
    public WorldView(String framename, CarController carC){
        drawPanel = new DrawPanel(X, Y-240);
        initComponents(framename);
        this.carC = carC;

    }

    // Sets everything in place and fits everything
    // TODO: Take a good look and make sure you understand how these methods and components work
    private void initComponents(String title) {

        this.setTitle(title);
        this.setPreferredSize(new Dimension(X,Y));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        this.add(drawPanel);



        SpinnerModel spinnerModel =
                new SpinnerNumberModel(0, //initial value
                        0, //min
                        100, //max
                        1);//step
        gasSpinner = new JSpinner(spinnerModel);
        gasSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                gasAmount = (int) ((JSpinner)e.getSource()).getValue();
            }
        });

        gasPanel.setLayout(new BorderLayout());
        gasPanel.add(gasLabel, BorderLayout.PAGE_START);
        gasPanel.add(gasSpinner, BorderLayout.PAGE_END);

        this.add(gasPanel);

        controlPanel.setLayout(new GridLayout(2,4));

        controlPanel.add(gasButton, 0);
        controlPanel.add(turboOnButton, 1);
        controlPanel.add(liftBedButton, 2);
        controlPanel.add(addRandomCar, 3);
        controlPanel.add(brakeButton, 4);
        controlPanel.add(turboOffButton, 5);
        controlPanel.add(lowerBedButton, 6);
        controlPanel.add(removeLastAddedCar, 7);
        controlPanel.setPreferredSize(new Dimension((X/2)+75, 200));
        this.add(controlPanel);
        controlPanel.setBackground(Color.CYAN);


        startButton.setBackground(Color.blue);
        startButton.setForeground(Color.green);
        startButton.setPreferredSize(new Dimension(X/5-45,200));
        this.add(startButton);


        stopButton.setBackground(Color.red);
        stopButton.setForeground(Color.black);
        stopButton.setPreferredSize(new Dimension(X/5-45,200));
        this.add(stopButton);

        // This actionListener is for the gas button only
        // TODO: Create more for each component as necessary
        //TODO: what is this abomination?

        buttonsActions();


        // Make the frame pack all it's components by respecting the sizes if possible.
        this.pack();

        // Get the computer screen resolution
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Center the frame
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        // Make the frame visible
        this.setVisible(true);
        // Make sure the frame exits when "x" is pressed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void buttonsActions() {
        gasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carC.gas(gasAmount);
            }
        });

        brakeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carC.brake(gasAmount);
            }
        });

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carC.startVehicles();
            }
        });
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carC.stopVehicles();
            }
        });
        turboOnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carC.turboOn();
            }
        });
        turboOffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carC.turboOff();
            }
        });
        liftBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carC.wagonRaise();
            }
        });
        lowerBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carC.wagonLower();
            }
        });
        addRandomCar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carC.addRandomVehicle();
            }
        });
        removeLastAddedCar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carC.removeLastAddedVehicle();
            }
        });
    }

    @Override
    public void actOnAdding(int x, int y, String imageName) {
        addRenderedVehicle(new RenderedVehicle(x,y,imageName));
        drawPanel.repaint();
    }

    @Override
    public void actOnRemoving() {
        removeLastRenderedVehicle();
        drawPanel.repaint();
    }

    @Override
    public void actOnUpdate(ArrayList<Integer> xs, ArrayList<Integer> ys, ArrayList<String> names) {
        updateRenderedVehicles(xs, ys, names);
        drawPanel.repaint();
    }

    
    
    private static void updateRenderedVehicles(ArrayList<Integer> xs, ArrayList<Integer> ys, ArrayList<String> names) {
        for (int i = 0; i < renderedVehicles.size() ; i++) {
            int x = xs.get(i);
            int y = ys.get(i);
            String name = names.get(i);
            RenderedVehicle vehicleR = renderedVehicles.get(i);
            renderedVehicles.set(i, vehicleR.updateRenderedVehicle(x, y, name));
        }
    }

}