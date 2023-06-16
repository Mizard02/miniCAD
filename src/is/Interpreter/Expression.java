package is.Interpreter;

import is.command.CommandHandler;
import is.shapes.model.GraphicObjectListener;
import is.shapes.view.GraphicObjectPanel;

public interface Expression {

    Object interpreta(GraphicObjectPanel panel, CommandHandler ch);
}
