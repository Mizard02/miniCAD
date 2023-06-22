package is.Interpreter;

import is.command.CommandHandler;
import is.shapes.view.GraphicObjectPanel;

public class Pos implements Expression
{
    private double X;
    private double Y;

    public Pos(double x, double y) {
        if ( x > 0.0 && y > 0.0) {
            X = x;
            Y = y;
        }
        else throw new SyntaxException("Numeri inseriti negativi, inserire numeri positivi");
    }

    public double getX() {
        return X;
    }

    public double getY() {
        return Y;
    }

    @Override
    public Object interpreta(GraphicObjectPanel panel, CommandHandler ch) {
        return null;
    }
}
