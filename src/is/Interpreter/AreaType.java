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
       return util.implForType(Type);
    }
}
