package is.Interpreter;

import is.command.CommandHandler;
import is.shapes.view.GraphicObjectPanel;

public class Remove extends CMD implements Expression {

    int id;

    public Remove(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
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
        return null;
    }
}
