package is.Interpreter;

import is.command.CommandHandler;
import is.shapes.model.RectangleObject;
import is.shapes.view.GraphicObjectPanel;

import java.awt.geom.Point2D;

public class RectangleTC extends TypeConstr{

    private Pos dimension;

    public RectangleTC(Pos position) {
        this.dimension = position;
    }

    @Override
    public Object interpreta(GraphicObjectPanel panel, CommandHandler ch) {
        return new RectangleObject(new Point2D.Double(), dimension.getX(), dimension.getY());
    }
}
