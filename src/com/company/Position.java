package com.company;
import java.lang.Math;

public class Position {
    // actual car positions
    private double actualX = 0;
    private double actualY = 0;

    // target
    private float newX = 0;
    private float newY = 0;

    // setting new target
    public void setNewX(float x) {
        this.newX = x;
    }
    public void setNewY(float y) {
        this.newY = y;
    }

    // getting actual positions
    public double getX() {
        return actualX;
    }
    public double getY() {
        return actualY;
    }

    //getting new positions
    public float getNewX() {
        return newX;
    }

    public void move(float V, float t) {
        // checking if a new target has been set
        if (actualX != newX || actualY != newY) {

            // km/h -> m/s
            V = V * 10 / 36;

            // move x and y
            double rx = (V * t * (newX - actualX)) / (Math.sqrt(Math.pow((newX - actualX), 2) + Math.pow((newY - actualY), 2)));

            double ry = (V * t * (newY - actualY)) / (Math.sqrt(Math.pow((newX - actualX), 2) + Math.pow((newY - actualY), 2)));


            if(Math.abs(newX-actualX)<rx){
                // target reach
                actualX = newX;
            }
            else {
                // skip elimination
                if(((actualX > newX) && (actualX + rx < newX))||((actualX < newX) && (actualX + rx > newX))){
                    actualX = newX;
                }
                else {
                    // move
                    actualX += rx;
                }
            }

            if(Math.abs(newY-actualY)<ry){
                actualY = newY;
            }
            else {
                // skip elimination
                if(((actualY > newY) && (actualY + ry < newY))||((actualY < newY) && (actualY + ry > newY))){
                    actualY = newY;
                }
                else {
                    // move
                    actualY += ry;
                }
            }
        }
    }
}
