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
        return util.implForID(id);
    }
}
