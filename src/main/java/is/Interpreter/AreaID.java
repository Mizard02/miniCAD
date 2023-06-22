package is.Interpreter;

import is.shapes.model.GraphicObject;

import java.util.ArrayList;

public class AreaID extends Area{
    private Integer id;

    public AreaID(Integer id) {
        this.id = id;
    }

    @Override
    protected ArrayList<GraphicObject> getGraphicObjects() {
        return util.implForID(id);
    }
}
