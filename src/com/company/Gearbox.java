package com.company;

public class Gearbox extends Component {
    public final Clutch clutch;
    private int actualGear = 0;
    private final int numOfGears;

    public Gearbox(String name, float weight, float price, int numOfGears, Clutch clutch) {
        super(name, weight, price + clutch.getPrice());
        this.numOfGears = numOfGears;
        this.clutch = clutch;
    }

    public int getNumOfGears() {
        return numOfGears;
    }

    public int getActualGear() {
        return actualGear;
    }

    public float getGearRatio() {
        if(actualGear != 0){
            return (float) (0.02 + (actualGear-1)*0.04);
        }
        else {
            return 0;
        }
    }

    public float getGearRatio(int gear) {
        return (float) (0.02 + (gear-1)*0.04);
    }

    public void shift(boolean gearUp) {
        if(gearUp){
            if((actualGear + 1 <= numOfGears)&&(clutch.clutchState())){
                actualGear += 1;
            }
        }
        else{
            if((actualGear - 1 >= 0)&&(clutch.clutchState())){
                actualGear -= 1;
            }
        }
    }
}