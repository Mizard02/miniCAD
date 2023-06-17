package is.Interpreter;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;

public class AnalizzatoreLessicale {

	private StreamTokenizer input;
	private Simboli simbolo;

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

	public Simboli prossimoSimbolo() {
		try {
			switch (input.nextToken()){
			case StreamTokenizer.TT_EOF:
				simbolo = Simboli.EOF;
				break;
			case StreamTokenizer.TT_WORD:
				// verifica prima se la parola e' riservata
				if (input.sval.equalsIgnoreCase("new"))
					simbolo = Simboli.NEW;
				else if (input.sval.equalsIgnoreCase("del"))
					simbolo = Simboli.DEL;
				else if (input.sval.equalsIgnoreCase("circle"))
					simbolo = Simboli.CIRCLE;
				else if (input.sval.equalsIgnoreCase("rectangle"))
					simbolo = Simboli.RECTANGLE;
				else if (input.sval.equalsIgnoreCase("img"))
					simbolo = Simboli.IMG;
				else if (input.sval.equalsIgnoreCase("grp"))
					simbolo = Simboli.GRP;
				else if (input.sval.equalsIgnoreCase("ungrp"))
					simbolo = Simboli.UNGRP;
				else if (input.sval.equalsIgnoreCase("scale"))
					simbolo = Simboli.SCALE;
				else if (input.sval.contains("."))
					simbolo = Simboli.POSFLOAT;
				else if (input.sval.matches("\\d"))
					simbolo = Simboli.OBJID;
				break;
			case '"':
				simbolo = Simboli.PATH;
				break;
			case '(':
				simbolo = Simboli.TONDA_APERTA;
				break;
			case ')':
				simbolo = Simboli.TONDA_CHIUSA;
				break;
				case ',':
					simbolo = Simboli.VIRGOLA;
					break;
				/*case StreamTokenizer.TT_NUMBER:
					if(input.toString().contains("."))
					simbolo = Simboli.POSFLOAT;
						else
						simbolo = Simboli.OBJID;
					break;*/
			default:
				simbolo = Simboli.CHAR_INVALIDO;
			}
		} catch (IOException e) {
			simbolo = Simboli.EOF;
		}
		return simbolo;
	}// prossimoSimbolo
}// AnalizzatoreLessicale

