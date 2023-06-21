package is.Interpreter;

import is.command.CommandHandler;
import is.shapes.model.GraphicObject;
import is.shapes.specificcommand.ZoomCommand;
import is.shapes.view.GraphicObjectPanel;

import java.util.ArrayList;

public class ListID extends List{

    private Integer id;

    public ListID(Integer id) {
        this.id = id;
    }

    @Override
    protected ArrayList<GraphicObject> getGraphicObjects() {
        return util.implForID(id);
    }
}
