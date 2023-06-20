package is.Interpreter;

import java.io.Reader;
import java.util.ArrayList;

public class Parser {
	private AnalizzatoreLessicale lexer;
	private TerminalExpression simbolo;
	private CMD root;

	//Costruttore
	public Parser(Reader in) {
		lexer = new AnalizzatoreLessicale(in); //analizza solo la combinazione fornita
		root = CMD();
		atteso(TerminalExpression.EOF);
	}

	private CMD CMD(){
		simbolo = lexer.prossimoSimbolo();
		if(simbolo == TerminalExpression.NEW)
			root = create();
		else if(simbolo == TerminalExpression.DEL)
			root = remove();
		else if(simbolo == TerminalExpression.GRP)
			root = group();
		else if(simbolo == TerminalExpression.SCALE)
			root = scale();
		else if (simbolo == TerminalExpression.UNGRP)
			root = ungroup();
		else if ( simbolo == TerminalExpression.MVOFF)
			root = mvoff();
		else if (simbolo == TerminalExpression.MV)
			root = mv();
		else if (simbolo == TerminalExpression.LS)
			root = ls();
		else if (simbolo == TerminalExpression.AREA)
			root = area();
		else if (simbolo == TerminalExpression.PERIMETER)
			root = perimeter();
		simbolo = lexer.prossimoSimbolo();
		return root;
	}

	private Area area(){
		simbolo = lexer.prossimoSimbolo();
		Area res;
		if(simbolo == TerminalExpression.OBJID)
			res = new AreaID(Integer.valueOf(lexer.getString()));
		else if(simbolo == TerminalExpression.RECTANGLE || simbolo == TerminalExpression.CIRCLE || simbolo == TerminalExpression.IMG)
			res = new AreaType(lexer.getString());
		else if(simbolo == TerminalExpression.ALL)
			res = new AreaAll();
		else throw new SyntaxException("inserire OBJID or TYPE or All");
		return res;
	}

	private Perimeter perimeter(){
		simbolo = lexer.prossimoSimbolo();
		Perimeter res;
		if(simbolo == TerminalExpression.OBJID)
			res = new PerimeterID(Integer.valueOf(lexer.getString()));
		else if(simbolo == TerminalExpression.RECTANGLE || simbolo == TerminalExpression.CIRCLE || simbolo == TerminalExpression.IMG)
			res = new PerimeterType(lexer.getString());
		else if(simbolo == TerminalExpression.ALL)
			res = new PerimeterAll();
		else throw new SyntaxException("inserire OBJID or TYPE or All");
		return res;
	}

	private List ls(){
		simbolo = lexer.prossimoSimbolo();
		List res;
		if(simbolo == TerminalExpression.OBJID)
			res = new ListID(Integer.valueOf(lexer.getString()));
		else if(simbolo == TerminalExpression.RECTANGLE || simbolo == TerminalExpression.CIRCLE || simbolo == TerminalExpression.IMG)
			res = new ListType(lexer.getString());
		else if(simbolo == TerminalExpression.ALL)
			res = new ListAll();
		else if (simbolo == TerminalExpression.GROUPS)
			res = new ListaGroups();
		else throw new SyntaxException("inserire OBJID or TYPE or All or GROUPS");
		return res;
	}

	private MV mv(){
		Integer id;
		Pos p;

		simbolo = lexer.prossimoSimbolo();
		if(simbolo == TerminalExpression.OBJID){
			id = Integer.valueOf(lexer.getString());
			simbolo = lexer.prossimoSimbolo();
			p = pos();
		}else
			throw new SyntaxException("inserire L'id esempio -> 1");

		return new MV(id, p);
	}

	private MVOFF mvoff(){
		Integer id;
		Pos p;

		simbolo = lexer.prossimoSimbolo();
		if(simbolo == TerminalExpression.OBJID){
			id = Integer.valueOf(lexer.getString());
			simbolo = lexer.prossimoSimbolo();
			p = pos();
		}else
			throw new SyntaxException("inserire L'id esempio -> 1");

		return new MVOFF(id, p);
	}

	private Ungroup ungroup(){
		simbolo = lexer.prossimoSimbolo();
		Integer id = 0;
		if(simbolo == TerminalExpression.OBJID)
			id = Integer.valueOf(lexer.getString());
		return new Ungroup(id);
	}

