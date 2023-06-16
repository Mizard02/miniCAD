package is.shapes.view;

import is.Interpreter.SyntaxException;
import is.shapes.model.GraphicEvent;
import is.shapes.model.GraphicObject;
import is.shapes.model.GraphicObjectListener;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.JComponent;

public class GraphicObjectPanel extends JComponent implements GraphicObjectListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8993548105090978185L;

	/**
	 * @directed true
	 */

	private Double id = 0.0;
	private Map<Double, GraphicObject> objects = new HashMap<>();

	private Map<Class<? extends GraphicObject>, GraphicObjectView> viewMap = new HashMap<>();

	public GraphicObjectPanel() {
		setBackground(Color.WHITE);
	}

	@Override
	public void graphicChanged(GraphicEvent e) {
		repaint();
		revalidate();

	}

	
	public GraphicObject getGraphicObjectAt(Point2D p) {
		for (GraphicObject g : objects.values()) {
			if (g.contains(p))
				return g;
		}
		return null;
	}

	@Override
	public Dimension getPreferredSize() {
		Dimension ps = super.getPreferredSize();
		double x = ps.getWidth();
		double y = ps.getHeight();
		for (GraphicObject go : objects.values()) {
			double nx = go.getPosition().getX() + go.getDimension().getWidth() / 2;
			double ny = go.getPosition().getY() + go.getDimension().getHeight() / 2;
			if (nx > x)
				x = nx;
			if (ny > y)
				y = ny;
		}
		return new Dimension((int) x, (int) y);
	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		for (GraphicObject go : objects.values()) {
			GraphicObjectView view = viewMap.get(go.getClass());
			view.drawGraphicObject(go, g2);
		}

	}

	public void add(GraphicObject go) {
		id+=1;
		objects.put(id, go);
		go.addGraphicObjectListener(this);
		repaint();
	}

	public void remove(Double id) {
		if (objects.containsKey(id)){
			objects.remove(id).removeGraphicObjectListener(this);
			repaint();
		}
		else
			throw new SyntaxException("non esiste nessun oggetto con l'id fornito");
	}

	public double getID(GraphicObject go){
		for (Map.Entry<Double, GraphicObject> entry : objects.entrySet()) {
			if (entry.getValue().equals(go)) {
				return entry.getKey();
			}
		}
		return 0;
	}

	public void remove(GraphicObject go) {
		LinkedList<GraphicObject> list = (LinkedList<GraphicObject>) objects.values();
		if (list.remove(go)) {
			repaint();
			go.removeGraphicObjectListener(this);
		}
	}

	public GraphicObject getObjectById(Double id){
		return objects.get(id);
	}

	public void installView(Class<? extends GraphicObject> clazz, GraphicObjectView view) {
		viewMap.put(clazz, view);
	}
}
