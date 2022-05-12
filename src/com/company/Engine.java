package com.company;

public class Engine extends Component{

    private int RPM = 0;
    private final int maxRPM;
    private final int RPMSpeedChange;
    private final int HP;

    public Engine(String name, float weight, float price, int maxRPM, int HP) {
        super(name, weight, price);
        this.maxRPM = maxRPM;
        this.HP = HP;
        this.RPMSpeedChange = HP*2;
    }

    // getters
    public int getRPM() {
        return RPM;
    }

    public int getMaxRPM() {
        return maxRPM;
    }

    public int getHP() {
        return HP;
    }

    // engine start
    public void start(){
        if(RPM == 0)
            RPM = 1000;
    }

    // engine stop
    public void stop(){
        if(RPM != 0)
            RPM = 0;
    }

    // RPM changing
    public void changeRPM(boolean increase){
        if(RPM != 0) {
            if (increase) {
                if (RPM + RPMSpeedChange <= maxRPM)
                    RPM += RPMSpeedChange;
                else if (maxRPM - RPM < RPMSpeedChange)
                    RPM = maxRPM;
            } else {
                if (RPM - RPMSpeedChange >= 1000)
                    RPM -= RPMSpeedChange;
                else if (RPM - 1000 < RPMSpeedChange)
                    RPM = 1000;
            }
        }
    }
}