package tictactoe;


import java.util.ArrayList;
import java.util.Random;

public class Field {

    public static final int DEFAULT_FIELD_SIZE = 3;

    private final Mark[][] fieldGame;
    private final int size;
    private boolean gameOver = false;
    private Mark winner;

    public Field() {
        this(DEFAULT_FIELD_SIZE);
    }

    public Field(int size) {
        this.size = size;
        fieldGame = new Mark[this.size][this.size];
        eraseField();
    }

    public Mark getMark(int x, int y) {
        return fieldGame[x][y];
    }

    public boolean makeStroke(Stroke stroke) {
        int x = stroke.getX();
        int y = stroke.getY();
        boolean entered;
        if (fieldGame[x][y] == Mark.N) {
            fieldGame[x][y] = stroke.getMark();
            entered = true;
        } else {
            entered = false;
        }
        if (diagonalWinner(stroke.getMark()) || columnWinner(stroke.getMark()) || lineWinner(stroke.getMark()) || diagonalNegativeWinner(stroke.getMark())) {
            winner = stroke.getMark();
            gameOver = true;
        } else if (isDrawnGame()) {
            winner = Mark.DRAW;
            gameOver = true;
        } else {
            winner = Mark.N;
            gameOver = false;
        }
        return entered;
    }


    public Mark getWinner() {
        return winner;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    private boolean isDrawnGame() {
        for (int i = 0; i < this.size; i++) {
            if (!checkLineHasNoEmptyCells(i)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkLineHasNoEmptyCells(int lineNumber) {
        for (int i = 0; i < this.size; i++) {
            if (fieldGame[i][lineNumber] == Mark.N) {
                return false;
            }
        }
        return true;
    }


    private boolean diagonalWinner(Mark playerMark) {
        int i = 0;
        boolean winner = false;
        for (int j = 0; j != this.size; j++) {
            if (fieldGame[i][j] == playerMark) {
                i++;
                if (i == this.size) {
                    winner = true;
                    break;
                }
            } else {
                i = 0;
            }
        }
        return winner;
    }

    private boolean diagonalNegativeWinner(Mark playerMark) {
        int i = 0;
        boolean winner = false;
        for (int j = 2; j != -1; j--) {
            if (fieldGame[j][i] == playerMark) {
                i++;
                if (i == this.size) {
                    winner = true;
                    break;
                }
            } else {
                i = 0;
            }
        }
        return winner;
    }

    private boolean lineWinner(Mark playerMark) {
        boolean winner = false;
        for (int i = 0; i < this.size; i++) {
            if (oneLineWinner(playerMark, i)) {
                winner = true;
            }
        }
        return winner;
    }

    private boolean oneLineWinner(Mark playerMark, int lineNumber) {
        int coincidenceOfNumber = 0;
        boolean winnerLine = false;
        for (int i = 0; i != this.size; i++) {
            if (fieldGame[i][lineNumber] == playerMark) {
                coincidenceOfNumber++;
                if (coincidenceOfNumber == this.size) {
                    winnerLine = true;
                }
            }
        }
        return winnerLine;
    }


    private boolean columnWinner(Mark playerMark) {
        int i = 0, j = 0;
        boolean state = false;
        while (i != DEFAULT_FIELD_SIZE) {
            if (fieldGame[i][j] == playerMark) {
                j++;
                if (j == DEFAULT_FIELD_SIZE) {
                    state = true;
                    break;
                }
            } else {
                i++;
                j = 0;
            }
        }
        return state;
    }

    public Stroke computerGenerateStroke(Mark mark) {
        Random random = new Random();
        int randInt;
        randInt = random.nextInt(emptyCellLine().size());
        Stroke computerStroke = new Stroke(emptyCellLine().get(randInt).getX(), emptyCellLine().get(randInt).getY(), mark);

        return computerStroke;

    }

    private ArrayList<Stroke> emptyCellLine() {
        ArrayList<Stroke> arrStroke = new ArrayList<Stroke>();
        int n = 0;
        for (int j = 0; j != DEFAULT_FIELD_SIZE; j++) {
            for (int i = 0; i != DEFAULT_FIELD_SIZE; i++) {
                if (fieldGame[i][j] == Mark.N) {
                    Stroke stroke = new Stroke(i, j, Mark.N);
                    arrStroke.add(n, stroke);
                    n++;
                }
            }
        }
        return arrStroke;
    }

    private void eraseField() {
        for (int i = 0; i < DEFAULT_FIELD_SIZE; i++) {
            eraseLine(i);
        }
    }

    private void eraseLine(int lineNumber) {
        for (int i = 0; i < DEFAULT_FIELD_SIZE; i++) {
            fieldGame[i][lineNumber] = Mark.N;
        }
    }


}
