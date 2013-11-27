package tictactoe;


public class Stroke {

    private int x;
    private int y;
    private Mark mark;

    public Stroke() {
        mark = Mark.N;
    }

    public Stroke(int x, int y, Mark mark) {
        this.x = x;
        this.y = y;
        this.mark = mark;
    }

    public void setX(int x) {
        if (x > 0 && x < Field.DEFAULT_FIELD_SIZE)
        this.x = x;
    }

    public void setY(int y) {
        if (y > 0 && y < Field.DEFAULT_FIELD_SIZE)
        this.y = y;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Mark getMark() {
        return mark;
    }
}
