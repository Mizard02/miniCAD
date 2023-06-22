package is.Interpreter;

import is.shapes.model.GraphicObject;

import java.util.ArrayList;

public class PerimeterType extends Perimeter{
    String Type;

    public PerimeterType(String type) {
        Type = type;
    }

    @Override
    protected ArrayList<GraphicObject> getGraphicObjects() {
        return util.implForType(Type);
    }
}
