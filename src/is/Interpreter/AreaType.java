package is.Interpreter;

import is.shapes.model.GraphicObject;

import java.util.ArrayList;

public class AreaType extends Area{
    String Type;

    public AreaType(String type) {
        Type = type;
    }

    @Override
    protected ArrayList<GraphicObject> getGraphicObjects() {
        ArrayList<GraphicObject> list = new ArrayList<>(panel.getAllObjects());
        ArrayList<GraphicObject> res = new ArrayList<>();

        for(GraphicObject go : list){
            if(go.getType().equalsIgnoreCase(Type))
                res.add(go);
        }

        return res;
    }
}