	private Scale scale(){
		Integer id;
		Scale res = new Scale();

		simbolo = lexer.prossimoSimbolo();
		if(simbolo == TerminalExpression.OBJID){
			id = Integer.valueOf(lexer.getString());
			simbolo = lexer.prossimoSimbolo();
			if(simbolo == TerminalExpression.POSFLOAT)
				res = new Scale(id, Double.valueOf(lexer.getString()));
		}
		return res;
	}

	private Group group(){
		return new Group(listId());
	}

	private ArrayList<Integer> listId(){
		ArrayList<Integer> res = new ArrayList<>();

		simbolo = lexer.prossimoSimbolo();
		if(simbolo == TerminalExpression.OBJID){
			res.add(Integer.valueOf(lexer.getString()));
			simbolo = lexer.prossimoSimbolo();
			while(simbolo == TerminalExpression.VIRGOLA) {
				simbolo = lexer.prossimoSimbolo();
				if (simbolo == TerminalExpression.OBJID) {
					res.add(Integer.valueOf(lexer.getString()));
					simbolo = lexer.prossimoSimbolo();
				}
			}
		}
		return res;
	}

	private Create create(){
		TypeConstr tc = typeconstr();
		Pos p = pos();
		simbolo = lexer.prossimoSimbolo();
		return new Create(tc, p);
	}

	private TypeConstr typeconstr(){
		simbolo = lexer.prossimoSimbolo();
		TypeConstr res;
		Pos p;
		if (simbolo == TerminalExpression.CIRCLE){
			simbolo = lexer.prossimoSimbolo();
			atteso(TerminalExpression.TONDA_APERTA);
			if(simbolo == TerminalExpression.POSFLOAT) {
				res = new CircleTC(Double.valueOf(lexer.getString()));
				simbolo = lexer.prossimoSimbolo();
			} else
				throw new SyntaxException("atteso POSFLOAT -> float.float ");
			atteso(TerminalExpression.TONDA_CHIUSA);
		} else if(simbolo == TerminalExpression.IMG) {
			simbolo = lexer.prossimoSimbolo();
			atteso(TerminalExpression.TONDA_APERTA);
			if (simbolo == TerminalExpression.PATH){
				res = new ImgTC(lexer.getString());
				simbolo = lexer.prossimoSimbolo();
			} else
				throw new SyntaxException("atteso PATH -> \"C\\cartella1\\file.txt\""); // carattere di escape -> \
			atteso(TerminalExpression.TONDA_CHIUSA);
		} else if(simbolo == TerminalExpression.RECTANGLE){
			simbolo = lexer.prossimoSimbolo();
			p = pos();
			res = new RectangleTC(p);
		} else
			throw new SyntaxException("Atteso comando RECTANGLE, CIRCLE o IMG ");
		return res;
	}

	private Pos pos(){
		double w, h;
		atteso(TerminalExpression.TONDA_APERTA);
		if(simbolo == TerminalExpression.POSFLOAT) {
			w = Double.valueOf(lexer.getString());
			simbolo = lexer.prossimoSimbolo();
			atteso(TerminalExpression.VIRGOLA);
			if (simbolo == TerminalExpression.POSFLOAT){
				h = Double.valueOf(lexer.getString());
				simbolo = lexer.prossimoSimbolo();
				atteso(TerminalExpression.TONDA_CHIUSA);
			} else
				throw new SyntaxException("atteso POSFLOAT -> float.float ");
		} else
			throw new SyntaxException("atteso POSFLOAT -> float.float ");
		return new Pos(w, h);
	}

	private Remove remove(){
		Remove res;
		simbolo = lexer.prossimoSimbolo();
		if(simbolo == TerminalExpression.OBJID)
			res = new Remove(Integer.valueOf(lexer.getString()));
		else
			throw new SyntaxException("atteso id");
		return res;
	}

	private void atteso(TerminalExpression s) {
		if (simbolo != s) {
			String msg = " trovato " + simbolo + " mentre si attendeva " + s;
			throw new SyntaxException(msg);
		}
		simbolo = lexer.prossimoSimbolo();
	}// atteso

	public CMD getComando() {
		return root;
	}// ritorna il composite costruito

}
