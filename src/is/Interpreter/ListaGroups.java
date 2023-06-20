package is.Interpreter;

import is.command.CommandHandler;
import is.shapes.model.GraphicObject;
import is.shapes.view.GraphicObjectPanel;

import java.util.ArrayList;
import java.util.Set;

public class ListaGroups extends List{
    @Override
    public Object interpreta(GraphicObjectPanel panel, CommandHandler ch) {
        ArrayList<ArrayList<Integer>> list =  new ArrayList<>(panel.getAllGroups());
        ArrayList<Integer> listId = new ArrayList<>(panel.getAllIdGroups());
        for(Integer groups : listId) {
            System.out.print("Groups : " + groups + " [ ");
            for (ArrayList<Integer> groupslist : list)
                for (Integer objId : groupslist) {
                    GraphicObject go = panel.getObjectById(objId);
                    System.out.print(go.getType() + ":" + objId + ", ");
                }
            System.out.print(" ] ");
        }
        return null;
    }

    @Override
    protected ArrayList<GraphicObject> getGraphicObjects() {
        return null;
    }
}
