package is.Interpreter;

import is.shapes.model.GraphicObject;

import java.util.ArrayList;

public class PerimeterAll extends Perimeter{
    @Override
    protected ArrayList<GraphicObject> getGraphicObjects() {
        return util.implForAll();
    }
}
