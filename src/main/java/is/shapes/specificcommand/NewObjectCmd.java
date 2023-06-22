package is.shapes.specificcommand;

import is.command.Command;
import is.shapes.model.GraphicObject;
import is.shapes.view.GraphicObjectPanel;

public class NewObjectCmd implements Command {

	private GraphicObjectPanel panel;
	private GraphicObject go;

	private double x = 10;
	private double y =  10;

	public NewObjectCmd(GraphicObjectPanel panel, GraphicObject go) {
		this.panel = panel;
		this.go = go;
	}

	public NewObjectCmd(GraphicObjectPanel panel, GraphicObject go, double x, double y) {
		this.panel = panel;
		this.go = go;
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean doIt() {
		go.moveTo(x, y);
		panel.add(go);

		return true;
	}

	@Override
	public boolean undoIt() {
		panel.remove(go);
		return true;
	}

}
