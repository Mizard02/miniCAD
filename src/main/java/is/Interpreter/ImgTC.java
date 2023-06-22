package is.Interpreter;

import is.TestGraphics2;
import is.command.CommandHandler;
import is.shapes.model.ImageObject;
import is.shapes.view.GraphicObjectPanel;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ImgTC extends TypeConstr{

    private String path;

    public ImgTC(String path) {
        this.path = path;
    }

    @Override
    public Object interpreta(GraphicObjectPanel panel, CommandHandler ch) {
        return  new ImageObject(new ImageIcon(path), new Point());
    }
}
