package is.Interpreter;

import is.command.CommandHandler;
import is.shapes.view.GraphicObjectPanel;

public class Ungroup extends CMD{

    Integer groupID;

    public Ungroup(Integer groupID) {
        this.groupID = groupID;
    }

    @Override
    public Object interpreta(GraphicObjectPanel panel, CommandHandler ch) {
        panel.remove(groupID);
        return null;
    }
}
