package is.Interpreter;

import java.io.Reader;

public class Parser {
	private AnalizzatoreLessicale lexer;
	private Simboli simbolo;
	private CMD root;

	//Costruttore
	public Parser(Reader in) {
		lexer = new AnalizzatoreLessicale(in); //analizza solo la combinazione fornita
		root = CMD();
		atteso(Simboli.EOF);
	}

	private CMD CMD(){
		simbolo = lexer.prossimoSimbolo();
		if(simbolo == Simboli.NEW){
			root = create();
		} else if(simbolo == Simboli.DEL){
			root = remove();
		}
		simbolo = lexer.prossimoSimbolo();
		return root;
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
		if (simbolo == Simboli.CIRCLE){
			simbolo = lexer.prossimoSimbolo();
			atteso(Simboli.TONDA_APERTA);
			if(simbolo == Simboli.POSFLOAT) {
				res = new CircleTC(Double.valueOf(lexer.getString()));
				simbolo = lexer.prossimoSimbolo();
			}
			else
				throw new SyntaxException("atteso POSFLOAT -> float.float ");
			atteso(Simboli.TONDA_CHIUSA);
		} else if(simbolo == Simboli.IMG) {
			simbolo = lexer.prossimoSimbolo();
			atteso(Simboli.TONDA_APERTA);
			if (simbolo == Simboli.PATH){
				res = new ImgTC(lexer.getString());
				simbolo = lexer.prossimoSimbolo();
			}
			else
				throw new SyntaxException("atteso PATH -> \"C\\cartella1\\file.txt\""); // carattere di escape -> \
			atteso(Simboli.TONDA_CHIUSA);
		} else if(simbolo == Simboli.RECTANGLE){
			simbolo = lexer.prossimoSimbolo();
			p = pos();
			res = new RectangleTC(p);
		} else
			throw new SyntaxException("Atteso comando RECTANGLE, CIRCLE o IMG ");
		return res;
	}

	private Pos pos(){
		double w, h;
		atteso(Simboli.TONDA_APERTA);
		if(simbolo == Simboli.POSFLOAT) {
			w = Double.valueOf(lexer.getString());
			simbolo = lexer.prossimoSimbolo();
			atteso(Simboli.VIRGOLA);
			if (simbolo == Simboli.POSFLOAT){
				h = Double.valueOf(lexer.getString());
				simbolo = lexer.prossimoSimbolo();
				atteso(Simboli.TONDA_CHIUSA);
			}
			else
				throw new SyntaxException("atteso POSFLOAT -> float.float ");
		}
		else
			throw new SyntaxException("atteso POSFLOAT -> float.float ");
		return new Pos(w, h);
	}

	private Remove remove(){
		simbolo = lexer.prossimoSimbolo();
		atteso(Simboli.OBJID);
		return new Remove(Integer.valueOf(lexer.getString()));
	}

    /*private Combinazione combinazione() {

		ParteOr pOr = parteOr();
		Combinazione comb = new Combinazione(pOr);

		while (simbolo == Simboli.OR) {
			comb.addParteOr(parteOr());
		}
		return comb;
	}// combinazione


	private ParteOr parteOr() {
		ParteAnd pAnd = parteAnd();
		ParteOr pOr = new ParteOr(pAnd);

		while (simbolo == Simboli.AND) {
			pOr.addParteAnd(parteAnd());
		}
		return pOr;
	}// parteOr

	private ParteAnd parteAnd() {
		ParteNear left = parteNear();
		ParteNear right = null;
		if (simbolo == Simboli.NEAR)
			right = parteNear();
		return new ParteAnd(left, right);

	}// parteAnd

	private ParteNear parteNear() {
		simbolo = lexer.prossimoSimbolo();
		if (simbolo == Simboli.NOT) {

			simbolo = lexer.prossimoSimbolo();
			Elemento el = elemento();
			return new ParteNearElemento(true, el);
		}
		if (simbolo == Simboli.TONDA_APERTA) {
			Combinazione cb = combinazione();
			atteso(Simboli.TONDA_CHIUSA);
			return new ParteNearCombinazione(cb);
		}
		return new ParteNearElemento(false, elemento());

	}// parteNear

	private Elemento elemento() {
		Elemento res = null;
		if (simbolo == Simboli.STRINGA_QUOTATA) {
			res = new Elemento(lexer.getString(), true);

			simbolo = lexer.prossimoSimbolo();
		} else if (simbolo == Simboli.PAROLA) {
			res = new Elemento(lexer.getString(), false);

			simbolo = lexer.prossimoSimbolo();
		} else
			throw new SyntaxException(
					"Attesa STRINGA_QUOTATA o PAROLA in elemento()");
		return res;
	}// elemento*/

	private void atteso(Simboli s) {
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
