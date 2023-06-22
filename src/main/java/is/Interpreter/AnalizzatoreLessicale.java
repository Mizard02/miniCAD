package is.Interpreter;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;

public class AnalizzatoreLessicale {

	private StreamTokenizer input;
	private TerminalExpression simbolo;

	//in questo costruttore creiamo e personalizziamo lo StreamTokenizer
	//per adattarlo all'uso di cui abbiamo bisogno
	public AnalizzatoreLessicale(Reader in) {
		input = new StreamTokenizer(in);
		input.resetSyntax();//rimuovo le modifiche apportate alla tabella dei modificatori
		input.eolIsSignificant(false);//ignoro i caratteri di fine riga non li considero come token separati
		input.wordChars('a', 'z');
		input.wordChars('A', 'Z');//Con questo metodo definiamo il range di caratteri che possono costituire i token
		input.wordChars('0', '9');
		input.wordChars('\u0000', '.');
		input.whitespaceChars('\u0000', ' ');//Con questo metodo definiamo un range di caratteri che verranno interpretati come spazio
		input.ordinaryChar('(');
		input.ordinaryChar(')');
		input.ordinaryChar(',');
		input.quoteChar('"');
		//input.parseNumbers();
	}

	public String getString() {
		return input.sval;
	}

	public TerminalExpression prossimoSimbolo() {
		try {
			switch (input.nextToken()){
			case StreamTokenizer.TT_EOF:
				simbolo = TerminalExpression.EOF;
				break;
			case StreamTokenizer.TT_WORD:
				// verifica prima se la parola e' riservata
				if (input.sval.equalsIgnoreCase("new"))
					simbolo = TerminalExpression.NEW;
				else if (input.sval.equalsIgnoreCase("del"))
					simbolo = TerminalExpression.DEL;
				else if (input.sval.equalsIgnoreCase("circle"))
					simbolo = TerminalExpression.CIRCLE;
				else if (input.sval.equalsIgnoreCase("rectangle"))
					simbolo = TerminalExpression.RECTANGLE;
				else if (input.sval.equalsIgnoreCase("img"))
					simbolo = TerminalExpression.IMG;
				else if (input.sval.equalsIgnoreCase("grp"))
					simbolo = TerminalExpression.GRP;
				else if (input.sval.equalsIgnoreCase("ungrp"))
					simbolo = TerminalExpression.UNGRP;
				else if (input.sval.equalsIgnoreCase("scale"))
					simbolo = TerminalExpression.SCALE;
				else if (input.sval.equalsIgnoreCase("mv"))
					simbolo = TerminalExpression.MV;
				else if (input.sval.equalsIgnoreCase("mvoff"))
					simbolo = TerminalExpression.MVOFF;
				else if (input.sval.equalsIgnoreCase("ls"))
					simbolo = TerminalExpression.LS;
				else if (input.sval.equalsIgnoreCase("all"))
					simbolo = TerminalExpression.ALL;
				else if (input.sval.equalsIgnoreCase("groups"))
					simbolo = TerminalExpression.GROUPS;
				else if (input.sval.equalsIgnoreCase("area"))
					simbolo = TerminalExpression.AREA;
				else if (input.sval.equalsIgnoreCase("perimeter"))
					simbolo = TerminalExpression.PERIMETER;
				else if (input.sval.contains("."))
					simbolo = TerminalExpression.POSFLOAT;
				else if (input.sval.matches("^\\d+$"))
					simbolo = TerminalExpression.OBJID;
				break;
			case '"':
				simbolo = TerminalExpression.PATH;
				break;
			case '(':
				simbolo = TerminalExpression.TONDA_APERTA;
				break;
			case ')':
				simbolo = TerminalExpression.TONDA_CHIUSA;
				break;
				case ',':
					simbolo = TerminalExpression.VIRGOLA;
					break;
			default:
				simbolo = TerminalExpression.CHAR_INVALIDO;
			}
		} catch (IOException e) {
			simbolo = TerminalExpression.EOF;
		}
		return simbolo;
	}// prossimoSimbolo
}// AnalizzatoreLessicale

