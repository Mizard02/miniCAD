package is.shapes.specificcommand;

import is.command.Command;
import is.shapes.model.GraphicObject;
import is.shapes.view.GraphicObjectPanel;

public class RemoveCommand implements Command {

    private GraphicObjectPanel panel;
    private Integer id;

    private GraphicObject ripristina;

    public RemoveCommand(GraphicObjectPanel panel, Integer id) {
        this.panel = panel;
        this.id = id;
    }

    @Override
    public boolean doIt() {
        ripristina = panel.getObjectById(id);
        panel.remove(id);
        return true;
    }

    @Override
    public boolean undoIt() {
        panel.add(ripristina);
        System.out.println("l'oggetto è stato ripristinato con il nuovo ID: " + ripristina.getId());
        return false;
    }
}
