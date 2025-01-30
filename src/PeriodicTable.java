public class PeriodicTable {
    private Element[][] mainTable;
    private Element[] lanthanides;
    private Element[] actinides;

    public PeriodicTable(String fileName) {
        mainTable = new Element[8][17];
        lanthanides = new Element[15];
        actinides = new Element[15];
        loadData(fileName);
    }

    public void loadData(String fileName) {
        TextFileReader fr = new TextFileReader(fileName);
        fr.readString(); //skip first line as it is header

        //parse the elements.txt file line by line
        while(!fr.endOfFile()) {
            String line = fr.readString();
            String[] data = line.split(",");
            int atomicNo = Integer.parseInt(data[0]);
            String name = data[1];
            String symbol = data[2];
            float atomicWeight = Float.parseFloat(data[3]);
            int period = Integer.parseInt(data[7]);
            int group = Integer.parseInt(data[8]);
            String state = data[9];

            //type variable
            String type = "";
            String[] types = {data[12], data[13], data[14]};

            //check type
            for (int i = 0; i < types.length; i++) {
                if (types[i].equals("yes")) {
                    type = types[i];
                }
            }

            //Create new Element with data above
            Element element = new Element(atomicNo, atomicWeight, symbol, name, state, type);

            //Populate arrays
            if (period == 6) {
                lanthanides[atomicNo-51] = element;
            } else if (period == 7) {
                actinides[atomicNo-89] = element;
            } else {mainTable[period][group-1] = element;}


        }
        fr.close();
    }

    public String toString() {
        String periodicTable = "";
        //Start by parsing through the main table
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 17; j++) {
                if (mainTable[i][j] != null && mainTable[i][j].getSymbol().length() == 2) {
                    periodicTable = periodicTable + mainTable[i][j].getSymbol() + "  ";
                } else if (mainTable[i][j] != null && mainTable[i][j].getSymbol().length() == 1) {
                    periodicTable = periodicTable + mainTable[i][j].getSymbol() + "   ";
                }

            }
        }
        //Then parse through the lathanides and the actinides
    }



}
