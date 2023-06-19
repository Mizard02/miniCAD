package is.Interpreter;

import is.command.CommandHandler;
import is.shapes.model.GraphicObject;
import is.shapes.specificcommand.MoveCommand;
import is.shapes.specificcommand.ZoomCommand;
import is.shapes.view.GraphicObjectPanel;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class MV extends Move{
    public MV(Integer id, Pos pos) {
        super(id, pos);
    }

    @Override
    public Object interpreta(GraphicObjectPanel panel, CommandHandler ch) {
        Class c = panel.getClassById(id);
        GraphicObject go;
        if(c == ArrayList.class){
            ArrayList<Integer> list = panel.getGroup(id);
            for(Integer i : list){
                go = panel.getObjectById(i);
                ch.handle(new MoveCommand(go, new Point2D.Double(pos.getX(), pos.getY())));
            }
        }else {
            go = panel.getObjectById(id);
            ch.handle(new MoveCommand(go, new Point2D.Double(pos.getX(), pos.getY())));
        }
        return null;
    }
}
