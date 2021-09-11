package cmdver.singleplayer;

import java.util.Scanner;

class Field {
    private final String[] alphabet = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
            "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

    private byte letArrLength;
    private byte numArrLength;

    private byte foundX;
    private byte foundY;

    private String[] letters;
    private byte[] numbers;

    public void setLen(byte let, byte num) {
        letArrLength = let;
        numArrLength = num;
    }

    public byte getLetArrLength() {
        return letArrLength;
    }

    public byte getNumArrLength() {
        return numArrLength;
    }

    private void createLetterArray() {
        letters = new String[letArrLength];
        System.arraycopy(alphabet, 0, letters, 0, letArrLength);
    }

    private void createNumberArray() {
        numbers = new byte[numArrLength];

        for (byte f = 0; f < numArrLength; f++) {
            numbers[f] = f;
        }
    }

    public String[] getLetterArray() {
        return letters;
    }

    public byte[] getNumArray() {
        return numbers;
    }

    private void findRandomX() {
        foundX = ((byte) (Math.random() * letArrLength));
    }

    private void findRandomY() {
        foundY = ((byte) (Math.random() * numArrLength));
    }

    public byte getRandomX() {
        return foundX;
    }

    public byte getRandomY() {
        return foundY;
    }

    public void createCoordinateSystem(Field fieldObj) {
        fieldObj.createLetterArray();
        fieldObj.createNumberArray();
    }

    public String createRandomCoordinate(Field fieldObj) {
        fieldObj.findRandomX();
        fieldObj.findRandomY();

        return letters[foundX] + numbers[foundY];
    }
}

class Ship {
    private byte countOfShips;
    private byte shipsParts;
    private String[][] shipsCoordinates;

    public void setShips(byte _countOfShips, byte _shipsParts) {
        countOfShips = _countOfShips;
        shipsParts = _shipsParts;
    }

    public byte getCountOfShips() {
        return countOfShips;
    }

    public byte getShipsParts() {
        return shipsParts;
    }

    public void createShips(Field objField, Ship objShip) {
        shipsCoordinates = new String[countOfShips][shipsParts];

        String[] letArr = objField.getLetterArray();
        byte[] numArr = objField.getNumArray();

        byte howManyPartsAhead;
        float cond;

        for (byte i = 0; i < objShip.getCountOfShips(); i++) {
            shipsCoordinates[i][0] = objField.createRandomCoordinate(objField);

            howManyPartsAhead = 0;
            cond = (float) Math.random();

            for (byte j = 1; j < objShip.getShipsParts(); j++) {
                if (cond > 0.5) {
                    if ((objField.getRandomX() + j) < objField.getLetArrLength()) {
                        shipsCoordinates[i][j] = letArr[objField.getRandomX() + j] + numArr[objField.getRandomY()];
                        howManyPartsAhead++;
                    } else
                        shipsCoordinates[i][j] = letArr[objField.getRandomX() - j + howManyPartsAhead]
                                + numArr[objField.getRandomY()];
                } else if (cond < 0.5)
                    if ((objField.getRandomY() + j) < objField.getNumArrLength()) {
                        shipsCoordinates[i][j] = letArr[objField.getRandomX()] + numArr[objField.getRandomY() + j];
                        howManyPartsAhead++;
                    } else
                        shipsCoordinates[i][j] = letArr[objField.getRandomX()]
                                + numArr[objField.getRandomY() - j + howManyPartsAhead];

            }

        }
    }

    public String[][] getShipsCoordinates() {
        return shipsCoordinates;
    }

    public boolean checkShipsCoordinates(Ship shipObj) {
        boolean anyRepeats = false;

        for (byte r = 0; r < shipObj.getCountOfShips(); r++)
            for (byte v = 0; v < shipObj.getShipsParts(); v++)
                for (byte a = (byte) (r + 1); a < shipObj.getCountOfShips(); a++)
                    for (byte x = (byte) (v + 1); x < shipObj.getShipsParts(); x++)
                        if (shipObj.getShipsCoordinates()[r][v].equals(shipObj.getShipsCoordinates()[a][x])) {
                            anyRepeats = true;
                            break;
                        }

        for (byte y = 0; y < shipObj.getCountOfShips(); y++)
            for (byte e = 0; e < shipObj.getShipsParts(); e++)
                for (byte w = (byte) (e + 1); w < shipObj.getShipsParts(); w++)
                    if (shipObj.getShipsCoordinates()[y][e].equals(shipObj.getShipsCoordinates()[y][w])) {
                        anyRepeats = true;
                        break;
                    }

        for (byte b = 0; b < shipObj.getCountOfShips(); b++)
            for (byte o = 0; o < shipObj.getShipsParts(); o++)
                for (byte h = (byte) (b + 1); h < shipObj.getCountOfShips(); h++)
                    if (shipObj.getShipsCoordinates()[b][o].equals(shipObj.getShipsCoordinates()[h][0])) {
                        anyRepeats = true;
                        break;
                    }

        for (byte m = 0; m < shipObj.getCountOfShips(); m++)
            for (byte l = (byte) (shipObj.getShipsParts() - 1); l < shipObj.getShipsParts(); l++)
                for (byte k = (byte) (m + 1); k < shipObj.getCountOfShips(); k++)
                    if (shipObj.getShipsCoordinates()[m][l].equals(shipObj.getShipsCoordinates()[k][l])) {
                        anyRepeats = true;
                        break;
                    }

        return anyRepeats;
    }
}

