package is.Interpreter;

import is.Interpreter.Bridge.UtilsImpl;
import is.command.CommandHandler;
import is.shapes.model.GraphicObject;
import is.shapes.view.GraphicObjectPanel;

import java.util.ArrayList;

public abstract class Perimeter extends CMD{
    protected GraphicObjectPanel panel;

    protected UtilsImpl util;

    abstract protected ArrayList<GraphicObject> getGraphicObjects();

    final protected void Calcola (){
        ArrayList<GraphicObject> goList = getGraphicObjects();
        double res = 0.0;
        for(GraphicObject go : goList){
            res += go.getPerimeter();
        }
        System.out.println("Il perimetro è : " + res);
    }

    public Object interpreta(GraphicObjectPanel panel, CommandHandler ch) {
        util = new UtilsImpl(panel);
        Calcola();
        return null;
    }
}
