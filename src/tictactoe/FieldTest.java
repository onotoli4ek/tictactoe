package tictactoe;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class FieldTest {

    @Test
    public void testAllCellsInitializedWithN() {
        Field field = new Field();

        Assert.assertEquals(Mark.N, field.getMark(0, 0));
        assertEquals(Mark.N, field.getMark(0,1));
        assertEquals(Mark.N, field.getMark(0,2));
        assertEquals(Mark.N, field.getMark(1,0));
        assertEquals(Mark.N, field.getMark(1,1));
        assertEquals(Mark.N, field.getMark(1,2));
        assertEquals(Mark.N, field.getMark(2,0));
        assertEquals(Mark.N, field.getMark(2,1));
        assertEquals(Mark.N, field.getMark(2,2));
    }

    @Test
    public void testGameIsNotOverOnStart() {
        Field field = new Field();

        assertFalse(field.isGameOver());
    }

    @Test
    public void testDraw() {
        Field field = new Field();

        field.makeStroke(new Stroke(0,0,Mark.X));
        field.makeStroke(new Stroke(0,1,Mark.O));
        field.makeStroke(new Stroke(0,1,Mark.O));
        field.makeStroke(new Stroke(0,2,Mark.X));
        field.makeStroke(new Stroke(1,0,Mark.O));

        assertFalse(field.isGameOver());

        field.makeStroke(new Stroke(1,1,Mark.O));
        field.makeStroke(new Stroke(1,2,Mark.X));
        field.makeStroke(new Stroke(2,0,Mark.X));
        field.makeStroke(new Stroke(2,1,Mark.X));
        field.makeStroke(new Stroke(2,2,Mark.O));

        assertTrue(field.isGameOver());
        assertEquals(Mark.DRAW, field.getWinner());
    }

    @Test
    public void testGameIsOverAndXWinsDiagonal() {

        Field field = new Field();

        field.makeStroke(new Stroke(0,0,Mark.X));
        field.makeStroke(new Stroke(1,0,Mark.O));
        field.makeStroke(new Stroke(1,1,Mark.X));

        assertFalse(field.isGameOver());

        field.makeStroke(new Stroke(1,2,Mark.O));
        field.makeStroke(new Stroke(2,2,Mark.X));

        assertTrue(field.isGameOver());
        assertEquals(Mark.X, field.getWinner());

    }

}

