/*
CS 1027B – Assignment 2
Name: Tudor Filimon
Student Number: 251406803
Email: tfilimon@uwo.ca
Created: Feb 15, 2025
*/

public class TrainCar {
    private int weight;
    private String freight;

    //constructor
    public TrainCar(int weight, String freight) {
        this.weight = weight;
        this.freight = freight;
    }

    //getters
    public int getWeight() {
        return this.weight;
    }

    public String getFreight() {
        return this.freight;
    }

    //setters
    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setFreight(String freight) {
        this.freight = freight;
    }

    //toString
    public String toString(){
        return "<" + getFreight() + ", " + getWeight() + ">";
    }

    /*
     * checks if train cars can connect
     * params: other train car
     */
    public boolean canConnect(TrainCar other) {
        //compare weight and freight
        if (other.getFreight().equals(this.freight) || other.getWeight() == this.weight) {
            return true;
        }
        return false;
    }

    /*
     * checks if train cars are equal
     * params: other train car
     */
    public boolean equals(TrainCar other) {
        // check if other isnt null
        if (other == null) {
            return false;
        }

        //check that they are both of the same class, where TrainCar should not be equal to Reefer
        if (this.getClass() != other.getClass()) {
            return false;
        }
        
        //check equality once other two conditionals are passed succesfully
        if ( (other.getFreight().equals(this.freight)) && (other.getWeight() == this.weight) ) {
            return true;
        }
        return false;
    }

}
