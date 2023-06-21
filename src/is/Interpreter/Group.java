package is.Interpreter;

import is.command.CommandHandler;
import is.shapes.view.GraphicObjectPanel;

import java.util.ArrayList;

public class Group extends CMD {

    private ArrayList<Integer> ListID;

    public Group(ArrayList<Integer> listID) {
        ListID = listID;
    }
    @Override
    public Object interpreta(GraphicObjectPanel panel, CommandHandler ch) {

        System.out.println("l'ID del gruppo creato è " + panel.createGroup(ListID));
        return null;
    }
}
