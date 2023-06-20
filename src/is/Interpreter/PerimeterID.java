package is.Interpreter;

import is.shapes.model.GraphicObject;

import java.util.ArrayList;

public class PerimeterID extends Perimeter{
    private Integer id;

    public PerimeterID(Integer id) {
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
