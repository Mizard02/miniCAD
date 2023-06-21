package is.Interpreter.Bridge;

import is.shapes.model.GraphicObject;
import is.shapes.view.GraphicObjectPanel;

import java.util.ArrayList;

public class UtilsImpl implements Utils{

    private GraphicObjectPanel panel;

    public UtilsImpl(GraphicObjectPanel panel) {
        this.panel = panel;
    }

    @Override
    public ArrayList<GraphicObject> implForID( Integer id) {
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

    @Override
    public ArrayList<GraphicObject> implForAll() {
        return new ArrayList<>(panel.getAllObjects());
    }

    @Override
    public ArrayList<GraphicObject> implForType( String Type) {
        ArrayList<GraphicObject> list = new ArrayList<>(panel.getAllObjects());
        ArrayList<GraphicObject> res = new ArrayList<>();

        for(GraphicObject go : list){
            if(go.getType().equalsIgnoreCase(Type))
                res.add(go);
        }

        return res;
    }
}