public class SeaBattle {
    private final String[] phrases = { "Hello and welcome to the \"Sea Battle\" game!",
            "But before the beginning I better introduce you into the rules. In \"Sea Battle\" you need to destroy enemy's ships.",
            " ",
            "Gameplay looks a bit like this: I ask you the coordinate in format LETTERnumber and you input it(A1, for example). ",
            "If your input is correct, then it means that you hit the enemy's ship. If it's not, then you missed. ",
            "After some correct inputs you destroy it. To win a game you need to destroy all of ships.", " ",
            "But firstly you need to input some other stuff: ", "1. Count of columns of play field (it's max x coordinate + 1);", 
            "2. Rows of play field (it's max y-coordinate + 1);",
            "3. Count of ships you fight against;", "4. Count of parts each ship must have (they have the same size).", " ",
            "Insert each number from a new line." };

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Field mainField = new Field();
        Ship mainShip = new Ship();
        SeaBattle battle = new SeaBattle();

        byte[] input = new byte[4];
        battle.printOut(battle.phrases);

        for (byte n = 0; n < input.length; n++)
            input[n] = in.nextByte();

        mainField.setLen(input[0], input[1]);
        mainField.createCoordinateSystem(mainField);

        mainShip.setShips(input[2], input[3]);

        do {
            mainShip.createShips(mainField, mainShip);
            mainShip.checkShipsCoordinates(mainShip);
        } while (mainShip.checkShipsCoordinates(mainShip));

        String[] letterArr = mainField.getLetterArray();
        byte[] numberArr = mainField.getNumArray();

        String[] phrases2 = { "Field contains " + letterArr.length + " columns and " + numberArr.length + " rows.",
                "In columns there are letters from " + letterArr[0] + " to " + letterArr[letterArr.length - 1],
                "In rows there are numbers from " + numberArr[0] + " to " + numberArr[numberArr.length - 1],
                "On field there are " + mainShip.getCountOfShips() + " ships and each of them consists of "
                        + mainShip.getShipsParts() + " parts",
                "Very well! Now...let the game begin :)" };

        battle.printOut(phrases2);

        boolean areAnyElementsLeft = true;
        boolean hit = false;
        boolean isShipDestroyed = false;
        boolean shotOldDestination = false;
        byte howManyPartsFound = 0;
        byte howManyShipsFound = 0;
        byte[][] whichShipsPartsWereFound = new byte[mainShip.getCountOfShips()][mainShip.getShipsParts()];

        for (byte q = 0; q < whichShipsPartsWereFound.length; q++)
            for (byte g = 0; g < whichShipsPartsWereFound[q].length; g++)
                whichShipsPartsWereFound[q][g] = 0;

        do {
            System.out.print("\nEnter hit coordinate: ");
            String hitCoordinates = in.next();

            for (byte t = 0; t < mainShip.getCountOfShips(); t++) {
                for (byte u = 0; u < mainShip.getShipsParts(); u++) {
                    if ((hitCoordinates.equals(mainShip.getShipsCoordinates()[t][u]))
                            && (whichShipsPartsWereFound[t][u] == 0)) {
                        hit = true;
                        whichShipsPartsWereFound[t][u] = (byte) 1;
                        howManyPartsFound++;

                        if ((howManyPartsFound >= mainShip.getShipsParts())
                                && (battle.searchForOnes(whichShipsPartsWereFound, t) == mainShip.getShipsParts())) {
                            isShipDestroyed = true;

                            for (byte c = 0; c < whichShipsPartsWereFound[t].length; c++)
                                whichShipsPartsWereFound[t][c] = (byte) 2;

                            howManyPartsFound -= mainShip.getShipsParts();
                            howManyShipsFound++;
                        }

                        break;
                    } else if ((hitCoordinates.equals(mainShip.getShipsCoordinates()[t][u]))
                            && ((whichShipsPartsWereFound[t][u] == 2) || (whichShipsPartsWereFound[t][u] == 1)))
                        shotOldDestination = true;

                }
            }

            if (hit)
                System.out.println("Hit!");
            else if (shotOldDestination)
                System.out.println("You've already shot successfully at this destination. Try some other!)");
            else
                System.out.println("Miss!");

            if (isShipDestroyed)
                System.out.println("Ship is destroyed!");

            hit = false;
            shotOldDestination = false;
            isShipDestroyed = false;

            if (howManyShipsFound == mainShip.getCountOfShips())
                areAnyElementsLeft = false;
        } while (areAnyElementsLeft);

        System.out.println("Congratulations!) You won the \"Sea Battle\" game!");

        in.close();
    }

    private void printOut(String[] phrases) {
        for (String phr : phrases)
            System.out.println(phr);
    }

    private byte searchForOnes(byte[][] arr, byte pos) {
        byte ones = 0;
        for (byte d = 0; d < arr[pos].length; d++)
            if (arr[pos][d] == (byte) 1)
                ones++;

        return ones;
    }
}