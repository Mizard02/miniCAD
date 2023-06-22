package is.Interpreter;

import is.shapes.model.GraphicObject;

import java.util.ArrayList;

public class AreaAll extends Area{
    @Override
    protected ArrayList<GraphicObject> getGraphicObjects() {
        return util.implForAll();
    }
}
