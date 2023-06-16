package is.Interpreter;

import is.command.CommandHandler;
import is.command.HistoryCommandHandler;
import is.shapes.model.CircleObject;
import is.shapes.model.GraphicObject;
import is.shapes.model.GraphicObjectListener;
import is.shapes.view.GraphicObjectPanel;

import java.awt.*;
import java.awt.geom.Point2D;

public class CircleTC extends TypeConstr{

    private double raggio;

    public CircleTC(double raggio) {
        this.raggio = raggio;
    }

    public double getRaggio() {
        return raggio;
    }

    public void setRaggio(double raggio) {
        this.raggio = raggio;
    }

    @Override
    public Object interpreta(GraphicObjectPanel panel, CommandHandler ch) {
        return new CircleObject(new Point2D.Double(), raggio);
    }
}
