/*
CS 1027B – Assignment 1
Name: Tudor Filimon
Student Number: 251406803
Email: tfilimon@uwo.ca
Created: January 30, 2025
*/

public class Element {
    //instance vars
    private int atomicNo;
    private float atomicWeight;
    private String symbol;
    private String name;
    private String state;
    private String type;

    //constructor
    public Element(int num, float wt, String sym, String nm, String st, String ty) {
        atomicNo = num;
        atomicWeight = wt;
        symbol = sym;
        name = nm;
        state = st;
        type = ty;
    }
    //getters
    public int getAtomicNo() { return atomicNo; }
    public float getAtomicWeight() { return atomicWeight; }
    public String getSymbol() { return symbol; }
    public String getState() { return state; }
    public String getType() { return type; }
    public String getName() { return name; }

    //setters
    public void setName(String nm) { name = nm; }
    public void setState(String st) { state = st; }
    public void setType(String ty) { type = ty; }

    //toString()
    public String toString() { return symbol + " (" + name + ")"; }

    //equality
    public boolean equals(Element other) {
        //return true or false
        return this.atomicNo == other.atomicNo;
    }
}
