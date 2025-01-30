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

    //setters
    public void setName(String nm) { name = nm; }
    public void setState(String st) { state = st; }
    public void setType(String ty) { type = ty; }

    //stringify
    public String toString() { return symbol + " (" + name + ")"; }

    //equality
    public boolean equals(Element other) {
        if (this.atomicNo == other.atomicNo) {
            return true;
        } else {
            return false;
        }
    }
}
