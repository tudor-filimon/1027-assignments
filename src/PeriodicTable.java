/*
CS 1027B – Assignment 1
Name: Tudor Filimon
Student Number: 251406803
Email: tfilimon@uwo.ca
Created: January 30, 2025
*/

public class PeriodicTable {
    private Element[][] mainTable;
    private Element[] lanthanides;
    private Element[] actinides;

    public PeriodicTable(String fileName) {
        mainTable = new Element[7][18];
        lanthanides = new Element[15];
        actinides = new Element[15];
        loadData(fileName);
    }

    public void loadData(String fileName) {
        TextFileReader fr = new TextFileReader(fileName);
        fr.readString(); //skip first line as it is header

        //parse the elements.txt file line by line and extract the data neccesary based on the header in the file
        while (!fr.endOfFile()) {
            String line = fr.readString(); //skip the first line as that is the header
            String[] data = line.split(","); //split line by comma

            //pull out data
            int atomicNo = Integer.parseInt(data[0]);
            String name = data[1];
            String symbol = data[2];
            float atomicWeight = Float.parseFloat(data[3]);
            int period = Integer.parseInt(data[7]);

            String group = data[8];
            String state = data[9];

            //type variable -- check for type
            String type = "";
            if (data[12].equals("yes")) {
                type = "Metal";
            } else if (data[13].equals("yes")) {
                type = "Nonmetal";
            } else if (data[14].equals("yes")) {
                type = "Metalloid";
            }

            //Create new Element with data above
            Element element = new Element(atomicNo, atomicWeight, symbol, name, state, type);

            //Fill the arrays for mainTable, lactinides, and anthinides
            if (period == 6 && group.isEmpty()) {
                lanthanides[atomicNo - 57] = element;
            } else if (period == 7 && group.isEmpty()) {
                actinides[atomicNo - 89] = element;
            } else if (Integer.parseInt(group) >= 0 && Integer.parseInt(group) <= 18) {
                mainTable[period - 1][Integer.parseInt(group) - 1] = element;
            }

        }
        fr.close();
    }

