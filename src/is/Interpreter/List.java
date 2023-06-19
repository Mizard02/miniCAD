package is.Interpreter;

import is.shapes.model.GraphicObject;

import java.util.ArrayList;

public abstract class List extends CMD {

    abstract protected ArrayList<GraphicObject> getGraphicObjects();

    final protected void Stampa (){
        ArrayList<GraphicObject> goList = getGraphicObjects();
        for(GraphicObject go : goList){
            System.out.println(go);
        }
    }
}
