package is.Interpreter;

import is.command.CommandHandler;
import is.shapes.specificcommand.RemoveCommand;
import is.shapes.view.GraphicObjectPanel;

public class Remove extends CMD implements Expression {

    double id;

    public Remove(double id) {
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "del " + id;
    }


    @Override
    public Object interpreta(GraphicObjectPanel panel, CommandHandler ch) {
        ch.handle(new RemoveCommand(panel, id));
        return null;
    }
}
