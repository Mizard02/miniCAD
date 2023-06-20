package is.Interpreter;

import is.command.CommandHandler;
import is.shapes.model.GraphicObject;
import is.shapes.view.GraphicObjectPanel;

import java.util.ArrayList;

public class ListAll extends List{

    @Override
    protected ArrayList<GraphicObject> getGraphicObjects() {
        return new ArrayList<>(panel.getAllObjects());
    }
}
