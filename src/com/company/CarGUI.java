package com.company;

import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CarGUI extends Thread{
    private JPanel appPanel;
    private JPanel carsPanel;
    private JComboBox<Car> carsComboBox;
    private JButton deleteCar;
    private JButton addCar;
    private JPanel mapPanel;
    private JPanel parametersPanel;
    private JPanel controlPanel;
    private JTextField modelField;
    private JLabel modelTextField;
    private JLabel numTextField;
    private JTextField numField;
    private JLabel weightTextField;
    private JTextField weightField;
    private JLabel maxSpeedTextField;
    private JTextField maxSpeedField;
    private JLabel engineTextField;
    private JTextField engineField;
    private JLabel gearboxTextField;
    private JTextField gearboxField;
    private JLabel clutchTextField;
    private JTextField clutchField;
    private JLabel priveTextField;
    private JTextField priceField;
    private JButton accelerationButton;
    private JButton clutchButton;
    private JButton buttonGearDown;
    private JButton buttonGearUp;
    private JButton brakeButton;
    private JPanel functionsPanel;
    private JLabel mapLabel;
    private JLabel carLabel;
    private JTextField gearField;
    private JButton startButton;
    private JLabel currentSpeedTextField;
    private JLabel currentRPMTextField;
    private JTextField currentRPMField;
    private JTextField currentSpeedField;
    private JLabel hpTextField;
    private JTextField hpField;
    private Car car;

    public CarGUI(Car c) {
        car = c;

        carsComboBox.addItem(car);
        start();

        ImageIcon mapIcon = new ImageIcon("img/map.png");
        ImageIcon carIcon = new ImageIcon("img/car.png");
        ImageIcon upIcon = new ImageIcon("img/up.png");
        ImageIcon downIcon = new ImageIcon("img/down.png");

        mapLabel.setIcon(mapIcon);
        carLabel.setIcon(carIcon);
        buttonGearUp.setIcon(upIcon);
        buttonGearDown.setIcon(downIcon);

        addCar.addActionListener(e -> {
            // open new window
            JFrame f = new CarCreateWindow(carsComboBox);
            f.pack();
            f.setVisible(true);
        });

        deleteCar.addActionListener(e -> {
        });

        startButton.addActionListener(e -> car.OnOff());

        buttonGearUp.addActionListener(e -> car.gearbox.shift(true));

        buttonGearDown.addActionListener(e -> car.gearbox.shift(false));

        clutchButton.addActionListener(e -> {
            if(car.gearbox.clutch.clutchState()){
                car.gearbox.clutch.release();
                clutchButton.setBackground(new Color(29,30,28));
            }
            else {
                car.gearbox.clutch.press();
                clutchButton.setBackground(new Color(8,81,31));
            }
        });

        brakeButton.addActionListener(e -> car.engine.changeRPM(false));

        accelerationButton.addActionListener(e -> car.engine.changeRPM(true));

        mapPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                car.goTO(e.getX(),e.getY());
            }
        });

        carsComboBox.addActionListener(e -> car = (Car) carsComboBox.getSelectedItem());

        deleteCar.addActionListener(e -> {
            if (carsComboBox.getItemCount() != 1) {
                car.interrupt();
                carsComboBox.removeItem(car);
            }
        });
    }

    public void refresh() {
        carLabel.setLocation((int) car.getActualPositionX(), (int) car.getActualPositionY());
        modelField.setText(car.getModel());
        numField.setText(car.getRegistrationNumber());
        weightField.setText(Float.toString(car.getWeight()));
        maxSpeedField.setText(Float.toString(car.getMaxSpeed()));
        hpField.setText(Integer.toString(car.engine.getHP()));
        engineField.setText(car.engine.getName());
        gearboxField.setText(car.gearbox.getName());
        clutchField.setText(car.gearbox.clutch.getName());
        priceField.setText(Float.toString(car.getPrice()));
        gearField.setText(Integer.toString(car.gearbox.getActualGear()));
        currentRPMField.setText(Float.toString(car.engine.getRPM()));
        currentSpeedField.setText(Float.toString(car.getCurrentSpeed()));
        if(!car.isEngineOn()){
            startButton.setText("START");
            startButton.setForeground( new Color(9,81,31));
        }
        else {
            startButton.setText("STOP");
            startButton.setForeground( new Color(195,0,14));
        }
    }

    public void run(){
        try {
            while (true){
                refresh();
                Thread.sleep(100);
            }
        }catch (Exception ignored){}
    }

    public static void main(String[] args) {

        // first starting car
        Clutch clutch = new Clutch("standard",30,1000);
        Gearbox gearbox = new Gearbox("standard",150,2000,5,clutch);
        Engine engine = new Engine("1.9 TDI",300,5000,4500,120);
        Car golf = new Car("Golf","KT 123",engine,gearbox,800,16,50000);

        JFrame frame = new JFrame("CarGUI");
        frame.setContentPane(new CarGUI(golf).appPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }
}