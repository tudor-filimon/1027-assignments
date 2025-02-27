/*
CS 1027B – Assignment 2
Name: Tudor Filimon
Student Number: 251406803
Email: tfilimon@uwo.ca
Created: Feb 15, 2025
*/

public class Reefer extends TrainCar {
    private int temp;

    //constructor
    public Reefer(int weight, int temp, String freight){
        super(weight, freight);
        this.temp = temp;
    }

    //getters
    public int getTemp() {
        return this.temp;
    }

    //setters
    public void setTemp(int temp) {
        this.temp = temp;
    }

    //toString
    public String toString() {
        return String.format("<%s, %d, %dC>", getFreight(), getWeight(), getTemp());
    }

    //check connection
    public boolean canConnect(TrainCar other) {
        //check using TrainCar's canConnect method
        if (super.canConnect(other)){
            return true;
        } 
        
        //check for instace of Reefer
        if (other instanceof Reefer) {
            //cast other from TrainCar to Reefer in order to use getTemp()
            Reefer newOther = (Reefer) other;
            if (Math.abs(this.temp - newOther.getTemp()) <= 5) {
                return true;
            }
        }
        return false;
    }

    public boolean equals(TrainCar other) {
        // Check if other is null
        if (other == null) {
            return false;
        }
    
        // Call TrainCar's equals() method to compare weight and freight
        if (this.getClass() != other.getClass()) {
            return false;
        }

        //check using super
        if (!(super.equals(other))) {
            return false;
        }
    
        // Cast other to Reefer to compare temperature
        Reefer otherReefer = (Reefer) other;
        return this.temp == otherReefer.getTemp();
    }
}
