/*
CS 1027B – Assignment 1
Name: Tudor Filimon
Student Number: 251406803
Email: tfilimon@uwo.ca
Created: January 30, 2025
*/

public class Compound {
    private Element[] elements;
    private int[] elementCount;

    public Compound(PeriodicTable table, String[][] compoundArray) {
        elements = new Element[compoundArray.length];
        elementCount = new int[compoundArray.length];

        //parse through the elements in the compoundArray
        for (int i = 0; i < compoundArray.length; i++) {
            String symbol = compoundArray[i][0];

            //check length to add the # to the elementCount
            if (compoundArray[i].length == 1) {
                elementCount[i] = 1;
            } else {
                elementCount[i] = Integer.parseInt(compoundArray[i][1]);
            }
            //populate the elements table
            elements[i] = table.getElement(symbol);
        }
    }

    public String getBondType() {
        if (elements.length == 2) {
            //get types for each element
            String type1 = elements[0].getType();
            String type2 = elements[1].getType();

            //check type for bond
            if ( (type1.equals("Metal") && type2.equals("Nonmetal")) || (type1.equals("Nonmetal") && type2.equals("Metal")) ) {
                return "ionic";
            } else if ( (type1.equals("Metalloid") && type2.equals("Metal") ) || (type1.equals("Metal") && type2.equals("Metalloid")) ) {
                return "covalent";
            } else if (type1.equals("Nonmetal") && type2.equals("Nonmetal")) {
                return "covalent";
            } else {
                return null;
            }
        } else { //if more than two elements, return null
            return null;
        }
    }

    public String toString() {
        String compoundString = "";
        //format accordingly and pull from the elements and elementCount
        for (int i = 0; i < elements.length; i++) {
            compoundString = compoundString + elements[i].getName() + ": " + elementCount[i] + "\n";
        }
        return compoundString;
    }

}



