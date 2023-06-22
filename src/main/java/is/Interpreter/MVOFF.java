package is.Interpreter;

import is.command.CommandHandler;
import is.shapes.model.GraphicObject;
import is.shapes.specificcommand.MoveCommand;
import is.shapes.view.GraphicObjectPanel;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class MVOFF extends Move{
    public MVOFF(Integer id, Pos pos) {
        super(id, pos);
    }

    @Override
    public Object interpreta(GraphicObjectPanel panel, CommandHandler ch) {
        Class c = panel.getClassById(id);
        GraphicObject go;
        Point2D p1;
        if(c == ArrayList.class){
            ArrayList<Integer> list = panel.getGroup(id);
            for(Integer i : list){
                go = panel.getObjectById(i);
                p1 = go.getPosition();
                double x = pos.getX() + p1.getX();
                double y = pos.getY() + p1.getY();
                ch.handle(new MoveCommand(go, new Point2D.Double(x, y)));
            }
        }else {
            go = panel.getObjectById(id);
            p1 = go.getPosition();
            double x = pos.getX() + p1.getX();
            double y = pos.getY() + p1.getY();
            ch.handle(new MoveCommand(go, new Point2D.Double(x, y)));
        }
        return null;
    }
}
