package com.company;

import javax.swing.*;
import java.util.Objects;

public class CarCreateWindow extends JFrame {
    public JPanel creatingWindow;
    private JButton addButton;
    private JButton cancelButton;
    private JTextField caroseriaModelField;
    private JTextField caroseriaWeightField;
    private JTextField caroseriaPriceField;
    private JTextField caroseriaRegField;
    private JPanel caroseriaPanel;
    private JPanel enginePanel;
    private JPanel gearBoxPanel;
    private JPanel clutchPanel;
    private JLabel modelTextField;
    private JLabel caroseriaWeightTextField;
    private JLabel caroseriaPriceTextField;
    private JLabel numTextField;
    private JLabel engineTextField;
    private JLabel gearboxTextField;
    private JLabel NumOfGearsTextField;
    private JLabel clutchTextField;
    private JPanel buttonPanel;
    private JLabel engineTypeTextField;
    private JRadioButton radioButtonPetrol;
    private JRadioButton radioButtonDiesel;
    private JLabel wheelsTextField;
    private JLabel hpTextField;
    private JTextField engineHPField;
    private JComboBox<String> wheelsComboBox;
    private JLabel errorLabel;
    private JComboBox gearboxNumOfGearsField;
    private JComboBox engineComboBox;
    private JComboBox gearboxComboBox;
    private JComboBox clutchComboBox;

    public CarCreateWindow(JComboBox<Car> cars) {
        setContentPane(creatingWindow);
        setTitle("Create New Car");


        cancelButton.addActionListener(e -> CarCreateWindow.this.dispose());
        addButton.addActionListener(e -> {
            try {
                // creating new car

                // new clutch
                Clutch clutch;
                if(clutchComboBox.getSelectedIndex() == 0){
                    clutch = new Clutch("standard", 20, 500);
                }
                else if(clutchComboBox.getSelectedIndex() == 1)
                {
                    clutch = new Clutch("comfort", 30, 1000);
                }
                else {
                    clutch = new Clutch("sport", 10, 2000);
                }

                // new gearbox
                Gearbox gearbox;
                if(gearboxComboBox.getSelectedIndex() == 0){

                    gearbox = new Gearbox("standard", 50, 5000, gearboxNumOfGearsField.getSelectedIndex() + 1, clutch);
                }
                else if(clutchComboBox.getSelectedIndex() == 1)
                {
                    gearbox = new Gearbox("comfort", 80, 10000, gearboxNumOfGearsField.getSelectedIndex() + 1, clutch);
                }
                else {
                    gearbox = new Gearbox("sport", 30, 15000, gearboxNumOfGearsField.getSelectedIndex() + 1, clutch);
                }

                // new engine
                Engine engine;

                // fuel type
                boolean fuelType;
                fuelType = radioButtonPetrol.isSelected();

                int maxRPM = 5000;
                if(!fuelType){
                    maxRPM -= 1000;
                }

                if(engineComboBox.getSelectedIndex() == 0){
                    engine = new Engine("standard", 200, 20000, maxRPM, Integer.parseInt(engineHPField.getText()));
                }
                else if(clutchComboBox.getSelectedIndex() == 1)
                {
                    engine = new Engine("comfort", 250, 40000, maxRPM + 2000, Integer.parseInt(engineHPField.getText()));
                }
                else {
                    engine = new Engine("sport", 150, 50000, maxRPM + 4000, Integer.parseInt(engineHPField.getText()));
                }

                // creating new car object
                Car car = new Car(caroseriaModelField.getText(),caroseriaRegField.getText(),engine,gearbox,Float.parseFloat(caroseriaWeightField.getText()),Integer.parseInt(Objects.requireNonNull(wheelsComboBox.getSelectedItem()).toString()),Float.parseFloat(caroseriaPriceField.getText()));
                cars.addItem(car);

                // closing window
                CarCreateWindow.this.dispose();
            }
            catch (Exception ignored){
                errorLabel.setVisible(true);
            }
        });
    }
}
