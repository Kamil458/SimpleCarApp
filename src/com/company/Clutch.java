package com.company;

public class Clutch extends Component{
    private boolean state = false;

    public Clutch(String name, float weight, float price) {
        super(name, weight, price);
    }

    public boolean clutchState() {
        return state;
    }

    public void press(){
        state = true;
    }

    public void release(){
        state = false;
    }
}