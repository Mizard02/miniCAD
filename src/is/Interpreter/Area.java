package is.Interpreter;

import is.command.CommandHandler;
import is.shapes.model.GraphicObject;
import is.shapes.view.GraphicObjectPanel;

import java.util.ArrayList;

public abstract class Area extends CMD{

    protected GraphicObjectPanel panel;

    abstract protected ArrayList<GraphicObject> getGraphicObjects();

    final protected void Calcola (){
        ArrayList<GraphicObject> goList = getGraphicObjects();
        double res = 0.0;
        for(GraphicObject go : goList){
            res += go.getArea();
        }
        System.out.println("L'area è : " + res);
    }

    public Object interpreta(GraphicObjectPanel panel, CommandHandler ch) {
        this.panel = panel;
        Calcola();
        return null;
    }
}
