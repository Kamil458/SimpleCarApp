package com.company;

public class Car extends Thread {
    private final String model;
    private final String registrationNumber;
    private final float maxSpeed;
    private final float weight;
    private final float price;
    private final int wheels;

    public final Engine engine;
    public final Gearbox gearbox;

    private final Position actualPosition = new Position();

    @Override
    public String toString() {
        return model + ", " + registrationNumber;
    }


    // contruktor
    public Car(String model, String registrationNumber, Engine engine, Gearbox gearbox, float weight, int wheels, float price) {
        this.model = model;
        this.registrationNumber = registrationNumber;
        this.engine = engine;
        this.gearbox = gearbox;
        this.weight = weight + engine.getWeight() + gearbox.getWeight();
        this.wheels = wheels;
        this.maxSpeed = (float) (engine.getMaxRPM() * gearbox.getGearRatio(gearbox.getNumOfGears()) * getWheels() * 0.16 * 0.06);
        this.price = price + engine.getPrice() + gearbox.getPrice();
        // new thread
        start();

    }

    // getters
    public String getModel() {
        return model;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public float getWeight() {
        return weight;
    }

    public float getPrice() {
        return price;
    }

    public int getWheels() {
        return wheels;
    }

    // car engine start and stop
    public void OnOff(){
        if(isEngineOn())
            engine.stop();
        else
            engine.start();
    }

    public boolean isEngineOn(){
        return engine.getRPM() != 0;
    }

    // getting actual speed (km/h)
    public float getCurrentSpeed(){
        if(!gearbox.clutch.clutchState()) {
            if (actualPosition.getX() != actualPosition.getNewX()) {
                // RPM * gear ratio * wheel * 2 * pi * 0.0254(cal to meter) *0.06(m/min -> km/h)
                return (float) (engine.getRPM() * gearbox.getGearRatio() * getWheels() * 0.16 * 0.06);
            }
            else{
                return 0;
            }
        }
        else{
            return 0;
        }
    }

    // getting actual position
    public float getActualPositionX(){
        return (float) actualPosition.getX();
    }

    public float getActualPositionY(){
        return (float) actualPosition.getY();
    }

    // car moving
    public void goTO(float x, float y){
        actualPosition.setNewX(x);
        actualPosition.setNewY(y);
    }

    // new thread for moving the car
    public void run(){
        try {
            while (true)
            {
                // car moving
                actualPosition.move(getCurrentSpeed(), 0.1f);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            return;
        }
    }
}