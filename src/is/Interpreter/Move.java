package is.Interpreter;

import is.command.CommandHandler;
import is.shapes.view.GraphicObjectPanel;

public abstract class Move extends CMD{

    protected Integer id;

    protected Pos pos;

    public Move(Integer id, Pos pos) {
        this.id = id;
        this.pos = pos;
    }


}
