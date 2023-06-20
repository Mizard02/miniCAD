package is.Interpreter;

import is.command.CommandHandler;
import is.shapes.model.GraphicObject;
import is.shapes.view.GraphicObjectPanel;

import java.util.ArrayList;

public abstract class List extends CMD {

    protected GraphicObjectPanel panel;

    abstract protected ArrayList<GraphicObject> getGraphicObjects();

    final protected void Stampa (){
        ArrayList<GraphicObject> goList = getGraphicObjects();
        for(GraphicObject go : goList){
            System.out.println(go + "\n");
        }
    }

    public Object interpreta(GraphicObjectPanel panel, CommandHandler ch) {
        this.panel = panel;
        Stampa();
        return null;
    }
}
