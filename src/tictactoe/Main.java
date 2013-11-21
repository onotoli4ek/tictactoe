package tictactoe;

public class Main {

    public static void main(String[] args) {

        Field field = new Field();
        IOHelper ioHelper = new IOHelper();

        while (!field.isGameOver()) {
            System.out.println("Please enter X");
            while (!field.makeStroke(ioHelper.getStroke(Mark.X))) {
            }
            ioHelper.printField(field);
            if (!field.isGameOver()) {
                System.out.println("Please enter O");
                while (!field.makeStroke(field.computerGenerateStroke(Mark.O))) {
                }
                ioHelper.printField(field);
            }
        }
        ioHelper.printResult(field.getWinner());
    }
}

