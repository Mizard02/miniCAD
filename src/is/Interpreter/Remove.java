package is.Interpreter;

import is.command.CommandHandler;
import is.shapes.specificcommand.RemoveCommand;
import is.shapes.view.GraphicObjectPanel;

public class Remove extends CMD {

    Integer id;

    public Remove(int id) {
        this.id = id;
    }

    @Override
    public Object interpreta(GraphicObjectPanel panel, CommandHandler ch) {
        ch.handle(new RemoveCommand(panel, id));
        return null;
    }
}
