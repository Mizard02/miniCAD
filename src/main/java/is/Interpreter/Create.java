package is.Interpreter;

import is.command.CommandHandler;
import is.command.HistoryCommandHandler;
import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.CircleObject;
import is.shapes.model.GraphicObject;
import is.shapes.model.GraphicObjectListener;
import is.shapes.specificcommand.MacroCommand;
import is.shapes.specificcommand.MoveCommand;
import is.shapes.specificcommand.NewObjectCmd;
import is.shapes.view.CreateObjectAction;
import is.shapes.view.GraphicObjectPanel;

import java.awt.geom.Point2D;

public class Create extends CMD {

    private TypeConstr tp;
    private Pos p;


    public Create(TypeConstr tp, Pos p) {
        this.tp = tp;
        this.p = p;
    }

    @Override
    public Object interpreta(GraphicObjectPanel panel, CommandHandler ch) {
        AbstractGraphicObject go = (AbstractGraphicObject) tp.interpreta(panel, ch);
        MacroCommand mc = new MacroCommand(new NewObjectCmd(panel, go), new MoveCommand(go, new Point2D.Double(p.getX(), p.getY())));
        ch.handle(mc);
        System.out.println("l'ID dell'oggetto creato è " + panel.getID(go));
        return panel.getID(go);
    }
}
