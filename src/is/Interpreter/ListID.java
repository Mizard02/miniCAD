package is.Interpreter;

import is.command.CommandHandler;
import is.shapes.model.GraphicObject;
import is.shapes.specificcommand.ZoomCommand;
import is.shapes.view.GraphicObjectPanel;

import java.util.ArrayList;

public class ListID extends List{

    private Integer id;

    public ListID(Integer id) {
        this.id = id;
    }

    @Override
    protected ArrayList<GraphicObject> getGraphicObjects() {
        Class c = panel.getClassById(id);
        ArrayList<GraphicObject> res = new ArrayList<GraphicObject>();
        GraphicObject go;
        if(c == ArrayList.class){
            ArrayList<Integer> list = panel.getGroup(id);
            for(Integer i : list){
                go = panel.getObjectById(i);
                res.add(go);
            }
        }else {
            go = panel.getObjectById(id);
            res.add(go);
        }
        return res;
    }
}
