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
import java.util.*;

import javax.swing.JComponent;

public class GraphicObjectPanel extends JComponent implements GraphicObjectListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8993548105090978185L;

	/**
	 * @directed true
	 */

	private Integer id = 0;
	private Map<Integer, GraphicObject> objects = new HashMap<>();
	private Map<Integer, ArrayList<Integer>> groups = new HashMap<>();
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
		go.setId(id);
		objects.put(id, go);
		go.addGraphicObjectListener(this);
		repaint();
	}

	public void remove(Integer id) {
		if (objects.containsKey(id)){
			objects.remove(id).removeGraphicObjectListener(this);
		} else if (groups.containsKey(id)) {
			ArrayList<Integer> l = groups.get(id);
			for(Integer i: l)
				objects.remove(i).removeGraphicObjectListener(this);
			groups.remove(id);
		} else
			throw new SyntaxException("non esiste nessun oggetto/gruppo con l'id fornito");
		repaint();
	}

	public Integer getID(GraphicObject go){
		for (Map.Entry<Integer, GraphicObject> entry : objects.entrySet()) {
			if (entry.getValue().equals(go)) {
				return entry.getKey();
			}
		}
		return 0;
	}

	public void remove(GraphicObject go) {
		remove(go.getId());
	}

	public Class getClassById(Integer id){
		Class c = Object.class;
		if(objects.containsKey(id))
			return GraphicObject.class;
		else if (groups.containsKey(id))
			return ArrayList.class;
		return c;
	}

	public ArrayList<Integer> getGroup(Integer id){
		return groups.get(id);
	}

	public void removeGroup(Integer id){
		groups.remove(id);
	}

	public GraphicObject getObjectById(Integer id){
		return objects.get(id);
	}

	public Collection<GraphicObject> getAllObjects(){return objects.values();}

	public Collection<ArrayList<Integer>> getAllGroups(){return groups.values();}

	public Set<Integer> getAllIdGroups(){return groups.keySet();}

	public Integer createGroup(ArrayList<Integer> listId){
		for(Integer id : listId)
			if(!objects.containsKey(id) && !groups.containsKey(id))
				throw new SyntaxException("non esiste nessun oggetto con l'id fornito");
		id+=1;
		groups.put(id, listId);
		return id;
	}

	public void installView(Class<? extends GraphicObject> clazz, GraphicObjectView view) {
		viewMap.put(clazz, view);
	}
}