    public Element getElement(String sym) {
        //parse through lanthanides
        for (int i = 0; i < lanthanides.length; i++) {
            if (lanthanides[i] != null && lanthanides[i].getSymbol().equals(sym)) {
                return lanthanides[i];
            }
        }

        //parse through actinides
        for (int i = 0; i < actinides.length; i++) {
            if (actinides[i] != null && actinides[i].getSymbol().equals(sym)) {
                return actinides[i];
            }
        }

        //parse through maintable
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 18; j++) {
                if (mainTable[i][j] != null && mainTable[i][j].getSymbol().equals(sym)) {
                    return mainTable[i][j];
                }
            }
        }
        //if none is found, return null
        return null;
    }

    public Element[] getPeriod(int per) {
        //first we set up a count for the array length
        //if the period is 6 or 7, we automatically add 15 to consider lathanides or actinides
        int count = 0;
        if (per == 6 || per == 7) {
            count += 15;
        }

        //error trapping for incorrect inputs
        if (per <= 0 || per >= 8) {
            return null;
        }

        //first, we need to count how many spots we allocate to the array that are not null
        for (int j = 0; j < 18; j++) {
            if (mainTable[per - 1][j] != null) { //first period is 0 index
                count++;
            }
        }

        //second, we create the return array and fill those spots
        Element[] periodReturn = new Element[count];

        //then we fill those spots; we need to keep track of the true index of the array though
        int trueIndex = 0;

        //special cases with lanthanides and actinidies where their elements are outside of the maintable
        if (per == 6) {
            for (int j = 0; j < 18; j++) {
                if (mainTable[per - 1][j] != null) {
                    periodReturn[trueIndex] = mainTable[per - 1][j];
                    trueIndex++;
                } else if (mainTable[per - 1][j] == null) { //this should run on the null/blank space in the 6th period
                    for (int l = 0; l < 15; l++) {
                        periodReturn[trueIndex] = lanthanides[l];
                        trueIndex++;
                    }
                }
            }
        } else if (per == 7) {
            for (int j = 0; j < 18; j++) {
                if (mainTable[per - 1][j] != null) {
                    periodReturn[trueIndex] = mainTable[per - 1][j];
                    trueIndex++;
                } else if (mainTable[per - 1][j] == null) { //this should run on the null/blank space in the 7th period
                    for (int a = 0; a < 15; a++) {
                        periodReturn[trueIndex] = actinides[a];
                        trueIndex++;
                    }
                }
            }
        }
        //for any period that is not 6 or 7
        else {
            for (int j = 0; j < 18; j++) {
                if (mainTable[per - 1][j] != null) {
                    periodReturn[trueIndex] = mainTable[per - 1][j];
                    trueIndex++;
                }
            }
        }
        return periodReturn;
    }

    public Element[] getGroup(int grp) {
        if (grp <= 0 || grp >= 19) {
            return null;
        }

        //first, we count the length of the array, with no null/blank spots
        int count = 0;
        for (int i = 0; i < 7; i++) {
            if (mainTable[i][grp - 1] != null) {
                count++;
            }
        }

        //second, we create the return array and fill it out, keeping track of the trueIndex like above
        Element[] groupReturn = new Element[count];
        int trueIndex = 0;

        for (int i = 0; i < 7; i++) {
            if (mainTable[i][grp - 1] != null) {
                groupReturn[trueIndex] = mainTable[i][grp - 1];
                trueIndex++;
            }
        }
        return groupReturn;
    }

    public Element[] getLanthanides() {
        return lanthanides;
    }

    public Element[] getActinides() {
        return actinides;
    }


    public String toString() {
        String periodicTable = "";

        //Start by parsing through the main table
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 18; j++) {
                //when the element is not null, check if the length of the symbol is one or two, and format accordingly
                if (mainTable[i][j] != null) {
                    //check the length of the symbol and format accordingly
                    if (mainTable[i][j].getSymbol().length() == 2) {
                        periodicTable = periodicTable + "  " + mainTable[i][j].getSymbol();
                    } else if (mainTable[i][j].getSymbol().length() == 1) {
                        periodicTable = periodicTable + "   " + mainTable[i][j].getSymbol();
                    }
                } else { //if the element is null, simply insert 4 spaces
                    periodicTable = periodicTable + "    ";
                }

            }
            //at the end of each row, move to the next
            periodicTable = periodicTable + "\n";
        }

        /* Then parse through the lathanides and actinides in the same manner,
        with the same formatting based on the length of the symbol
        to match the instructions. */
        periodicTable = periodicTable + "\n";
        periodicTable = periodicTable + "            ";

        for (int i = 0; i < 15; i++) {
            if (lanthanides[i] != null) {
                //check the length of the symbol and format accordingly
                if (lanthanides[i].getSymbol().length() == 2) {
                    periodicTable = periodicTable + "  " + lanthanides[i].getSymbol();
                } else if (lanthanides[i].getSymbol().length() == 1) {
                    periodicTable = periodicTable + "   " + lanthanides[i].getSymbol();
                }
            } else { //if the element is null, simply insert 4 spaces
                periodicTable = periodicTable + "    ";
            }
        }

        //same for the actinides
        periodicTable = periodicTable + "\n";
        periodicTable += "            ";

        for (int i = 0; i < 15; i++) {
            if (actinides[i] != null) {
                //check the length of the symbol and format accordingly
                if (actinides[i].getSymbol().length() == 2) {
                    periodicTable = periodicTable + "  " + actinides[i].getSymbol();
                } else if (actinides[i].getSymbol().length() == 1) {
                    periodicTable = periodicTable + "   " + actinides[i].getSymbol();
                } else { //if null, add empty four spaces
                    periodicTable = periodicTable + "    ";
                }
            }
        }
        return periodicTable;
    }
}




