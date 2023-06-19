package is.Interpreter;

import is.command.CommandHandler;
import is.shapes.view.GraphicObjectPanel;

public enum TerminalExpression implements Expression{
	CHAR_INVALIDO, NESSUN_SIMBOLO, NEW, DEL, CIRCLE, RECTANGLE, IMG, PATH, OBJID, POSFLOAT, GRP, SCALE, UNGRP, MV, MVOFF, LS, ALL, GROUPS, AREA, PERIMETER, VIRGOLA, TONDA_APERTA, TONDA_CHIUSA, EOF;

	@Override
	public Object interpreta(GraphicObjectPanel panel, CommandHandler ch) {
		return null;
	}
}
