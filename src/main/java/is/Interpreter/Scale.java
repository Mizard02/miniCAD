package is.Interpreter;

import is.command.CommandHandler;
import is.shapes.model.GraphicObject;
import is.shapes.specificcommand.ZoomCommand;
import is.shapes.view.GraphicObjectPanel;

import java.util.ArrayList;

public class Scale extends CMD{

    Integer id;
    double factor;

    public Scale (){}

    public Scale(Integer id, double factor) {
        this.id = id;
        this.factor = factor;
    }

    @Override
    public Object interpreta(GraphicObjectPanel panel, CommandHandler ch) {
        Class c = panel.getClassById(id);
        GraphicObject go;
        if(c == ArrayList.class){
            ArrayList<Integer> list = panel.getGroup(id);
            for(Integer i : list){
                go = panel.getObjectById(i);
                ch.handle(new ZoomCommand(go, factor));
            }
        }else {
            go = panel.getObjectById(id);
            ch.handle(new ZoomCommand(go, factor));
        }
        return null;
    }
}
