package is.Interpreter.Bridge;

import is.shapes.model.GraphicObject;
import is.shapes.view.GraphicObjectPanel;

import java.util.ArrayList;

public interface Utils {

    ArrayList<GraphicObject> implForID( Integer id);
    ArrayList<GraphicObject> implForAll();
    ArrayList<GraphicObject> implForType( String Type);
}
