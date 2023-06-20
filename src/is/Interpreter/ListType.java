package is.Interpreter;

import is.command.CommandHandler;
import is.shapes.model.GraphicObject;
import is.shapes.view.GraphicObjectPanel;

import java.util.ArrayList;

public class ListType extends List{

    String Type;

    public ListType(String type) {
        Type = type;
    }

    @Override
    protected ArrayList<GraphicObject> getGraphicObjects() {
        ArrayList<GraphicObject> list = new ArrayList<>(panel.getAllObjects());
        ArrayList<GraphicObject> res = new ArrayList<>();

        for(GraphicObject go : list){
            if(go.getType().equalsIgnoreCase(Type))
                res.add(go);
        }

        return res;
    }


}
