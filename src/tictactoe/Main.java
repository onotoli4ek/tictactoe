package tictactoe;

public class Main {

    public static void main(String[] args) {

        Field field = new Field();
        IOHelper ioHelper = new IOHelper();
        Computer comp = new Computer();
        while (!field.isGameOver()) {
            System.out.println("Please enter X");
            while (!field.makeStroke(ioHelper.getStroke(Mark.X, field))) {
            }
            ioHelper.printField(field);
            if (!field.isGameOver()) {
                System.out.println("Please enter O");
                while (!field.makeStroke(comp.computerGenerateStroke(Mark.O, field))) {
                }
                ioHelper.printField(field);
            }
        }
        ioHelper.printResult(field.getWinner());
    }
}

