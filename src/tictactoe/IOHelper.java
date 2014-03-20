package tictactoe;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class IOHelper {

    private static final String AXIS_X = "x";
    private static final String AXIS_Y = "y";

    public Stroke getStroke(Mark mark, Field field) {
        Stroke temporaryStroke = new Stroke();
        temporaryStroke.setX(correctInput(AXIS_X));
        temporaryStroke.setY(correctInput(AXIS_Y));
        temporaryStroke.setMark(mark);
        if (field.getMark(temporaryStroke.getX(),temporaryStroke.getY()) == Mark.N) {
            return temporaryStroke;
        }   else {
            System.out.println("This cell has marked. Try again");
            return new Stroke(temporaryStroke.getX(), temporaryStroke.getY(), field.getMark(temporaryStroke.getX(),temporaryStroke.getY()));
        }
    }


    private int correctInput(String strCoordinate) {
        String inputLine = " ";
        boolean correctLine = false;
        BufferedReader redStr = new BufferedReader(new InputStreamReader(System.in));
        while (!correctLine) {
            System.out.print(strCoordinate + " =");
            try {
                inputLine = redStr.readLine();
                if (Integer.parseInt(inputLine) >= 0 && Integer.parseInt(inputLine) <= 2) {
                    correctLine = true;
                } else {
                    System.out.println("Incorrect number. Number must to be between 0 and 2");
                }
            } catch (Exception e) {
                System.out.println("incorrect!");
            }

        }
        return Integer.parseInt(inputLine);

    }

    public void printResult(Mark winner) {
        switch (winner) {
            case DRAW:
                System.out.print("It is drawn game!");
                break;
            case O:
                System.out.print("Player O winner!");
                break;
            case X:
                System.out.print("Player X winner!");
                break;
        }

    }

    public void printField(Field field) {
        System.out.println();
        for (int i = 0; i < Field.DEFAULT_FIELD_SIZE; i++) {
            printLine(field, i);
            System.out.println();
        }
        System.out.println();

    }

    private void printLine(Field field, int lineNumber) {
        for (int i = 0; i < Field.DEFAULT_FIELD_SIZE; i++) {
            printCell(field, i, lineNumber);
        }
    }

    private void printCell(Field field, int x, int y) {
        String[][] printField = new String[Field.DEFAULT_FIELD_SIZE][Field.DEFAULT_FIELD_SIZE];
        switch (field.getMark(x, y)) {
            case N:
                printField[x][y] = " ";
                System.out.print("[" + printField[x][y] + "]");
                break;
            case O:
                printField[x][y] = "O";
                System.out.print("[" + printField[x][y] + "]");
                break;
            case X:
                printField[x][y] = "X";
                System.out.print("[" + printField[x][y] + "]");
                break;
        }
    }


}


