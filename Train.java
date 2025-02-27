/*
CS 1027B – Assignment 2
Name: Tudor Filimon
Student Number: 251406803
Email: tfilimon@uwo.ca
Created: Feb 15, 2025
*/

public class Train {
    DoubleNode<TrainCar> locomotive;
    DoubleNode<TrainCar> caboose; 

    //constructor
    public Train() {
        locomotive = null;
        caboose = null;
    }

    //getters
    public DoubleNode<TrainCar> getLocomotive() {
        return locomotive;
    }

    public DoubleNode<TrainCar> getCaboose() {
        return caboose;
    }

    //addCar() method
    public void addCar(TrainCar car){
        //Check if train is empty, then set the car to the locomotive
        if (locomotive == null) {
            //create a new node
            DoubleNode<TrainCar> carNode = new DoubleNode<>(car);
            //set both to indicate train is made up of only one car
            locomotive = carNode;
            caboose = carNode;
            return;
        }

        //Check if car can connect to the caboose
        if (caboose.getElement().canConnect(car)) {
            //create a new node
            DoubleNode<TrainCar> carNode = new DoubleNode<>(car);
            
            //update double pointers
            carNode.setPrevious(caboose);
            caboose.setNext(carNode);
            //make the new caboose the newNode
            caboose = carNode; 
            return;
        }

        //Find the closest valid position to insert the car
        //We know it can not connect to the end, so we check the second last car
        DoubleNode<TrainCar> current = caboose.getPrevious();

        //parse through the list, to find a connection point where both forward and backward connection is valid
        while (current != null) {
            //Check if the current car can connect to the new car and if the next element can connect to the car
            if (current.getElement().canConnect(car) && (current.getNext() == null || car.canConnect(current.getNext().getElement()))) {
                //create a new node
                DoubleNode<TrainCar> carNode = new DoubleNode<>(car);
                
                //update pointers to insert the node between the two cars
                carNode.setPrevious(current);
                carNode.setNext(current.getNext());
    
                //check for inserting at caboose
                if (current.getNext() != null) {
                    current.getNext().setPrevious(carNode);
                } else {
                    caboose = carNode; // If added at the end, update the caboose
                }
                
                //update the current's pointer and return successfully once done
                current.setNext(carNode);
                return;
            }
            //update the loop to keep parsing the linked list from back to front
            current = current.getPrevious();
        }

        //if the car was not added, throw a train exception
        throw new TrainException("Car cannot be added to the train.");
    }

    //try adding the car
    public boolean tryAddCar(TrainCar car){
        try {
            addCar(car);
            return true;
        } catch (TrainException e) {
            return false;
        }
    }

    //removing a car
    public void removeCar(TrainCar car){
        //initial check to see if train is empty
        if (locomotive == null) {
            throw new TrainException("Cannot remove car: The train is empty.");
        }
        
        //start at the front
        DoubleNode<TrainCar> current = locomotive;
        
        //parse the list
        while (current != null) {
            if (current.getElement().equals(car)) {
                //if the car is the locomotive and caboose (aka the train is just the car), it should not be removed
                if (current == locomotive && current == caboose){
                    locomotive = null;
                    caboose = null;
                    return;
                }

                //if the car is the locomotive, but the train has other cars, update the locomotive
                if (current == locomotive) {
                    locomotive = current.getNext();
                    if (locomotive != null) {
                        locomotive.setPrevious(null);;
                    }
                }

                //if the car is the caboose, update the caboose reference
                if (current == caboose) {
                    //set the new caboose to the 2nd last train car
                    caboose = current.getPrevious();
                    if (caboose != null) {
                        //discconect the old caboose using the new caboose
                        caboose.setNext(null);
                    }
                    return;
                }

                //check that removing this new car does not harm connection rules
                DoubleNode<TrainCar> prevNode = current.getPrevious();
                DoubleNode<TrainCar> nextNode = current.getNext();

                //check that the previous nor the next node is null, and that the previous and the next nodes can connect once removed
                if (prevNode != null && nextNode != null) {
                    if (!prevNode.getElement().canConnect(nextNode.getElement())) {
                        throw new TrainException("Cannot remove train car: Removing it would break connection.");
                    }
                }

                //if the car being removed is in the middle, adjust the links
                if (prevNode != null) {
                    prevNode.setNext(nextNode);
                }
                if (nextNode != null) {
                    nextNode.setPrevious(prevNode);
                }

                //once complete, return
                return;

            }
            
            //UPDATE the while loop to move forward
            current = current.getNext();


        }
        //if loop is traversed fully without finding the car, throw an exception
        throw new TrainException("Car not found in the train.");
    }


    //test removing a car
    public boolean tryRemoveCar(TrainCar car) {
        try {
            removeCar(car);
            return true;
        } catch (TrainException e) {
            return false;
        }
    }

    //toString method
    public String toString() {
        if (locomotive == null && caboose == null) {
            return "The train is empty.";
        } 

        //set current to locomotive, the start of the train, to parse it
        DoubleNode<TrainCar> current = new DoubleNode<>(null);
        current = locomotive;

        //create a string to return
        String trainList = "";

        while (current != null) {
            //check if car is of Reefer class (to check for temp), if not then it must be of Train class
            if (current.getElement() instanceof Reefer) {
                //check for last traincar to not add an extra space
                if (current == caboose){
                    trainList = trainList + current.getElement().toString();
                } else {
                    trainList = trainList + current.getElement().toString() + ", ";
                }  
            } else { //if the car is not a child of TrainCar
                if (current == caboose){
                    trainList = trainList + current.getElement().toString();
                } else {
                    trainList = trainList + current.getElement().toString() + ", ";
                }   
            }
            //update position
            current = current.getNext();
        }
        //return string when done
        return trainList;
    }

}
