package tictactoe;

import java.util.ArrayList;
import java.util.Random;

public class Computer {
    public Field checkField = new Field();

    public Field initialCheckField (Field field1, Field field2)   {
        for (int j = 0; j != Field.DEFAULT_FIELD_SIZE; j++) {
            for (int i = 0; i != Field.DEFAULT_FIELD_SIZE; i++) {
                switch (field1.getMark(i, j)) {
                    case DRAW:
                        field2.makeStroke(new Stroke(i,j,Mark.DRAW));
                    case O:
                        field2.makeStroke(new Stroke(i,j,Mark.O));
                    case X:
                        field2.makeStroke(new Stroke(i,j,Mark.X));
                }
            }
        }
        return field2;
    }

    public Stroke computerGenerateStroke(Mark mark, Field field) {
        Mark markOpponent;
        boolean endCheck = false;
        Stroke computerStroke = new Stroke();
        checkField.eraseField();
        checkField = initialCheckField(field,checkField);
        if (mark == Mark.N) {
            markOpponent = Mark.O;
        }   else {
            markOpponent = Mark.X;
        }

        for (int item = 0; item < emptyCellLine(field).size(); item++ ) {
            checkField.setMark(emptyCellLine(field).get(item).getX(), emptyCellLine(field).get(item).getY(), mark);
            if (checkField.checkGameOver(mark)) {
                computerStroke.setX(emptyCellLine(field).get(item).getX());
                computerStroke.setY(emptyCellLine(field).get(item).getY());
                computerStroke.setMark(mark);
                endCheck = true;
                break;
            }   else   {
                checkField.setMark(emptyCellLine(field).get(item).getX(), emptyCellLine(field).get(item).getY(), Mark.N);
            }
        }
        if (!endCheck)  {
            for (int item = 0; item < emptyCellLine(field).size(); item++ ) {
                checkField.setMark(emptyCellLine(field).get(item).getX(), emptyCellLine(field).get(item).getY(), markOpponent);
                if (checkField.checkGameOver(markOpponent)) {
                    computerStroke.setX(emptyCellLine(field).get(item).getX());
                    computerStroke.setY(emptyCellLine(field).get(item).getY());
                    computerStroke.setMark(mark);
                    endCheck = true;
                    break;
                }   else    {
                    checkField.setMark(emptyCellLine(field).get(item).getX(), emptyCellLine(field).get(item).getY(), Mark.N);
                }
            }
        }
        if ((!endCheck) && (field.getMark(1, 1) == Mark.N)) {             // поменять на переменные
            computerStroke.setX(1);
            computerStroke.setY(1);
            computerStroke.setMark(mark);
            checkField.setMark(1, 1, mark);
        } else if (!endCheck) {
            Random random = new Random();
            int randInt;
            randInt = random.nextInt(emptyCellLine(field).size());
            computerStroke.setX(emptyCellLine(field).get(randInt).getX());
            computerStroke.setY(emptyCellLine(field).get(randInt).getY());
            computerStroke.setMark(mark);
            checkField.setMark(computerStroke.getX(), computerStroke.getY(), mark);
        }
        return computerStroke;

    }

    private ArrayList<Stroke> emptyCellLine(Field field) {
        ArrayList<Stroke> arrStroke = new ArrayList<Stroke>();
        int n = 0;
        for (int j = 0; j != Field.DEFAULT_FIELD_SIZE; j++) {
            for (int i = 0; i != Field.DEFAULT_FIELD_SIZE; i++) {
                if (field.getMark(i, j) == Mark.N) {
                    Stroke stroke = new Stroke(i, j, Mark.N);
                    arrStroke.add(n, stroke);
                    n++;
                }
            }
        }
        return arrStroke;
    }
}
